package com.design.pattern.composite;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/30
 */

import java.util.ArrayList;
import java.util.List;

/**
 *@ClassName BranchNode
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/30 15:37
 *@Version 1.0
 **/
public class BranchNode implements TreeNode {

    private String name;

    private List<TreeNode> nodeList;

    public BranchNode(String name) {
        this.name = name;
    }

    public BranchNode(String name, List<TreeNode> nodeList) {
        this.name = name;
        this.nodeList = nodeList;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<TreeNode> nodeList) {
        this.nodeList = nodeList;
    }

    public void addNode(TreeNode node) {
        if(nodeList == null) {
            this.nodeList = new ArrayList<>();
        }
        nodeList.add(node);
    }
}
