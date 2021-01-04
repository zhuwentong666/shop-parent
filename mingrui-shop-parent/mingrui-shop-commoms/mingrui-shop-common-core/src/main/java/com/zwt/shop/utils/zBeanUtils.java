package com.zwt.shop.utils;

import org.springframework.beans.BeanUtils;

/**
 * @ClassName BeanUtils
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/25
 * @Version V1.0
 **/
public class zBeanUtils<T> {

    public static <T> T copyBean(Object source,Class<T> clazz)  {
        T t = null;//创建实例 类似 为为传来的参数new 一个实例
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(source,t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    }
}
