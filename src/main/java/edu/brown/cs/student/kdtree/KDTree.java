package edu.brown.cs.student.kdtree;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public class KDTree implements IKDTree {
  private KDNode root;
  private ArrayList<IKDInsertable> data;
  private int k; //the number of dimensions in the tree
  private Field[] fields;

  /**
   * constructor for the KDTree
   * @param data
   */
  public KDTree(Collection<IKDInsertable> data) {
    this.data = new ArrayList<>(data);
    KDNode firstNode = new KDNode(this.data.get(0));
    this.k = firstNode.getNumParams().size();
    Class classObj = this.data.get(0).getClass();
//    Constructor[] cons = classObj.getConstructors();
//    this.k = cons[0].getParameterCount();
    this.fields = classObj.getDeclaredFields();
  }

  /**
   * populates the tree
   * @throws IllegalAccessException
   */
  public void treeGenerator() throws IllegalAccessException {
    KDNode currNode = null;
    for (IKDInsertable datum: data) {
      KDNode newNode = new KDNode(datum);
      if (this.root == null) { //if there is not a root yet
        this.root = newNode;
        currNode = this.root;
      } else {
        while (!currNode.isLeaf()) { // while we aren't at a leaf node
          int depth = newNode.increaseDepth();
          int aligningParameterIndex = depth % k;
          if ((Double) newNode.getNumParams().get(aligningParameterIndex)
              < (Double) currNode.getNumParams().get(aligningParameterIndex)) {
            if (currNode.isLeaf()) {
              currNode.addLeftChild(newNode);
            } else {
              currNode = currNode.leftChild;
            }
          } else if ((Double) newNode.getNumParams().get(k) < (Double) currNode.getNumParams().get(k)) {
            if (currNode.isLeaf()) {
              currNode.addRightChild(newNode);
            } else {
              currNode = currNode.rightChild;
            }
          }

        }

      }
    }

  }

}
