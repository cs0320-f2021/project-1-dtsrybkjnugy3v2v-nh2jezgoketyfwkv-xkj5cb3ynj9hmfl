package java.edu.brown.cs.student.kdtree;

import static org.junit.Assert.assertEquals;
import edu.brown.cs.student.kdtree.KDCalculator;
import org.junit.Test;

public class KDCalculatorTest {

  /**
   * tests that the distance between two points is calculated correctly
   * in the findDistance method
   */

  @Test
  public void findDistanceTest() {
    KDCalculator KDCalc = new KDCalculator(null);
    // simple tests with simple numbers
    double output = KDCalc.findDistance(1,1,0,0);
    assertEquals(output, 1.414214, 0.01);

    output = KDCalc.findDistance(5, 3, 8, 1);
    assertEquals(output, 3.605551, 0.01);

    // tests with zero distance
    output = KDCalc.findDistance(0,0,0,0);
    assertEquals(output, 0, 0.01);

    output = KDCalc.findDistance(32,75,32,75);
    assertEquals(output, 0, 0.01);

    // test with big numbers
    output = KDCalc.findDistance(3534573, 35479, 67896578, 567824);
    assertEquals(output, 64364206.503452, 0.01);

    // test with doubles
    output = KDCalc.findDistance(45.155, 235.235, 75.35, 856.23);
    assertEquals(output, 621.728661, 0.01);
  }
}
