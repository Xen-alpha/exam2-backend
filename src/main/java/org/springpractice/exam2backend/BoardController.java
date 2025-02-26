package org.springpractice.exam2backend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springpractice.exam2backend.model.request.CreateCommentRequest;
import org.springpractice.exam2backend.model.request.CreatePostRequest;
import org.springpractice.exam2backend.model.response.BaseResponse;
import org.springpractice.exam2backend.model.response.BoardListItemResponse;
import org.springpractice.exam2backend.model.response.PostResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<String>> createPost(@RequestBody CreatePostRequest body) {
        boardService.createPost(body);
        return ResponseEntity.ok(new BaseResponse<>(true,"OK"));
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<BoardListItemResponse>>> listPosts() {
        List<BoardListItemResponse> result = boardService.listAll();
        BaseResponse<List<BoardListItemResponse>> response = BaseResponse.<List<BoardListItemResponse>>builder()
                .success(true).result(result)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<BaseResponse<PostResponse>> getPost(@PathVariable Long idx) {
        PostResponse result = boardService.getPost(idx);
        BaseResponse<PostResponse> response = BaseResponse.<PostResponse>builder()
                .success(true).result(result)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{idx}/comment")
    public ResponseEntity<BaseResponse<String>> createPost(@RequestBody CreateCommentRequest body, @PathVariable Long idx) {
        boardService.createComment(body, idx);
        return ResponseEntity.ok(new BaseResponse<>(true,"OK"));
    }
}
