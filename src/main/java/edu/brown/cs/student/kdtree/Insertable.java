package edu.brown.cs.student.kdtree;

import java.util.ArrayList;
import java.util.List;

/**
 * the logic behind this interface is that not every piece of data can be inserted into a KDTree
 * only data with parameters that are numerical and can be compared are viable to be
 * inserted into a KDTree.
 */
public interface Insertable {
  /**
   * method that returns all the parameters of the object that are numerical in
   * an array list.
   */
  ArrayList<Double> returnNumParams();
  List<String> returnStringParams();
  int returnID();
}
