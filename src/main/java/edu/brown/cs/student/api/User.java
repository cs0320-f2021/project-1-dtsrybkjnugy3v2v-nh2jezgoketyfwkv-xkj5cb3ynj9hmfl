package edu.brown.cs.student.api;

import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */
public class User {
  public String review_text;
  public String review_summary;
  public String review_date;
  public long id;

  @Override
  /**
   * Compares users. Used to ensure we don't add similar users to the list
   */
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return id == user.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "User [review_text=" + review_text +  ", review_summary=" + review_summary + ", review_date="
        + review_date + ", id=" + id + "]";
  }
}
