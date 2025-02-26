package org.springpractice.exam2backend.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePostRequest {
    @Schema(description="게시글 제목")
    @NotBlank
    private String title;
    @Schema(description="게시글 내용")
    @NotBlank
    private String content;
    @Schema(description="게시글 작성자 필명")
    @NotBlank
    private String writer;
}
