package edu.brown.cs.student.api;

import edu.brown.cs.student.kdtree.IKDInsertable;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */
public class User implements IKDInsertable {
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

  /**
   * method that returns all the parameters of the object that are numerical in
   * an array list
   */
  @Override
  public ArrayList returnNumParams() {
    ArrayList<Double> returnList = new ArrayList<>();
    returnList.add(weightToDouble());
    returnList.add((double) this.age);
    return returnList;
  }

  @Override
  public int returnID() {
    return (int) user_id;
  }

  /**
   * Method that converts the weight to a double and returns it
   */
  private double weightToDouble() {
    String weightSubString = this.weight.substring(0, this.weight.length() - 3);
    return Double.parseDouble(weightSubString);
  }

  public int getID() {
    return (int) user_id;
  }
}
