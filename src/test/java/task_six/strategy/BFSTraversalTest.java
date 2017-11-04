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

public class BFSTraversalTest {

    private List<Integer> valuesBFSTree;

    @Before
    public void initData(){
        valuesBFSTree = new ArrayList<>();
        valuesBFSTree.add(1);
        valuesBFSTree.add(2);
        valuesBFSTree.add(3);
        valuesBFSTree.add(4);
        valuesBFSTree.add(5);
        valuesBFSTree.add(6);
        valuesBFSTree.add(7);
    }

    @Test
    public void setBackwardTraversal() throws Exception {
        Collections.reverse(valuesBFSTree);
        Tree tree = CreationTree.createTwoOnThreeTree();
        tree.setStrategy(new BFSTraversal());
        tree.setBackwardTraversal(true);
        List<Integer> valuesTree = new ArrayList<>();
        for (Node node : tree){
            valuesTree.add(node.getValue());
        }
        assertEquals(valuesBFSTree,valuesTree);
    }

    @Test
    public void iterator() throws Exception {
        Tree tree = CreationTree.createTwoOnThreeTree();
        tree.setStrategy(new BFSTraversal());
        List<Integer> valuesTree = new ArrayList<>();
        for (Node node : tree){
            valuesTree.add(node.getValue());
        }
        assertEquals(valuesBFSTree,valuesTree);
    }

}