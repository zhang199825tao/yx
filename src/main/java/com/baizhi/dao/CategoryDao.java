package com.baizhi.dao;

import com.baizhi.entity.Category;

import java.util.List;

public interface CategoryDao {

    //查询所有一级类别
    List<Category> queryByLevels(Integer levels);

    //根据以及类别查询所有子类别
    List<Category> queryByParentid(String parentid);

    //添加二级类别
    void addCategory(Category category);

    //删除类别
    void deleteCategory(String id);

    //修改二级类别名
    void updateCategory(Category category);

    //根据id查询类别详细
    Category selectCategory(String id);
}
