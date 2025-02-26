package org.springpractice.exam2backend.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostResponse {
    @Schema(description="게시글 id")
    private Long idx;
    @Schema(description="게시글 제목")
    private String title;
    @Schema(description="게시글 내용")
    private String content;
    @Schema(description="게시글 작성자 필명")
    private String writer;
    @Schema(description="게시글에 달린 댓글 목록")
    List<CommentResponse> comments;
}
