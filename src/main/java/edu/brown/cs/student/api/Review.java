package edu.brown.cs.student.api;

import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */

public class Review {
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
}
