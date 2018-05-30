package com.lw.share.entity;/**
 * Created by liuchen on 2018/5/30.
 */

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 刘晨
 * @create 2018-05-30 10:22
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Getter
@Setter
@TableName("base_resources")
public class BaseResources extends Model<BaseClass> {

    private static final long serialVersionUID = 5477750803810158136L;

    @TableId
    private String id;

    @TableId("class_id")
    private String classId;

    @TableId("class_name")
    private String className;

    @TableId("url")
    private String url;

    @TableId("res_name")
    private String resName;

    @TableId("res_author")
    private String resAuthor;

    @TableId("key_word01")
    private String keyWord01;

    @TableId("key_word02")
    private String keyWord02;

    @TableId("key_word03")
    private String keyWord03;

    private String memo;

    @TableId("top_order")
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
