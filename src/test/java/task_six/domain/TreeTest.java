package task_six.domain;

import org.junit.Before;
import org.junit.Test;
import task_six.CreationTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TreeTest {

    private List<Integer> treeValues;

    @Before
    public void initValues(){
        treeValues = new ArrayList<>();
        treeValues.add(1);
        treeValues.add(2);
        treeValues.add(3);
        treeValues.add(4);
        treeValues.add(5);
        treeValues.add(6);
        treeValues.add(7);
    }

    @Test
    public void setBackwardTraversal() throws Exception {
        Collections.reverse(treeValues);
        List<Integer> values = new ArrayList<>();
        Tree tree = CreationTree.createTwoOnThreeTree();
        tree.setBackwardTraversal(true);
        for (Node node : tree){
            values.add(node.getValue());
        }
        assertEquals(treeValues,values);
    }

    @Test
    public void iterator() throws Exception {
        List<Integer> values = new ArrayList<>();
        Tree tree = CreationTree.createTwoOnThreeTree();
        for (Node node : tree){
            values.add(node.getValue());
        }
        assertEquals(values,treeValues);
    }

}