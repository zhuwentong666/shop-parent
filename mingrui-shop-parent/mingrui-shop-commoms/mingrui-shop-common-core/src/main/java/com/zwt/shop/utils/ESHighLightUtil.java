package com.zwt.shop.utils;

import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName ESHighLightUtil
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/3/4
 * @Version V1.0
 **/
public class ESHighLightUtil {

    //构建高亮字段buiilder
    public static HighlightBuilder getHighlightBuilder(String ...highLightField)
    {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        Arrays.asList(highLightField).forEach(hlf -> {
            HighlightBuilder.Field field = new HighlightBuilder.Field(hlf);
            field.preTags("<span style='color:red'>");
            field.postTags("</span>");
            highlightBuilder.field(field);//这个值不会被覆盖,看源码
        });
        return highlightBuilder;
    }
    //将返回的内容替换成高亮
    public static <T> List<SearchHit<T>> getHighLightHit(List<SearchHit<T>>
                                                                 list){
        return list.stream().map(hit -> {
            //得到高亮字段
            Map<String, List<String>> highlightFields =
                    hit.getHighlightFields();
            highlightFields.forEach((key,value) -> {
                try {
                    T content = hit.getContent();//当前文档 T为当前文档类型
            //content.getClass()获取当前文档类型,并且得到排序字段的set方法
                //注意这种首字母大写的方式效率非常低,大数据环境下绝对不允许,但是可以实效果
                //可以参考ascll表来实现首字母大写
                    Method method = content.getClass().getMethod("set" +
                            String.valueOf(key.charAt(0)).toUpperCase() + key.substring(1),String.class);
                    //执行set方法并赋值
                    method.invoke(content,value.get(0));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            return hit;
        }).collect(Collectors.toList());
    }
    //首字母大写,效率最高!
    private static String firstCharUpperCase(String name){
        char[] chars = name.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

}
