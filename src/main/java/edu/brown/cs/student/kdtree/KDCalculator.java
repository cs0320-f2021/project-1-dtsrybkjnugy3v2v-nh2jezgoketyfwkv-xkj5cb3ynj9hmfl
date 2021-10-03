package edu.brown.cs.student.kdtree;

import java.util.ArrayList;

public class KDCalculator {

  public KDCalculator() {

  }

  /**
   * prints the k most similar users as the given user
   * @param k
   * @param user
   */
  public void findSimilarUsers(int k, int user) {
    ArrayList<Integer> neighbors = new ArrayList<>();

    // loops through list of closest neighbors and prints them to the REPL
    int count = 0;
    while (count <= k) {
      System.out.println(neighbors.get(count));
      count++;
    }

  }

  /**
   * prints the k most similar users to a given weight, height, and age
   * @param k
   * @param weight
   * @param height
   * @param age
   */

  public void findSimilarUsers(int k, int weight, int height, int age) {
    ArrayList<Integer> neighbors = new ArrayList<>();
  }

  /**
   * prints the horoscope chart of the k most similar users to a given user
   * @param k
   * @param user
   */

  public void classifyUsers(int k, int user) {
    ArrayList<Integer> neighbors = new ArrayList<>();
  }

  /**
   * prints the horoscope chart of the k most similar users to a given weight, height, and age
   * @param k
   * @param weight
   * @param height
   * @param age
   */

  public void classifyUsers(int k, int weight, int height, int age) {

  }

  /**
   * finds the distance between two points in a three dimensional space
   * @param x
   * @param y
   * @param z
   * @param x2
   * @param y2
   * @param z2
   * @return
   */

  public double findDistance(int x, int y, int z, int x2, int y2, int z2) {
    return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2) + Math.pow(z - z2, 2));
  }
}
