package task_six.visitor;

import org.apache.log4j.Logger;
import task_six.domain.Node;
import task_six.domain.Tree;

public class PrinterElement implements Visitor {

    private static Logger logger = Logger.getLogger(PrinterElement.class);

    @Override
    public void visitNode(Node node) {
        if (node.isLeaf()) {
            logger.info("This node is leaf, value " + node.getValue());
        } else {
            logger.info("This node is branch, value " + node.getValue());
        }
    }

}
