package com.mr.zwt.shop.base;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Result
 * @Description: TODO
 * @Author zwt
 * @Date 2021/1/18
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
public class Result<T>{
    private Integer code;//返回码

    private String message;//返回消息

    private T data;//返回数据

    public Result(Integer code , String message , Object data){
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }
}
