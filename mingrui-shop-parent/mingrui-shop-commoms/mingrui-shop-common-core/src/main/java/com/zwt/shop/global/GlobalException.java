package com.zwt.shop.global;

import com.google.gson.JsonObject;
import com.zwt.shop.base.Result;
import com.zwt.shop.status.HTTPStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalException
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/24
 * @Version V1.0
 **/
@RestControllerAdvice
@Slf4j
public class GlobalException {

//    @ExceptionHandler(value = RuntimeException.class)
//    public Result<JsonObject> testExcepiton(RuntimeException runtimeException){
//
//        log.error("code : {} , message : {}", HTTPStatus.ERROR,runtimeException.getMessage());
//
//        return new Result<JsonObject>(HTTPStatus.ERROR,runtimeException.getMessage(),null);
//    }

}
