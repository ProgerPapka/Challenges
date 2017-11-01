package task_six.strategy;

import org.junit.Before;
import org.junit.Test;
import task_six.CreationTree;
import task_six.domain.Node;
import task_six.domain.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DFSTraversalTest {

    private List<Integer> valuesDFSTree;

    @Before
    public void initData(){
        valuesDFSTree = new ArrayList<>();
        valuesDFSTree.add(1);
        valuesDFSTree.add(2);
        valuesDFSTree.add(4);
        valuesDFSTree.add(5);
        valuesDFSTree.add(3);
        valuesDFSTree.add(6);
        valuesDFSTree.add(7);
    }

    @Test
    public void setBackwardTraversal() throws Exception {
        Collections.reverse(valuesDFSTree);
        Tree tree = CreationTree.createTwoOnThreeTree();
        tree.setStrategy(new DFSTraversal());
        tree.setBackwardTraversal(false);
        tree.setBackwardTraversal(true);
        List<Integer> valuesTree = new ArrayList<>();
        for (Node node : tree){
            valuesTree.add(node.getValue());
        }
        assertEquals(valuesDFSTree,valuesTree);
    }

    @Test
    public void iterator() throws Exception {
        List<Integer> valuesTree = new ArrayList<>();
        Tree tree = CreationTree.createTwoOnThreeTree();
        tree.setStrategy(new DFSTraversal());
        for (Node node : tree){
            valuesTree.add(node.getValue());
        }
        assertEquals(valuesDFSTree,valuesTree);
    }

}