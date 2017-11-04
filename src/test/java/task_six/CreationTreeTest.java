package task_six;

import org.junit.BeforeClass;
import org.junit.Test;
import task_six.domain.Node;
import task_six.domain.Tree;
import task_six.exception.ExceptionOnATree;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CreationTreeTest {

    private static Tree standardTree;
    private static int maxCountNodes;

    @BeforeClass
    public static void initData() throws ExceptionOnATree {
        maxCountNodes = 4;
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        root.setChildren(Arrays.asList(child1, child2));
        child1.setChildren(Arrays.asList(new Node(4),
                new Node(5)));
        child2.setChildren(Arrays.asList(new Node(6),
                new Node(7)));
        standardTree = new Tree(root);
    }

    @Test
    public void createRandomTree() throws Exception {
        Tree tree = CreationTree.createRandomTree(2, 2);
        int count = 0;
        for (Node node : tree) {
            count++;
        }
        assertEquals(true, count <= maxCountNodes && count > 0);
    }

    @Test
    public void createTwoOnThreeTree() throws Exception {
        Tree tree = CreationTree.createTwoOnThreeTree();
        assertEquals(standardTree, tree);
    }

}