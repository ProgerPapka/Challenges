package task_six.domain;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private Node parent;
    private List<Node> children;
    private int value;

    private Node() {
        children = new ArrayList<>();
    }

    public Node(int value) {
        this();
        this.value = value;
    }

    public Node(Node parent, int value) {
        this(value);
        this.parent = parent;
    }

    public Node(Node parent, int value, List<Node> children) {
        this.parent = parent;
        this.value = value;
        this.children = children;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void addChildren(Node childNode) {
        children.add(childNode);
    }


    public boolean isLeaf() {
        return children == null || children.isEmpty();
    }

    public boolean isBranch() {
        return !isLeaf();
    }

    public void printInfo() {
        if (isLeaf()) {
            System.out.println("This node is leaf, value " + value);
        } else {
            System.out.println("This node is branch, value " + value);
        }
    }
}
