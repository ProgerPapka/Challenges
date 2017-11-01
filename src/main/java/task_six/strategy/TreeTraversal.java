package task_six.strategy;

import task_six.domain.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class TreeTraversal {

    private List<Node> listTraversal;
    private Node rootNode;
    private boolean backwardTraversal = false;
    private boolean lastState = false;

    public TreeTraversal() {
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
        indexing();
        this.lastState = false;
        this.backwardTraversal = false;
    }

    public void setBackwardTraversal(boolean backwardTraversal) {
        if (listTraversal == null)
            return;
        this.lastState = this.backwardTraversal;
        this.backwardTraversal = backwardTraversal;
        if (backwardTraversal != lastState) {
            Collections.reverse(listTraversal);
        }
    }

    public Iterator<Node> iterator() {
        return listTraversal.iterator();
    }

    private void indexing() {
        listTraversal = new ArrayList<>();
        listTraversal.add(rootNode);
        fillListTraversal(rootNode.getChildren(), listTraversal);
    }

    protected abstract void fillListTraversal
            (List<Node> children, List<Node> listTraversal);

}
