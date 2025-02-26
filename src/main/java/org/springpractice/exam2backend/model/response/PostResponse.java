package org.springpractice.exam2backend.model.response;

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
    private Long idx;
    private String title;
    private String content;
    private String writer;
    List<CommentResponse> comments;
}
