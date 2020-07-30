package com.design.pattern.composite;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/30
 */

/**
 *@ClassName LeafNode
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/30 15:35
 *@Version 1.0
 **/
public class LeafNode implements TreeNode{

    private String name;

    public LeafNode(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
