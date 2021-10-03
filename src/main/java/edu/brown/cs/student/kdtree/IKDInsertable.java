package edu.brown.cs.student.kdtree;

import java.util.ArrayList;

/**
 * the logic behind this interface is that not every piece of data can be inserted into a KDTree
 * only data with parameters that are numerical and can be compared are viable to be
 * inserted into a KDTree
 */
public interface IKDInsertable {
  /**
   * method that returns all the parameters of the object that are numerical in
   * an array list
   */
  public ArrayList returnNumParams();
}
