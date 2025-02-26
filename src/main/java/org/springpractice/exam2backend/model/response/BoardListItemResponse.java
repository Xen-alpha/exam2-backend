package org.springpractice.exam2backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BoardListItemResponse {
    private Long idx;
    private String title;
    private String writer;
    private Integer commentsNumber;
}
