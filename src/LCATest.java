import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class LCATest {
    @Test
    public void testConstructor()
    {
        new LCA.BinaryTree();
        new LCA.Node(1);
    }

    @Test
    public void testAdd()
    {
        LCA.BinaryTree tree = new LCA.BinaryTree();

        tree.add(40);
        int expectedResult = 40;
        assertEquals("Test passed successfully by adding 40 to the tree.", expectedResult, tree.root.data);

        tree.add(20);
        expectedResult = 20;
        assertEquals("Test passed successfully by adding 20 to the tree.", expectedResult, tree.root.left.data);
    }

    @Test
    public void testContainsNode(){
        LCA.BinaryTree tree = new LCA.BinaryTree();
        tree.add(40);
        tree.add(20);

        LCA.Node expectedResult = tree.root;
        assertEquals("Test passed successfully by confirming that 40 is in the tree", expectedResult, tree.containsNode(40));

        expectedResult = tree.root.left;
        assertEquals("Test passed successfully by confirming that 20 is in the tree", expectedResult, tree.containsNode(20));

    }

    @Test
    public void testLCA(){
        LCA.BinaryTree tree = new LCA.BinaryTree();
        tree.add(40);
        tree.add(20);
        tree.add(10);
        tree.add(30);
        tree.add(60);
        tree.add(50);
        tree.add(70);
        tree.add(5);
        tree.add(45);
        tree.add(55);

        /*
                    40
                 /      \
                20       60
               /  \     /  \
              10   30  50   70
             /          \
            5            55
         */

        int expectedResult = 20;
        assertEquals("Test passed successfully as the Lowest Common Ancestor of 5 and 30 is 20.",
                expectedResult, tree.lowestCommonAncestor(tree.root, tree.containsNode(5), tree.containsNode(30)).data);

        expectedResult = 60;
        assertEquals("Test passed successfully as the Lowest Common Ancestor of 5 and 30 is 20.",
                expectedResult, tree.lowestCommonAncestor(tree.root, tree.containsNode(55), tree.containsNode(70)).data);

        assertNull("Test passed successfully as the Lowest Common Ancestor of 55 and 2 does not exist.",
                tree.lowestCommonAncestor(tree.root, tree.containsNode(55), tree.containsNode(2)));

        assertNull("Test passed successfully as the Lowest Common Ancestor of 1 and 70 does not exist.",
                tree.lowestCommonAncestor(tree.root, tree.containsNode(1), tree.containsNode(70)));

        assertNull("Test passed successfully as the Lowest Common Ancestor of 1 and 2 does not exist.",
                tree.lowestCommonAncestor(tree.root, tree.containsNode(1), tree.containsNode(2)));
    }
}
