package com.baizhi.service;

import com.baizhi.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    List<Category> queryByLevels(Integer levels);

    List<Category> queryByParentid(String parentid);

    void addCategory(Category category);

    Map<String, String> deleteCategory(String id);

    //修改二级类别名
    void updateCategory(Category category);
}
