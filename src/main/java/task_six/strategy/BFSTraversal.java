package task_six.strategy;

import task_six.domain.Node;

import java.util.List;

public class BFSTraversal extends TreeTraversal {

    @Override
    protected void fillListTraversal(List<Node> children, List<Node> listTraversal) {
        if (children != null) {
            listTraversal.addAll(children);
            for (Node node : children)
                fillListTraversal(node.getChildren(), listTraversal);
        }
    }
}
