package edu.brown.cs.student.api;

import edu.brown.cs.student.kdtree.IKDInsertable;
import edu.brown.cs.student.orm.Database;
import edu.brown.cs.student.orm.Interests;
import edu.brown.cs.student.orm.Negative;
import edu.brown.cs.student.orm.Positive;
import edu.brown.cs.student.orm.Skills;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserResponse implements IKDInsertable {
  //API Data
  private int id;
  private String name;
  private String meeting;
  private String grade;
  private double years_of_experience;
  private String horoscope;
  private String meeting_times;
  private String preferred_language;
  private String marginalized_groups;
  private String prefer_group;
  //ORM Data
  private Skills skills;
  private List<Negative> negativeTraits;
  private List<Positive> positiveTraits;
  private List<Interests> interests;
  //Parameterized Data
  private ArrayList<Double> coords;
  private ArrayList<String> stringData;

  // Instance variables
  private Database database;
  private Map<String, String> queryParams;
  public UserResponse(int userId) {
    id = userId;
  }

  @Override
  public String toString() {
    return "UserResponse [id=" + id + ", name=" + name +  ", meeting=" + meeting
        + ", grade=" + grade + ", years_of_experience=" + years_of_experience
        + ", horoscope=" + horoscope + ", meeting_times=" + meeting_times
        + ", preferred_langauge=" + preferred_language + ", marginalized_groups="
        + marginalized_groups + ", prefer_group=" + prefer_group + "]";
  }
  /**
   * Instantiates the class by setting the database and query params.
   */
  public void instantiate() throws SQLException, ClassNotFoundException, InvocationTargetException,
      InstantiationException, IllegalAccessException, NoSuchMethodException {
    database = new Database("data/project-1/integration.sqlite3");
    queryParams = new HashMap<>();
    queryParams.put("id", String.valueOf(id));
    setORMData();
  }

  /**
   * Selects the ORM data from the database based on current user id and instantiates variables
   * @throws SQLException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws NoSuchMethodException
   */
  public void setORMData() throws SQLException, InvocationTargetException,
      InstantiationException, IllegalAccessException, NoSuchMethodException {
    skills = database.select(Skills.class, queryParams).get(0);
    negativeTraits = database.select(Negative.class, queryParams);
    positiveTraits = database.select(Positive.class, queryParams);
    interests = database.select(Interests.class, queryParams);
  }

  @Override
  public ArrayList<Double> returnNumParams() {
    setNumParams();
    for (Double datum: coords) {
      System.out.print(datum + ", ");
    }
    System.out.println();
    return coords;
  }

  /**
   * @return String Parameters
   */
  public List<String> returnStringParams() {
    setStringParams();
    for (String datum: stringData) {
      System.out.print(datum + ", ");
    }
    return stringData;
  }
  /**
   * Adds String parameters to the coords variable.
   */
  public void setNumParams() {
    coords = skills.getCoords();
    coords.add(years_of_experience);
  }
  /**
   * Adds String parameters to the stringData variable.
   */
  private void setStringParams() {
    // Adds string fields from API
    stringData = new ArrayList<>();
    stringData.add(meeting);
    stringData.add(grade);
    stringData.add(horoscope);
    stringData.add(preferred_language);
    stringData.add(marginalized_groups);
    stringData.add(prefer_group);

    // Removes empty fields
    stringData.removeIf(String::isEmpty);

    // adds negative traits
    for (Negative negativeTrait: negativeTraits) {
      stringData.add(negativeTrait.getTrait());
    }
    // adds positive traits
    for (Positive positiveTrait: positiveTraits) {
      stringData.add(positiveTrait.getTrait());
    }
    // adds interests
    for (Interests interest: interests) {
      stringData.add(interest.getInterest());
    }
    // Splits meeting times and adds to stringData
    String[] meetingTimes = meeting_times.split(";");
    for (String meetingTime: meetingTimes) {
      stringData.add(meetingTime.strip());
    }
  }

  @Override
  public int returnID() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserResponse that = (UserResponse) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
