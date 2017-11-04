package task_six.domain;

import org.apache.log4j.Logger;
import task_six.exception.ExceptionOnATree;
import task_six.strategy.BFSTraversal;
import task_six.strategy.TreeTraversal;

import java.util.Iterator;

public class Tree implements Iterable<Node> {

    private static Logger logger = Logger.getLogger(Tree.class);

    private final Node rootNode;
    private TreeTraversal strategy;

    public Tree(Node rootNode) throws ExceptionOnATree {
        this(rootNode, new BFSTraversal());
    }

    public Tree(Node rootNode, TreeTraversal strategy) throws ExceptionOnATree {
        this.rootNode = rootNode;
        this.strategy = strategy;
        strategy.setRootNode(rootNode);
    }

    public void setStrategy(TreeTraversal strategy) {
        this.strategy = strategy;
        try {
            strategy.setRootNode(rootNode);
        } catch (ExceptionOnATree exceptionOnATree) {
            logger.error(exceptionOnATree);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tree tree = (Tree) o;

        return rootNode.equals(tree.rootNode);
    }

    @Override
    public int hashCode() {
        return rootNode.hashCode();
    }
}
