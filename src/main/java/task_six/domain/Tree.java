package task_six.domain;

import task_six.exception.ExceptionOnATree;

public class Tree {

    private final Node rootNode;

    public Tree(Node rootNode) throws ExceptionOnATree {
        if (rootNode == null || rootNode.getParent() != null ){
            throw new ExceptionOnATree("Do not grease the object!" +
                    " RootNode == null or he has a parent.");
        }
            this.rootNode = rootNode;
    }

    public Node getRootNode() {
        return rootNode;
    }
}
