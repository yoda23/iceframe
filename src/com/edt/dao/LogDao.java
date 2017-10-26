package com.edt.dao;

import com.edt.entity.Log;

public interface LogDao {
    /**
     * 保存日志
     *
     * @author 刘钢
     * 2017/5/17 22:32
     */
    void insertLog(Log log);
}
