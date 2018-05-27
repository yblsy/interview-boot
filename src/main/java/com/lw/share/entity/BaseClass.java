package com.lw.share.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lw.share.commons.annotation.TreeLabel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 刘晨
 * @create 2018-05-06 21:38
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Getter
@Setter
@TableName("base_class")
@TreeLabel(text = "name")
public class BaseClass extends Model<BaseClass> {

    private static final long serialVersionUID = -1064080574401025929L;

    @TableId
    private String id;

    private String name;

    @TableField("parent_id")
    private String parentId;

    @TableField("parent_name")
    private String parentName;

    @TableField("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    private String level;

    private String url;

    @TableField("child_num")
    private Integer childNum;
    
    private String memo;

    private String icon;

    @TableField("selected_icon")
    private String selectedIcon;

    private Integer sort;

    @TableField("top_order")
    private Integer topOrder;

    @TableField("create_user")
    private String createUser;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_user")
    private String updateUser;

    @TableField("update_time")
    private Date updateTime;

    @TableField("is_delete")
    private Integer isDelete;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
