package org.springpractice.exam2backend.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateCommentRequest {
    private String content;
    private String writer;
    private Long postIdx;
}
