package edu.brown.cs.student.kdtree;

import edu.brown.cs.student.api.User;

import java.util.ArrayList;

public class KDCalculator {

  private ArrayList<KDNode<Insertable>> neighbors;
  private static final String[] horoscopes = new String[12];

  public KDCalculator() {
    this.neighbors = new ArrayList<>();
    horoscopes[0] = "Aries";
    horoscopes[1] = "Taurus";
    horoscopes[2] = "Gemini";
    horoscopes[3] = "Cancer";
    horoscopes[4] = "Leo";
    horoscopes[5] = "Virgo";
    horoscopes[6] = "Libra";
    horoscopes[7] = "Scorpio";
    horoscopes[8] = "Sagittarius";
    horoscopes[9] = "Capricorn";
    horoscopes[10] = "Aquarius";
    horoscopes[11] = "Pisces";
  }

  /**
   * prints the k most similar users as the given user.
   * @param k the number of nearest neighbors to find
   * @param targetUser the user whose neighbors we'd like to find
   * @param currNode the node which is currently being compared
   */
  public void findNearestNeighbors(int k, Insertable targetUser, KDNode<Insertable> currNode) {
    double weight = (double) targetUser.returnNumParams().get(0);
    double age = (double) targetUser.returnNumParams().get(1);
    this.findNearestNeighbors(k, weight, age, currNode);
  }

  /**
   * prints the k most similar users to a given weight, height, and age.
   * @param k the number of nearest nearest neighbors to find
   * @param targetWeight the weight to find the nearest neighbors of. can be thought of as 'x'
   * @param targetAge the age to find the nearest neighbors of. can be thought of as 'y'
   * @param currNode the node which is currently being compared
   */

  public void findNearestNeighbors(int k, double targetWeight,
                                   double targetAge, KDNode<Insertable> currNode) {
    double neighborWeight = currNode.getNumParams().get(0);
    double neighborAge = currNode.getNumParams().get(1);
    double distance = this.findDistance(targetWeight, targetAge, neighborWeight, neighborAge);
    currNode.setDistance(distance);

    // adds the current  node to the list of nearest neighbors if the list is not full
    if (this.neighbors.size() < k) {
      //System.out.println("Adding: " + currNode.getUserID());
      this.neighbors.add(currNode);

    } else { // else, current node is added to the list if it is closer than a node in the list
      boolean inserted = false;
      int i = 0;
      while (!inserted && i < k) {
        if (distance < this.neighbors.get(i).distance) {
          //System.out.println("Going to remove: " + this.neighbors.get(i).getUserID());
          //System.out.println("Replacing it with: " + currNode.getUserID());
          this.neighbors.remove(i);
          this.neighbors.add(i, currNode);
          inserted = true;
        }
        i++;
      }
    }
    // finds the relevant axis based on depth and sets that to the parameter to be compared
    int relevantAxis = currNode.getDepth() % k;
    double relevantParam = targetWeight;
    if (relevantAxis == 1) {
      relevantParam = targetAge;
    }
    this.neighbors.sort(new DistanceComparator());
    int numNeighbors = this.neighbors.size();
    double farthestDistance = this.neighbors.get(numNeighbors - 1).distance;
    double relevantDistance = Math.abs(currNode.getNumParams().get(relevantAxis)
        - relevantParam);
    //System.out.println("Farthest Distance: " + farthestDistance);
    //System.out.println("Relevant Distance/Axis: " + relevantDistance + "'" + relevantAxis);

    // recurse if farthest neighbor is farther than the relevant axis of the target and current node
    if ((farthestDistance > relevantDistance || numNeighbors < k) && !currNode.isLeaf()) {
      this.findNearestNeighbors(k, targetWeight, targetAge, currNode.leftChild);
      this.findNearestNeighbors(k, targetWeight, targetAge, currNode.rightChild);

      // recurse on right child if target node's axis is greater than current node's
    } else if (relevantDistance < 0 && !currNode.isLeaf()) {
      this.findNearestNeighbors(k, targetWeight, targetAge, currNode.rightChild);

      // recurse on left child if target node's axis is less than current node's axis
    } else if (relevantDistance > 0 && !currNode.isLeaf()) {
      this.findNearestNeighbors(k, targetWeight, targetAge, currNode.leftChild);
    }
  }

  /**
   * returns a list of the nearest neighbors so that they can be printed by the REPL.
   * @return an arrayList of KDNodes that are nearest neighbors
   */
  public ArrayList<KDNode<Insertable>> getNeighbors() {
    return this.neighbors;
  }


  /**
   * prints the horoscope chart of the k most similar users to a given user.
   * @param k the number of nearest neighbors to find and print horoscopes of
   * @param user the user whose neighbors we'd like to find.
   * @param root the node of the tree which the nearest neighbors algorithm will start on
   */
  public void classifyUsers(int k, User user, KDNode<Insertable> root) {
    this.findNearestNeighbors(k, user, root);
    //this.constructHoroscopeChart(k);
  }

  /**
   * prints the horoscope chart of the k most similar users to a given weight, height, and age.
   * @param k the number of nearest neighbors to find and print horoscopes of
   * @param weight the weight to find the closest neighbors of. can be thought of as 'x'
   * @param age the age to find the closest neighbors of. can be thought of as 'y'
   * @param root the node of the tree which the nearest neighbors algorithm will start on
   */
  public void classifyUsers(int k, double weight, double age, KDNode<Insertable> root) {
    this.findNearestNeighbors(k, weight, age, root);
    //this.constructHoroscopeChart(k);
  }

  /**
   * finds the Euclidean distance between two points in a two dimensional space.
   * @param x the x-coordinate of the first point
   * @param y the y-coordinate of the first point
   * @param x2 the x-coordinate of the second point
   * @param y2 the y-coordinate of the second point
   * @return the distance as a double
   */
  public double findDistance(double x, double y, double x2, double y2) {
    return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
  }

//  private void constructHoroscopeChart(int numNeighbors) {
//    int[] horoscopeChart = new int[12]; // todo: should create Constant variable
//    for (int i = 0; i < numNeighbors; i++) {
//      Insertable user = this.neighbors.get(i).datum;
//      String horoscope = user.getHoroscope();
//      for (int j = 0; j < horoscopes.length; j++) {
//        if (horoscopes[j].equals(horoscope)) {
//          int currCount = horoscopeChart[j];
//          currCount += 1;
//          horoscopeChart[j] = currCount;
//        }
//      }
//    }
//    this.printHoroscopeChart(horoscopeChart);
//  }

  private void printHoroscopeChart(int[] horoscopeChart) {
    for (int i = 0; i < horoscopeChart.length; i++) {
      System.out.println(horoscopes[i] + ": " + horoscopeChart[i]);
    }
  }
}
