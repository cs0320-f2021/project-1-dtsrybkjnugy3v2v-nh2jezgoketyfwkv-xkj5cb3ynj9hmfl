package edu.brown.cs.student.kdtree;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class KDTree implements IKDTree {
  private KDNode root;
  private List<Insertable> data;
  private int k; //the number of dimensions in the tree
  private Field[] fields;

  /**
   * constructor for the KDTree.
   * @param data
   */
  public KDTree(Collection<? extends Insertable> data) throws IllegalAccessException {
    this.data = new ArrayList<>(data);
    KDNode firstNode = new KDNode(this.data.get(0));
    this.k = firstNode.getNumParams().size();
    Class classObj = this.data.get(0).getClass();
    this.fields = classObj.getDeclaredFields();
    this.root = treeGenerator(this.data, 0);
  }

//  @Override
//  public List subList(int startIndex, int endIndex) {
//
//  }
  /**
   * populates the tree.
   */
  public KDNode treeGenerator(List<Insertable> unsortedData, int depth) throws IllegalAccessException {
    if (unsortedData.size() == 0) {
      return null;
    }
    else {
      sortData(unsortedData, depth);
      int dataSize = unsortedData.size();
      Insertable median = unsortedData.get((int) dataSize / 2); //will need to cast as int in case size it odd number
      List leftList = new ArrayList<>(unsortedData.subList(0, dataSize / 2));
      List rightList = new ArrayList<>(unsortedData.subList((dataSize / 2) + 1, unsortedData.size()));
      KDNode newRoot = new KDNode(median);
      newRoot.addLeftChild(treeGenerator(leftList, (depth + 1) % k));
      newRoot.addRightChild(treeGenerator(rightList, (depth + 1) % k));
      return newRoot;
    }
  }

  /**
   * sorts data given along indicated depth
   * @param unsortedData
   * @return a List of data
   * @throws IllegalAccessException
   */
  public List<Insertable> sortData(List<Insertable> unsortedData, int depth) throws IllegalAccessException {
    if (unsortedData.size() == 0) {
      return new ArrayList<>();
    }
    unsortedData.sort(createComparator(depth));
    return unsortedData;
  }

  public Comparator<Insertable> createComparator(int depth) {
    class DataSorter implements Comparator<Insertable> {

      /**
       * Compares its two arguments for order.  Returns a negative integer,
       * zero, or a positive integer as the first argument is less than, equal
       * to, or greater than the second.<p>
       * <p>
       * The implementor must ensure that {@code sgn(compare(x, y)) ==
       * -sgn(compare(y, x))} for all {@code x} and {@code y}.  (This
       * implies that {@code compare(x, y)} must throw an exception if and only
       * if {@code compare(y, x)} throws an exception.)<p>
       * <p>
       * The implementor must also ensure that the relation is transitive:
       * {@code ((compare(x, y)>0) && (compare(y, z)>0))} implies
       * {@code compare(x, z)>0}.<p>
       * <p>
       * Finally, the implementor must ensure that {@code compare(x, y)==0}
       * implies that {@code sgn(compare(x, z))==sgn(compare(y, z))} for all
       * {@code z}.<p>
       * <p>
       * It is generally the case, but <i>not</i> strictly required that
       * {@code (compare(x, y)==0) == (x.equals(y))}.  Generally speaking,
       * any comparator that violates this condition should clearly indicate
       * this fact.  The recommended language is "Note: this comparator
       * imposes orderings that are inconsistent with equals."<p>
       * <p>
       * In the foregoing description, the notation
       * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
       * <i>signum</i> function, which is defined to return one of {@code -1},
       * {@code 0}, or {@code 1} according to whether the value of
       * <i>expression</i> is negative, zero, or positive, respectively.
       *
       * @param o1 the first object to be compared.
       * @param o2 the second object to be compared.
       * @return a negative integer, zero, or a positive integer as the
       * first argument is less than, equal to, or greater than the
       * second.
       * @throws NullPointerException if an argument is null and this
       *                              comparator does not permit null arguments
       * @throws ClassCastException   if the arguments' types prevent them from
       *                              being compared by this comparator.
       */
      @Override
      public int compare(Insertable o1, Insertable o2) {
        int nodeDepth = depth;
        Double compareValue1 = (Double) o1.returnNumParams().get(nodeDepth % k);
        Double compareValue2 = (Double) o2.returnNumParams().get(nodeDepth % k);
        return compareValue2.compareTo(compareValue1);
      }
    }
    return new DataSorter();
  }



  /**
   * returns the root node of the tree.
   * @return
   */
  public KDNode getRoot() {
    return root;
  }

  @Override
  public String toString() {
    return "KdTree{"
        + "dimensions=" + k
        + ", tree=" + root.toString()
        + '}';
  }
  /** Check if this KdTree is equal to the passed object.
   @param o Another object
   @return a Boolean ture/false if the objects are equal.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KDTree kdTree = (KDTree) o;
    return k == kdTree.k && Objects.equals(root, kdTree.root);
  }

  /** Get a hashcode for a KdTree.
   @return an int representing the hash index.
   */
  @Override
  public int hashCode() {
    return Objects.hash(k, root);
  }

}
