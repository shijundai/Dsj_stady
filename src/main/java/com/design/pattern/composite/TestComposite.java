package com.design.pattern.composite;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/30
 */

/**
 *@ClassName TestComposite
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/30 15:40
 *@Version 1.0
 **/
public class TestComposite {

    public static void main(String []args) {
        BranchNode root = new BranchNode("root");
        BranchNode leve1_1 = new BranchNode("湖南省");
        LeafNode cs = new LeafNode("长沙");
        BranchNode zz = new BranchNode("株洲");
        LeafNode yl = new LeafNode("炎陵");
        LeafNode cl = new LeafNode("茶陵");
        LeafNode xt = new LeafNode("湘潭");

        BranchNode leve1_2 = new BranchNode("湖北省");
        LeafNode wh = new LeafNode("武汉");
        LeafNode jz = new LeafNode("荆州");
        LeafNode xg = new LeafNode("孝感");
        BranchNode level_3 = new BranchNode("江苏省");

        LeafNode level_4 = new LeafNode("北京市");
        LeafNode level_5 = new LeafNode("天津市");
        LeafNode level_6 = new LeafNode("重庆市");

        root.addNode(leve1_1);
        root.addNode(leve1_2);
        root.addNode(level_3);
        root.addNode(level_4);
        root.addNode(level_5);
        root.addNode(level_6);

        leve1_1.addNode(cs);
        leve1_1.addNode(zz);
        leve1_1.addNode(xt);
        zz.addNode(yl);
        zz.addNode(cl);

        leve1_2.addNode(wh);
        leve1_2.addNode(jz);
        leve1_2.addNode(xg);

        printTree(root,0);

    }

    public static void printTree(TreeNode node,int level) {
        for(int i=0;i<level;i++) {
            System.out.print("    ");
        }
        System.out.println(node.getName());
        if(node instanceof BranchNode) {
            BranchNode branch = (BranchNode) node;
            if(branch.getNodeList() != null) {
                branch.getNodeList().forEach(o -> printTree(o,level+1));
            }
        }
    }

}
