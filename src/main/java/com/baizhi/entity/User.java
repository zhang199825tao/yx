package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "姓名")
    private String username;
    @Excel(name = "手机号")
    private String phone;
    @Excel(name = "头像",type = 2)
    private String headimg;
    @Excel(name = "描述")
    private String brief;
    @Excel(name = "微信")
    private String wechat;
    @Excel(name = "出生年月日",format = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creatdate;
    @Excel(name = "用户状态")
    private int status;



}
