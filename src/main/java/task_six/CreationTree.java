package task_six;

import task_six.domain.Node;
import task_six.domain.Tree;
import task_six.exception.ExceptionOnATree;

import java.util.Arrays;
import java.util.Random;

public class CreationTree {

    private static Random random = new Random();

    public static Tree createRandomTree(int maxDepth, int maxBreadth) throws ExceptionOnATree {
        Node rootNode = new Node(random.nextInt());
        int depth = random.nextInt(maxDepth) + 1;
        int currentDepth = 1;
        fillRandomChildren(maxBreadth, rootNode, currentDepth, depth);
        return new Tree(rootNode);
    }

    private static void fillRandomChildren
            (int maxBreadth, Node parent, int currentDepth, int depth) {
        if (currentDepth != depth) {
            currentDepth++;
            int breadth = random.nextInt(maxBreadth) + 1;
            for (int i = 0; i < breadth; i++) {
                parent.addChildren(new Node(random.nextInt()));
            }
            for (Node node : parent.getChildren()) {
                fillRandomChildren(maxBreadth, node, currentDepth, depth);
            }
        }
    }

    public static Tree createTwoOnThreeTree() throws ExceptionOnATree {
        Node rootNode = new Node(1);

        Node firstChild = new Node(2);
        Node secondChild = new Node(3);
        rootNode.setChildren(Arrays.asList(firstChild, secondChild));

        firstChild.setChildren(Arrays.asList(new Node(4), new Node(5)));
        secondChild.setChildren(Arrays.asList(new Node(6), new Node(7)));
        return new Tree(rootNode);
    }

}
