package com.edt.common;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.iceutils.json.IceJsonStringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseController {
    @Resource
    private HttpServletResponse httpServletResponse;

    /**
     * 转换成JSON字符串回写到页面
     *
     * @param object object
     * @author 刘钢
     * 2017-05-18 11:28
     */

    public void WriterToPageByJson(Object object) {
        WriterToPageByString(toJsonString(object));
    }

    /**
     * 转换成JSON字符串回写到页面
     *
     * @param object      object
     * @param dateformate dateformate
     * @author 刘钢
     * 2017-05-18 11:29
     */

    public void WriterToPageByJson(Object object, String dateformate) {
        WriterToPageByString(toJsonString(object, dateformate));
    }

    /**
     * 转换成JSON字符串回写到页面,不打印null属性
     *
     * @param object object
     * @author 刘钢
     * 2017-05-18 11:29
     */

    public void WriterToPageByJsonNoNull(Object object) {
        WriterToPageByString(toJsonStringNoNull(object));
    }

    /**
     * 转换成JSON字符串回写到页面,不打印null属性
     *
     * @param object      object
     * @param dateformate dateformate
     * @author 刘钢
     * 2017-05-18 11:29
     */

    public void WriterToPageByJsonNoNull(Object object, String dateformate) {
        WriterToPageByString(toJsonStringNoNull(object, dateformate));
    }

    /**
     * 根据属性过滤器，转换成JSON字符串回写到页面
     *
     * @param object      object
     * @param filter      filter
     * @param dateformate dateformate
     * @author 刘钢
     * 2017-05-18 11:29
     */

    public void WriterToPageByJsonByFilter(Object object,
                                           SimplePropertyPreFilter filter, String dateformate) {
        WriterToPageByString(toJsonStringByFilter(object, filter, dateformate));
    }

    /**
     * 根据属性过滤器，转换成JSON字符串回写到页面
     *
     * @param object object
     * @param filter filter
     * @author 刘钢
     * 2017-05-18 11:30
     */

    public void WriterToPageByJsonByFilter(Object object,
                                           SimplePropertyPreFilter filter) {
        WriterToPageByString(toJsonStringByFilter(object, filter));
    }

    /**
     * 根据属性过滤器，转换成JSON字符串回写到页面
     *
     * @param object object
     * @author 刘钢
     * 2017-05-18 11:30
     */

    public void WriterToPageByJsonByFilter(Object object) {
        WriterToPageByString(toJsonStringNoNull(object));
    }

    /**
     * 根据属性过滤器，转换成JSON字符串回写到页面,不显示null属性
     *
     * @param object object
     * @param filter filter
     * @author 刘钢
     * 2017-05-18 11:30
     */

    public void WriterToPageByJsonByFilterNoNull(Object object,
                                                 SimplePropertyPreFilter filter) {
        WriterToPageByString(toJsonStringByFilter(object, filter));
    }

    /**
     * 将字符串回写到页面
     *
     * @param str str
     * @author 刘钢
     * 2017-05-18 11:31
     */

    public void WriterToPageByString(String str) {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter writer = httpServletResponse.getWriter();
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -----------JSON字符串处理------------------//
    private String toJsonString(Object object) {
        return IceJsonStringUtils.toJsonString(object);
    }

    private String toJsonString(Object object, String dateFormate) {
        return IceJsonStringUtils.toJsonString(object, dateFormate);
    }

    private String toJsonStringNoNull(Object object) {
        return IceJsonStringUtils.toJsonStringNoNull(object);
    }

    private String toJsonStringNoNull(Object object, String dateFormate) {
        return IceJsonStringUtils.toJsonStringNoNull(object, dateFormate);
    }

    private String toJsonStringByFilter(Object object,
                                        SimplePropertyPreFilter filter) {
        return IceJsonStringUtils.toJsonStringByFilter(object, filter);
    }

    private String toJsonStringByFilter(Object object,
                                        SimplePropertyPreFilter filter, String dateFormate) {
        return IceJsonStringUtils.toJsonStringByFilter(object, filter,
                dateFormate);
    }
}
