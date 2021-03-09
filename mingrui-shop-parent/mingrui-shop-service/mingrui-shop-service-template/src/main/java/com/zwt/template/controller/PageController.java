package com.zwt.template.controller;

import com.zwt.template.service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName controller
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/3/8
 * @Version V1.0
 **/
//@Controller
//@RequestMapping(value = "item")
public class PageController {

//    @Resource
    private PageService pageService;

//    @GetMapping(value = "{spuId}.html")
    public String test(@PathVariable(value="spuId")  Integer spuId , ModelMap map){
      Map<String,Object> goods = pageService.getGoodsInfo(spuId);
        map.putAll(goods);
        return "item";
    }


}
