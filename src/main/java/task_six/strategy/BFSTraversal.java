package task_six.strategy;

import task_six.domain.Node;

import java.util.Iterator;
import java.util.List;


public class BFSTraversal extends TreeTraversal {

    public BFSTraversal() {
        super();
    }

    @Override
    public void setRootNode(Node rootNode) {
        super.setRootNode(rootNode);
    }

    @Override
    public void setBackwardTraversal(boolean backwardTraversal) {
        super.setBackwardTraversal(backwardTraversal);
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
