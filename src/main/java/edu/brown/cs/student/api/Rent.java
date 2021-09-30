package edu.brown.cs.student.api;

import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */
public class Rent {
  public String fit;
  public long user_id;
  public long item_id;
  public int rating;
  public String rented_for;
  public String category;
  public int size;
  public int id;
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

