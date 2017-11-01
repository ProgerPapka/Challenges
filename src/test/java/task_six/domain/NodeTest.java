package task_six.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NodeTest {

    private static List<Node> list;

    @BeforeClass
    public static void initData() {
        Node firstWithValue1 = new Node(1);
        firstWithValue1.addChildren(new Node(3));
        list = Arrays.asList(firstWithValue1, new Node(2));
    }

    @Test
    public void isLeaf() throws Exception {
        assertEquals(true, list.get(0).getChildren().get(0).isLeaf());
        assertEquals(true, list.get(1).isLeaf());
    }

    @Test
    public void isBranch() throws Exception {
        assertEquals(true,list.get(0).isBranch());
    }

}