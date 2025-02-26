package org.springpractice.exam2backend.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommentResponse {
    @Schema(description="댓글 내용")
    private String content;
    @Schema(description="댓글 작성자 필명")
    private String writer;
}
