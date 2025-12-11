package com.fpj.report.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "统一返回结果")
public class ResultVO<T> {

    @Schema(description = "状态码", example = "200")
    private Integer code;

    @Schema(description = "返回消息", example = "操作成功")
    private String message;

    @Schema(description = "返回数据")
    private T data;

    @Schema(description = "时间戳", example = "1733932800000")
    private Long timestamp;

    public ResultVO() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultVO<T> success(String message, T data) {
        ResultVO<T> result = new ResultVO<>();
        result.code = 200;
        result.message = message;
        result.data = data;
        return result;
    }

    public static <T> ResultVO<T> error(String message) {
        ResultVO<T> result = new ResultVO<>();
        result.code = 500;
        result.message = message;
        return result;
    }
}
