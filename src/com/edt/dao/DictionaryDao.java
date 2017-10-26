package com.edt.dao;

import com.edt.entity.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DictionaryDao {
    /**
     * 获取数据字典内容
     *
     * @return java.util.List<com.edt.entity.Dictionary>
     * @author 刘钢
     * 2017/5/17 22:32
     */
    public List<Dictionary> getDictionary();
}
