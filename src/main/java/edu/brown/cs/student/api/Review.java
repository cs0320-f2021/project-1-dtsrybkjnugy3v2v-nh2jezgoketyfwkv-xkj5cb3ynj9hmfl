package edu.brown.cs.student.api;

import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */

public class Review {
  public String review_text;
  public String review_summary;
  public String review_date;
  public long id;
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
