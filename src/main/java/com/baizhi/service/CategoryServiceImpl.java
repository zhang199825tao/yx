package com.baizhi.service;
import com.baizhi.annotation.CherryAnnotation;
import com.baizhi.dao.CategoryDao;
import com.baizhi.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryByLevels(Integer levels) {
        return categoryDao.queryByLevels(levels);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryByParentid(String parentid) {
        return categoryDao.queryByParentid(parentid);
    }

    @CherryAnnotation
    @Override
    public void addCategory(Category category) {
        category.setId(UUID.randomUUID().toString());
        categoryDao.addCategory(category);
    }

    @CherryAnnotation
    @Override
    public Map<String, String> deleteCategory(String id) {
        List<Category> categories = categoryDao.queryByParentid(id);
        Map<String, String> map = new HashMap<>();
        if (categories.isEmpty()){
            categoryDao.deleteCategory(id);
            map.put("msg", "删除成功");
        }else{
            map.put( "msg","这个下边有东西，严禁删除");
        }
        return map;
    }

    @CherryAnnotation
    @Override
    public void updateCategory(Category category) {
        Category category1 = categoryDao.selectCategory(category.getId());
        category1.setCate_name(category.getCate_name());
        categoryDao.updateCategory(category1);
    }

}
