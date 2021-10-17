package edu.brown.cs.student.kdtree;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class KDNode<T extends Insertable> {
  public T datum;
  private int depth;
  public KDNode leftChild;
  public KDNode rightChild;
  public double distance;
  private int userID;
  private ArrayList<Double> fieldValues;

  public KDNode(T datum) throws IllegalAccessException {
    this.datum = datum;
    this.depth = 0;
    this.leftChild = null;
    this.rightChild = null;
    this.distance = 0;
    this.userID = datum.returnID();
//    Class classObj = this.datum.getClass();
//    Field[] allFields = classObj.getDeclaredFields();
//    for (Field field : allFields) {
//      if (field.get(datum) instanceof Double) {
//        this.fieldValues.add((Double)field.get(datum));
//      }
//    }
  }

//  public ArrayList getFieldValues() {
//    return this.fieldValues;
//  }

  /**
   * getter for the depth parameter
   * @return the depth of the node
   */
  public int getDepth() {
    return this.depth;
  }

  /**
   * checks if the Node is a leaf
   * @return True if the Node is a Leaf and False if it is not
   */
  public boolean isLeaf() {
    if (this.leftChild == null && this.rightChild == null){
      return true;
    }
    return false;
  }

  /**
   * takes in another node as an argument and adds it as a leftchild to the current node
   * @param newNode the new node to be added as a child
   */
  public void addLeftChild(KDNode newNode) {
    this.leftChild = newNode;
  }

  /**
   * takes in another node as an argument and adds it as a right child to the current node
   * @param newNode the new node to be added as a child
   */
  public void addRightChild(KDNode newNode) {
    this.rightChild = newNode;
  }

  /**
   * increase the depth parameter of the node by 1
   * @return
   */
  public int increaseDepth() {
    return this.depth += 1;
  }

  /**
   * gathers together all the parameters of the data that are numbers and thus can be compared
   * @return
   */
  public ArrayList<Double> getNumParams() {
    ArrayList<Double> returnList = this.datum.returnNumParams();
    return returnList;
  }

  /**
   * method called by KDCalculator that stores the Euclidean distance of this node
   * to the target point.
   * @param distance calculated by KDCalculator
   */

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public int getUserID() {
    return this.userID;
  }
}
