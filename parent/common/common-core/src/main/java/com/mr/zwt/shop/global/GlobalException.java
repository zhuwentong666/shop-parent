package com.mr.zwt.shop.global;

import com.alibaba.fastjson.JSONObject;
import com.mr.zwt.shop.base.Result;
import com.mr.zwt.shop.status.HTTPStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName GlobalException
 * @Description: TODO
 * @Author zwt
 * @Date 2021/1/22
 * @Version V1.0
 **/



@RestControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(value = RuntimeException.class)
    public Result<JSONObject> testException(RuntimeException e){
        log.error("code : {},message:{}", HTTPStatus.ERROR,e.getMessage());
        return new Result<JSONObject>(HTTPStatus.ERROR,e.getMessage(),null);
    }
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public Map<String,Object>methodArgumentNotValidException(MethodArgumentNotValidException exception){
        HashMap<String,Object> map = new HashMap<>();
        map.put("code",HTTPStatus.PARAMS_VALIDATE_ERROR);

        List<String>msgList=new ArrayList<>();

        exception.getBindingResult().getFieldErrors().stream().forEach(error->{
            msgList.add("Filed -->"+error.getField()+":"+error.getDefaultMessage());
            log.error("Filed-->"+error.getField()+":"+error.getDefaultMessage());

        });
        String message=msgList.parallelStream().collect(Collectors.joining(","));
        map.put("message",message);
        return map;
    }

}
