package edu.brown.cs.student.api;

import edu.brown.cs.student.kdtree.IKDInsertable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */

public class Review implements IKDInsertable {
  private String review_text;
  private String review_summary;
  private String review_date;
  private long id;
  @Override
  public String toString() {
    return "Review [review_text=" + review_text +  ", review_summary=" + review_summary + ", review_date="
        + review_date + ", id=" + id + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Review review = (Review) o;
    return id == review.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  /**
   * method that returns all the parameters of the object that are numerical in
   * an array list
   */
  @Override
  public ArrayList returnNumParams() {
    return null;
  }

  @Override
  public List<String> returnStringParams() {
    return null;
  }

  @Override
  public int returnID() {
    return 0;
  }
}
