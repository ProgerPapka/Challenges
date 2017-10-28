package task_six;

import task_six.domain.Node;
import task_six.strategy.TreeTraversal;

public class Consumer {

    public void printTreeNodes(TreeTraversal traversal){
        for(Node node : traversal){
            System.out.println(node.info());
        }
    }

}
