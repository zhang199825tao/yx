package com.baizhi.controller;

import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("queryByLevels")
    public List<Category> queryByLevels(Integer levels){

        return categoryService.queryByLevels(levels);
    }

    @RequestMapping("queryByParentId")
    public List<Category> queryByParentId(String id) {
        return categoryService.queryByParentid(id);
    }

    @RequestMapping("save")
    public void save(@RequestBody Category category){
        categoryService.addCategory(category);
    }


    @RequestMapping("delete")
    public Map<String, String> delete(String id){
        Map<String, String> map = categoryService.deleteCategory(id);
        return  map;
    }


    @RequestMapping("update")
    public void update(@RequestBody Category category){
        categoryService.updateCategory(category);
    }

}
