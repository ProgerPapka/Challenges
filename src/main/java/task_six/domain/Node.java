package task_six.domain;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private List<Node> children;
    private int value;

    public Node(int value) {
        this(value, new ArrayList<>());
    }

    public Node(int value, List<Node> children) {
        this.value = value;
        this.children = children;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (value != node.value) return false;
        return children.equals(node.children);
    }

    @Override
    public int hashCode() {
        int result = children.hashCode();
        result = 31 * result + value;
        return result;
    }
}
