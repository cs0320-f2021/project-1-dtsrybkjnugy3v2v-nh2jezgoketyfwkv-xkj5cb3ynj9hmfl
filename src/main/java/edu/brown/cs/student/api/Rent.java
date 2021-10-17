package edu.brown.cs.student.api;

import edu.brown.cs.student.kdtree.Insertable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */
public class Rent implements Insertable {
  private String fit;
  private long user_id;
  private long item_id;
  private int rating;
  private String rented_for;
  private String category;
  private int size;
  private int id;
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rent rent = (Rent) o;
    return id == rent.id;
  }
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
  @Override
  public String toString() {
    return "Rent [fit=" + fit + ", user_id=" + user_id +  ", item_id=" + item_id + ", rating="
        + rating + ", rented_for=" + rented_for + ", category=" + category
        + ", size=" + size + ", id=" + id  + "]";
  }

  /**
   * method that returns all the parameters of the object that are numerical in
   * an array list
   */
  @Override
  public ArrayList<Double> returnNumParams() {
    ArrayList<Double> returnList = new ArrayList<>();
    returnList.add((double) this.rating);
    returnList.add((double) this.size);
    return returnList;
  }

  @Override
  public List<String> returnStringParams() {
    return null;
  }

  @Override
  public int returnID() {
    return (int) this.user_id;
  }
}

