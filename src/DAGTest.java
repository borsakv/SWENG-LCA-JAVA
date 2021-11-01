import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class DAGTest {
    @Test
    public void testConstructor(){
        new DAG();
    }

    @Test
    public void testAddNode(){
        DAG test = new DAG();
        test.createNode(7);

        int expectedResult = 7;
        assertEquals("Test passed successfully by adding 7 to the DAG.", expectedResult, test.getNode(7).getData());
    }

    @Test
    public void testAddEdge(){
        DAG test = new DAG();
        test.addEdge(7, 6);

        int expectedResult = 7;
        assertEquals("Test passed successfully by adding 7 to the DAG.", expectedResult, test.getNode(7).getData());
        assertEquals("Test passed successfully by adding 7 to the DAG.", expectedResult, test.getNode(6).getParents().get(0).getData());

        expectedResult = 6;
        assertEquals("Test passed successfully by adding 6 to the DAG.", expectedResult, test.getNode(6).getData());
        assertEquals("Test passed successfully by adding 6 to the DAG.", expectedResult, test.getNode(7).getChildren().get(0).getData());
    }

    @Test
    public void testGetParent(){
        DAG test = new DAG();
        test.addEdge(7, 6);
        test.addEdge(8, 6);

        int expectedResult = 7;
        assertEquals("Test passed successfully by adding 7 as a parent of 6.", expectedResult, test.getNode(6).getParents().get(0).getData());

        expectedResult = 8;
        assertEquals("Test passed successfully by adding 8 as a parent of 6.", expectedResult, test.getNode(6).getParents().get(1).getData());
    }

    @Test
    public void testGetChild(){
        DAG test = new DAG();
        test.addEdge(7, 6);
        test.addEdge(8, 6);
        test.update();

        int expectedResult = 6;
        assertEquals("Test passed successfully by adding 6 as a child of 7.", expectedResult, test.getNode(7).getChildren().get(0).getData());

        expectedResult = 6;
        assertEquals("Test passed successfully by adding 6 as a child of 8.", expectedResult, test.getNode(8).getChildren().get(0).getData());
    }

    @Test
    public void testLCA(){
        DAG test = new DAG();
        test.addEdge(10, 5);
        test.addEdge(20, 10);
        test.addEdge(20, 30);
        test.addEdge(50, 55);
        test.addEdge(60, 50);
        test.addEdge(60, 70);
        test.addEdge(50, 30);
        test.addEdge(85, 50);
        test.addEdge(85, 70);
        test.update();

        /*
                20        60,85
               /  \       /  \
              10   30 <- 50   70
             /            \
            5              55
         */

        int expectedResult = 20;
        assertEquals("Test passed successfully as the lowest common ancestor of 5 and 30, is 20", expectedResult, test.lowestCommonAncestor(5, 30).get(0));

        expectedResult = 50;
        assertEquals("Test passed successfully as the lowest common ancestor of 30 and 55, is 50", expectedResult, test.lowestCommonAncestor(30, 55).get(0));

        expectedResult = 60;
        assertEquals("Test passed successfully as the first lowest common ancestor of 50 and 70 is 60", expectedResult, test.lowestCommonAncestor(50, 70).get(0));
        expectedResult = 85;
        assertEquals("Test passed successfully as the second lowest common ancestor of 50 and 70 is 85", expectedResult, test.lowestCommonAncestor(50, 70).get(1));

        ArrayList<Integer> newExpectedResult = new ArrayList<>();
        assertEquals("Test passed successfully as there is no common ancestor between 3 and 5.", newExpectedResult, test.lowestCommonAncestor(3, 5));

        assertEquals("Test passed successfully as there is no common ancestor between 30 and 12.", newExpectedResult, test.lowestCommonAncestor(30, 12));

        assertEquals("Test passed successfully as there is no common ancestor between 3 and 12.", newExpectedResult, test.lowestCommonAncestor(3, 12));
    }
}
