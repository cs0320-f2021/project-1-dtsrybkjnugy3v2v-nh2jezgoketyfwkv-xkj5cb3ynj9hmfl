package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import edu.brown.cs.student.stars.MathBot;
import org.junit.Test;

public class MathBotTest {

  /**
   * TODO: add comments for each test
   */

  @Test
  public void testAddition() {
    MathBot matherator9000 = new MathBot();
    double output = matherator9000.add(10.5, 3);
    assertEquals(13.5, output, 0.01);
  }

  @Test
  public void testLargerNumbers() {
    MathBot matherator9001 = new MathBot();
    double output = matherator9001.add(100000, 200303);
    assertEquals(300303, output, 0.01);
    output = matherator9001.subtract(300303, 100000);
    assertEquals(200303, output, 0.01);
  }

  @Test
  public void testSubtraction() {
    MathBot matherator9002 = new MathBot();
    double output = matherator9002.subtract(18, 17);
    assertEquals(1, output, 0.01);
  }

  @Test
  public void testZeros() {
    MathBot mb = new MathBot();
    double output = mb.add(0, 0);
    assertEquals(0.0, output, 0.01);
  }

  @Test
  public void testAdditionWithNegatives() {
    MathBot mb = new MathBot();
    double output = mb.add(-100, 50);
    assertEquals(-50, output, 0.01);
  }

  @Test
  public void testSubtractingLargerNumber() {
    MathBot mb = new MathBot();
    double output = mb.subtract(500, 1000);
    assertEquals(-500, output, 0.01);
  }

  @Test
  public void testSmallNumbers() {
    MathBot mb = new MathBot();
    double output = mb.add(0.01, 0.01);
    assertEquals(0.02, output, 0.001);
    output = mb.subtract(0.02, 0.01);
    assertEquals(0.01, output, 0.001);
  }
}
