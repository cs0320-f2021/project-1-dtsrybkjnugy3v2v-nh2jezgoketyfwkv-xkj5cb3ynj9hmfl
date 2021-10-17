package java.edu.brown.cs.student.kdtree;

import edu.brown.cs.student.kdtree.KDNode;
import edu.brown.cs.student.stars.Star;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class KDTreeNodeTest {

  @Test
  /**
   * tests the getDepth and increaseDepth methods in KDNode Class
   */
  public void testDepth() throws IllegalAccessException {
    Star testStar = new Star("0","Sun","2","3","4.3");
    KDNode testNode = new KDNode(testStar);
    assertSame(testNode.getDepth(), 0);
    testNode.increaseDepth();
    assertSame(testNode.getDepth(), 1);
  }

  @Test
  /**
   * test the IsLeaf, addRightChild, and addLeftChild method of KDNode
   */
  public void testChildren() throws IllegalAccessException {
    Star testStar = new Star("0","Sun","2.1","3.0","4.3");
    Star testStar2 = new Star("1","Mun","3.1","10.0","4.3");
    KDNode testNode = new KDNode(testStar);
    KDNode testNode2 = new KDNode(testStar2);
    assertTrue(testNode.isLeaf());
    testNode.addLeftChild(testNode2);
    assertTrue(!testNode.isLeaf());
    testNode.addRightChild(testNode2);
    assertTrue(!testNode.isLeaf());
    assertSame(testNode.leftChild,testNode2);
    assertSame(testNode.rightChild,testNode2);

  }
  @Test
  /**
   * tests the getNumParams method of KDNode class
   */
  public void testGetNumParams() throws IllegalAccessException {
    Star testStar = new Star("0","Sun","2.1","3.0","4.3");
    KDNode testNode = new KDNode(testStar);
    ArrayList<Double> returnList = testNode.getNumParams();
    ArrayList<Double> testList = new ArrayList<>();
    testList.add(2.1);
    testList.add(3.0);
    testList.add(4.3);
    assertTrue(returnList.equals(testList));
  }


}
