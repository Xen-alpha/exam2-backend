package org.springpractice.exam2backend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springpractice.exam2backend.model.request.CreateCommentRequest;
import org.springpractice.exam2backend.model.request.CreatePostRequest;
import org.springpractice.exam2backend.model.response.BaseResponse;
import org.springpractice.exam2backend.model.response.BoardListItemResponse;
import org.springpractice.exam2backend.model.response.PostResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Tag(name="게시판 기능", description="게시글 작성 및 조회, 댓글 기능")
public class BoardController {
    private final BoardService boardService;

    @Operation(description="게시글 작성")
    @ApiResponse(responseCode = "200", description="정상적으로 작성한 경우 성공")
    @ApiResponse(responseCode = "400", description="양식이 잘못된 경우 에러")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<String>> createPost(
            @Parameter(description = "게시글 작성 양식 객체입니다")
            @Valid @RequestBody CreatePostRequest body) {
        boardService.createPost(body);
        return ResponseEntity.ok(new BaseResponse<>(true,"OK"));
    }

    @Operation(description="게시글 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<BoardListItemResponse>>> listPosts() {
        List<BoardListItemResponse> result = boardService.listAll();
        BaseResponse<List<BoardListItemResponse>> response = BaseResponse.<List<BoardListItemResponse>>builder()
                .success(true).result(result)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(description="특정 게시글 내용 및 댓글 목록 조회")
    @ApiResponse(responseCode = "200", description="있는 게시글일 경우 성공")
    @ApiResponse(responseCode = "404", description="없는 게시글일 경우 자원 없음 에러")
    @GetMapping("/{idx}")
    public ResponseEntity<BaseResponse<PostResponse>> getPost(
            @Parameter(description = "게시글 id, 양의 정수 숫자입니다")
            @Valid  @PathVariable Long idx) {
        try {
            PostResponse result = boardService.getPost(idx);
            BaseResponse<PostResponse> response = BaseResponse.<PostResponse>builder()
                    .success(true).result(result)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BaseResponse<PostResponse> response = BaseResponse.<PostResponse>builder()
                    .success(false)
                    .build();
            return ResponseEntity.status(404).body(response);
        }
    }

    @Operation(description="댓글 작성")
    @ApiResponse(responseCode = "200", description="있는 게시글에 정상적으로 작성한 경우 성공")
    @ApiResponse(responseCode = "400", description="없는 게시글에 작성하거나 양식이 잘못된 경우 에러")
    @PostMapping("/{idx}/comment")
    public ResponseEntity<BaseResponse<String>> createPost(
            @Parameter(description="댓글 작성 양식입니다")
            @Valid @RequestBody CreateCommentRequest body,
            @Parameter(description="URL에 있는 게시글 번호입니다")
            @PathVariable Long idx) {
        try {
            boardService.createComment(body, idx);
            return ResponseEntity.ok(new BaseResponse<>(true, "OK"));
        } catch (Exception e) {
            BaseResponse<String> response = BaseResponse.<String>builder()
                    .success(false)
                    .build();
            return ResponseEntity.status(400).body(response);
        }
    }
}
