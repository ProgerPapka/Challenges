package task_six.strategy;

import task_six.domain.Node;
import task_six.domain.Tree;
import task_six.exception.ExceptionOnATree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class TreeTraversal implements Iterable<Node> {

    private List<Node> listTraversal = new ArrayList<>();
    private Tree tree;
    private boolean backwardTraversal = false;
    private boolean lastState = false;
    private boolean reverse = false;

    public TreeTraversal(Tree tree) throws ExceptionOnATree {
        if (tree == null) {
            throw new ExceptionOnATree("Tree is null!");
        }
        this.tree = tree;
        indexing();
    }

    public void setTree(Tree tree) {
        this.tree = tree;
        this.lastState = false;
        this.backwardTraversal = false;
    }

    public void setBackwardTraversal(boolean backwardTraversal) {
        this.lastState = this.backwardTraversal;
        this.backwardTraversal = backwardTraversal;
        if (backwardTraversal != lastState) {
            Collections.reverse(listTraversal);
        }
    }

    @Override
    public Iterator<Node> iterator() {
        return listTraversal.iterator();
    }

    private void indexing() {
        listTraversal.add(tree.getRootNode());
        fillListTraversal(tree.getRootNode().getChildren(), listTraversal);
    }

    protected abstract void fillListTraversal
            (List<Node> children, List<Node> listTraversal);

}
