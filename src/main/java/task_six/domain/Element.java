package task_six.domain;

import task_six.visitor.Visitor;

public interface Element {
    void accept(Visitor visitor);
}
