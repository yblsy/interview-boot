package com.lw.share.commons.model;

import com.google.common.base.Strings;
import com.lw.share.commons.annotation.TreeLabel;
import lombok.Getter;
import lombok.Setter;
import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * @author 刘晨
 * @create 2018-05-19 21:34
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Getter
@Setter
public class TreeModel<T> {

    public TreeModel(){

    }

    private String id;

    private String parentId;

    private String text;

    private T t;

    private List<TreeModel<T>> children;

    public static <T> TreeModel<T> getChildByT(T target){
        Class clazz = target.getClass();
        TreeModel<T> treeModel = new TreeModel<>();
        if(clazz.isAnnotationPresent(TreeLabel.class)){
            //获取头部注解
            TreeLabel treeLabel = (TreeLabel) clazz.getAnnotation(TreeLabel.class);
            try{
                //拿取id,parentId,text
                Field idF = clazz.getDeclaredField(treeLabel.id());
                idF.setAccessible(true);
                treeModel.setId(idF.get(target).toString());
                Field parentIdF = clazz.getDeclaredField(treeLabel.parentId());
                parentIdF.setAccessible(true);
                //设置parent
                if(!Objects.isNull(parentIdF.get(target))){
                    treeModel.setParentId(parentIdF.get(target).toString());
                }
                //设置text
                if(!Strings.isNullOrEmpty(treeLabel.text())){
                    Field textF = clazz.getDeclaredField(treeLabel.text());
                    textF.setAccessible(true);
                    treeModel.setText(textF.get(target).toString());
                }
                treeModel.setT(target);
            }catch (NoSuchFieldException e){
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return treeModel;
    }
}
