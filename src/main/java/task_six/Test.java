package task_six;

import task_six.domain.Tree;
import task_six.exception.ExceptionOnATree;
import task_six.strategy.BFSTraversal;
import task_six.strategy.DFSTraversal;
import task_six.strategy.TreeTraversal;

public class Test {

    public static void main(String[] args) {
        Tree tree = CreationTree.createTwoOnThreeTree();
        try {
            Consumer consumer = new Consumer();
            TreeTraversal traversal = new BFSTraversal(tree);
            traversal.setBackwardTraversal(true);
            TreeTraversal t = new DFSTraversal(tree);
            traversal.setBackwardTraversal(false);
            consumer.printTreeNodes(traversal);
            traversal.setBackwardTraversal(true);
            System.out.println();
            System.out.println();
            consumer.printTreeNodes(traversal);
            System.out.println();
            System.out.println();
            consumer.printTreeNodes(t);
            System.out.println();
            System.out.println();
            t.setBackwardTraversal(true);
            consumer.printTreeNodes(t);
            System.out.println();
            System.out.println();
            Tree randomTree = CreationTree.createRandomTree(3,3);
            TreeTraversal rt = new BFSTraversal(randomTree);
            consumer.printTreeNodes(rt);

        } catch (ExceptionOnATree e) {
            System.out.println(e.getMessage());
        }

    }

}
