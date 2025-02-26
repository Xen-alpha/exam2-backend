package org.springpractice.exam2backend.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePostRequest {
    private String title;
    private String content;
    private String writer;
}
