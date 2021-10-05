package edu.brown.cs.student.kdtree;

import edu.brown.cs.student.api.User;

import java.util.ArrayList;

public class KDCalculator {

  private ArrayList<KDNode<?>> neighbors;

  public KDCalculator() {
    this.neighbors = new ArrayList<>();
  }

  /**
   * prints the k most similar users as the given user.
   * @param k the number of nearest neighbors to find
   * @param targetUser the user whose neighbors we'd like to find
   * @param currNode the node which is currently being compared
   */
  public void findNearestNeighbors(int k, User targetUser, KDNode<?> currNode) {
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
                                   double targetAge, KDNode<?> currNode) {
    double neighborWeight = currNode.getNumParams().get(0);
    double neighborAge = currNode.getNumParams().get(1);
    double distance = this.findDistance(targetWeight, targetAge, neighborWeight, neighborAge);

    // adds the current  node to the list of nearest neighbors if the list is not full
    if (this.neighbors.size() < k) {
      this.neighbors.add(currNode);
    } else { // else, current node is added to the list if it is closer than a node in the list
      boolean inserted = false;
      int i = 0;
      while (!inserted || i < k) {
        if (distance < this.neighbors.get(i).distance) {
          this.neighbors.remove(i);
          this.neighbors.add(currNode);
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
    double relevantDistance = (double) (currNode.getNumParams().get(relevantAxis))
        - relevantParam;

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
  public ArrayList<KDNode<?>> getNeighbors() {
    return this.neighbors;
  }


  /**
   * prints the horoscope chart of the k most similar users to a given user.
   * @param k the number of nearest neighbors to find and print horoscopes of
   * @param user the user whose neighbors we'd like to find.
   * @param root the node of the tree which the nearest neighbors algorithm will start on
   */
  public void classifyUsers(int k, User user, KDNode<?> root) {
  }

  /**
   * prints the horoscope chart of the k most similar users to a given weight, height, and age.
   * @param k the number of nearest neighbors to find and print horoscopes of
   * @param weight the weight to find the closest neighbors of. can be thought of as 'x'
   * @param age the age to find the closest neighbors of. can be thought of as 'y'
   */
  public void classifyUsers(int k, int weight, int age) {

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
}
