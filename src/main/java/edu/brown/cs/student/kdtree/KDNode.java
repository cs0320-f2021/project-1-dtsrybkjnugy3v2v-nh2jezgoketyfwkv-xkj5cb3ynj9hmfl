package edu.brown.cs.student.kdtree;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class KDNode<T extends IKDInsertable> {
  private T datum;
  private int depth;
  public KDNode leftChild;
  public KDNode rightChild;

  public KDNode(T datum) {
    this.datum = datum;
    this.depth=0;
    this.leftChild = null;
    this.rightChild = null;
  }

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
    if(this.leftChild == null && this.rightChild==null){
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
    return this.depth +=1;
  }

  /**
   * gathers together all the parameters of the data that are numbers and thus can be compared
   * @return
   * @throws IllegalAccessException
   */
  public ArrayList getNumParams() throws IllegalAccessException {
    ArrayList returnList = new ArrayList();
    Class classObj = datum.getClass();
    Field[] fields = classObj.getDeclaredFields();
    for(Field field: fields){
      if(field.get(datum) instanceof Number){
        returnList.add(field.get(datum));
      }
    }
    return returnList;
  }

}
