package task_six;

import org.apache.log4j.Logger;
import task_six.domain.Node;
import task_six.domain.Tree;


public class Consumer {

    private static Logger logger = Logger.getLogger(Consumer.class);

    public void printTreeNodes(Tree tree) {
        for (Node node : tree) {
            if (node.isLeaf()) {
                logger.info("This node is leaf, value " + node.getValue());
            } else {
                logger.info("This node is branch, value " + node.getValue());
            }
        }
    }

}
