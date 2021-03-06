package edu.brown.cs.student.api;

import edu.brown.cs.student.kdtree.Insertable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is used for parsing json data. It is an outline af a user
 */
public class User implements Insertable {
  public long user_id;
  public String weight;
  public String bust_size;
  public String height;
  public int age;
  public String body_type;
  public String horoscope;
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
   * method that returns all the parameters of the object that are numerical in.
   * an array list
   */
  @Override
  public ArrayList returnNumParams() {
    ArrayList<Double> returnList = new ArrayList<>();
    returnList.add(weightToDouble());
    returnList.add((double) this.age);
    returnList.add(heightToDouble());
    return returnList;
  }

  @Override
  public List<String> returnStringParams() {
    return null;
  }

  @Override
  public int returnID() {
    return (int) user_id;
  }

  /**
   * Method that converts the weight to a double and returns it.
   */
  private double weightToDouble() {
    String weightSubString = this.weight.substring(0, this.weight.length() - 3);
    return Double.parseDouble(weightSubString);
  }

  private double heightToDouble() {
    this.height = this.height.replace("\"", "");
    this.height = this.height.replace("'", "");
    int feet = Integer.parseInt(this.height.substring(0, 1).strip());
    int inches = Integer.parseInt(this.height.substring(1).strip());
    return 12 * feet + inches;
  }

  public int getID() {
    return (int) user_id;
  }

  /**
   * used for the classify command. called by KDCalculator in order
   * to create a horoscope chart
   * @return a string that represents the user's horoscope
   */
  public String getHoroscope() {
    return this.horoscope;
  }
}
