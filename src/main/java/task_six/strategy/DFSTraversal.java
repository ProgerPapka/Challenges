package task_six.strategy;

import task_six.domain.Node;
import task_six.domain.Tree;
import task_six.exception.ExceptionOnATree;

import java.util.Iterator;
import java.util.List;

public class DFSTraversal extends TreeTraversal {

    public DFSTraversal(Tree tree) throws ExceptionOnATree {
        super(tree);
    }

    @Override
    public Iterator<Node> iterator() {
        return super.iterator();
    }

    @Override
    protected void fillListTraversal(List<Node> children, List<Node> listTraversal) {
        if (children != null) {
            for (Node node : children) {
                listTraversal.add(node);
                fillListTraversal(node.getChildren(), listTraversal);
            }
        }
    }
}
