package task_six.visitor;

import task_six.domain.Node;
import task_six.domain.Tree;

public interface Visitor {
    void visitNode(Node node);
    void visitTree(Tree tree);
}
