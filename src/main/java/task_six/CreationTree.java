package task_six;

import task_six.domain.Node;
import task_six.domain.Tree;
import task_six.exception.ExceptionOnATree;

import java.util.Random;

public class CreationTree {

    private static Random random = new Random();

    public static Tree createRandomTree(int maxDepth, int maxBreadth) {
        Node rootNode = new Node(null, random.nextInt());
        int depth = random.nextInt(maxDepth) + 1;
        int currentDepth = 1;
        fillRandomChildren(maxBreadth, rootNode, currentDepth, depth);
        try {
            return new Tree(rootNode);
        } catch (ExceptionOnATree e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void fillRandomChildren
            (int maxBreadth, Node parent, int currentDepth, int depth) {
        if (currentDepth != depth) {
            currentDepth++;
            int breadth = random.nextInt(maxBreadth) + 1;
            for (int i = 0; i < breadth; i++) {
                new Node(parent, random.nextInt());
            }
            for(Node node : parent.getChildren()){
                fillRandomChildren(maxBreadth,node,currentDepth,depth);
            }
        }
    }

    public static Tree createTwoOnThreeTree(){
        Node rootNode = new Node(null,1);
        Node firstChild = new Node(rootNode,2);
        Node secondChild = new Node(rootNode,3);

        Node firstLeftLeaf = new Node(firstChild,4);
        Node secondLeftLeaf = new Node(firstChild,5);

        Node firstRightLeaf = new Node(secondChild,6);
        Node secondRightLeaf = new Node(secondChild,7);

        try {
            return new Tree(rootNode);
        } catch (ExceptionOnATree e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
