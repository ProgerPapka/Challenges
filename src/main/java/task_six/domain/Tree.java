package task_six.domain;

import task_six.strategy.BFSTraversal;
import task_six.strategy.TreeTraversal;

import java.util.Iterator;

public class Tree implements Iterable<Node> {

    private final Node rootNode;
    private TreeTraversal strategy;

    public Tree(Node rootNode) {
        this.rootNode = rootNode;
        this.strategy = new BFSTraversal();
        strategy.setRootNode(rootNode);
    }

    public Tree(Node rootNode, TreeTraversal strategy) {
        this.rootNode = rootNode;
        this.strategy = strategy;
        strategy.setRootNode(rootNode);
    }

    public void setStrategy(TreeTraversal strategy) {
        this.strategy = strategy;
        strategy.setRootNode(rootNode);
    }

    public void setBackwardTraversal(boolean backwardTraversal) {
        strategy.setBackwardTraversal(backwardTraversal);
    }

    public Node getRootNode() {
        return rootNode;
    }

    @Override
    public Iterator<Node> iterator() {
        return strategy.iterator();
    }

}
