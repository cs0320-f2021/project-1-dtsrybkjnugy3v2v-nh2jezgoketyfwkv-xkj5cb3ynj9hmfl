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
    KDCalculator kdcalc = new KDCalculator(null);
    double output = kdcalc.findDistance(1,1,1,0,0,0);
    assertEquals(output, 1.732051, 0.001);
    output = kdcalc.findDistance(5, 3, 8, 1, 9, 3);
    assertEquals(output, 8.774964, 0.001);
    output = kdcalc.findDistance(0,0,0,0,0,0);
    assertEquals(output, 0, 0);
  }
}
