package edu.brown.cs.student.api;

import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */
public class User {
  private long user_id;
  private String weight;
  private String bust_size;
  private String height;
  private int age;
  private String body_type;
  private String horoscope;
  public User(Long id) {
    user_id = id;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return user_id == user.user_id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(user_id);
  }

  @Override
  public String toString() {
    return "User [id=" + user_id + ", weight=" + weight +  ", bust_size=" + bust_size + ", height="
        + height + ", age=" + age + ", body_type=" + body_type
        + ", horoscope=" + horoscope + "]";
  }
}
