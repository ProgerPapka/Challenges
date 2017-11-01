package task_six;

import org.apache.log4j.Logger;
import task_six.domain.Tree;
import task_six.strategy.DFSTraversal;

public class Test {

    private static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        Tree standardTree = CreationTree.createTwoOnThreeTree();
        Consumer consumer = new Consumer();
        logger.info("Print nodes of standard tree with BFS strategy:");
        consumer.printTreeNodes(standardTree);
        standardTree.setBackwardTraversal(true);
        logger.info("Print nodes of standard tree in backward " +
                "traversal with BFS strategy:");
        consumer.printTreeNodes(standardTree);

        standardTree.setStrategy(new DFSTraversal());
        logger.info("Print nodes of standard tree with DFS strategy:");
        consumer.printTreeNodes(standardTree);
        standardTree.setBackwardTraversal(true);
        logger.info("Print nodes of standard tree in backward " +
                "traversal with DFS strategy:");
        consumer.printTreeNodes(standardTree);

        Tree randomTree = CreationTree.createRandomTree(3, 3);
        logger.info("Print nodes of random tree with BFS strategy:");
        consumer.printTreeNodes(randomTree);
        randomTree.setBackwardTraversal(false);
        randomTree.setBackwardTraversal(true);
        logger.info("Print nodes of random tree in backward " +
                "traversal with BFS strategy:");
        consumer.printTreeNodes(randomTree);

        randomTree.setStrategy(new DFSTraversal());
        logger.info("Print nodes of random tree with DFS strategy:");
        consumer.printTreeNodes(randomTree);
        randomTree.setBackwardTraversal(false);
        randomTree.setBackwardTraversal(true);
        logger.info("Print nodes of random tree in backward " +
                "traversal with DFS strategy:");
        consumer.printTreeNodes(randomTree);

    }

}
