package task_six.strategy;

import task_six.domain.Node;

import java.util.List;

public class DFSTraversal extends TreeTraversal {

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
