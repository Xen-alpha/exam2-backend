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
public class BaseResponse<T> {
    @Schema(description="성공하면 true가 뜹니다")
    private boolean success;
    @Schema(description="성공할 경우 응답 객체가 들어가고 실패하면 null이 됩니다")
    private T result;
}
