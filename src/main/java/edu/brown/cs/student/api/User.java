package edu.brown.cs.student.api;

import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */
public class User {
  public long user_id;
  public String weight;
  public String bust_size;
  public String height;
  public int age;
  public String body_type;
  public String athletic;
  public String horoscope;

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
    return user_id == user.user_id && age == user.age &&
        Objects.equals(weight, user.weight) &&
        Objects.equals(bust_size, user.bust_size) &&
        Objects.equals(height, user.height) &&
        Objects.equals(body_type, user.body_type) &&
        Objects.equals(athletic, user.athletic) &&
        Objects.equals(horoscope, user.horoscope);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user_id);
  }

  //TODO write a toString statement so we can print data on console
//  @Override
//  public String toString() {
//    return "User [id=" + id + ", name=" + name + "]";
//  }
}
