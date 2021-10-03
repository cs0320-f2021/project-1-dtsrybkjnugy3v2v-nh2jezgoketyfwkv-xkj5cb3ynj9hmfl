package edu.brown.cs.student.api;

import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */
public class Rent {
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
}

