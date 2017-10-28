package task_six.strategy;

import task_six.domain.Node;
import task_six.domain.Tree;
import task_six.exception.ExceptionOnATree;

import java.util.*;

public class BFSTraversal extends TreeTraversal {

    public BFSTraversal(Tree tree) throws ExceptionOnATree {
        super(tree);
    }

    @Override
    public Iterator<Node> iterator() {
        return super.iterator();
    }

    @Override
    protected void fillListTraversal(List<Node> children, List<Node> listTraversal) {
        if (children != null) {
            listTraversal.addAll(children);
            for (Node node : children)
                fillListTraversal(node.getChildren(), listTraversal);
        }
    }
}
