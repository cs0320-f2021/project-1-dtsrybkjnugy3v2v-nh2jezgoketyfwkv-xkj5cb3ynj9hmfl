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
  private Interests interests;
  private Negative negativeTraits;
  private Positive positiveTraits;

  private Database database;
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

  public void setSkills() throws SQLException, ClassNotFoundException, InvocationTargetException,
      InstantiationException, IllegalAccessException, NoSuchMethodException {
    database = new Database("data/project-1/integration.sqlite3");
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("id", String.valueOf(id));
    List<Skills> skillsList = database.select(Skills.class, queryParams);
    skills = skillsList.get(0);
    System.out.println(skills);
  }

  //TODO Store entire array instead of first
  //TODO seperate meeting times

  public void setNegativeTraits() throws SQLException, ClassNotFoundException, InvocationTargetException,
      InstantiationException, IllegalAccessException, NoSuchMethodException {
    database = new Database("data/project-1/integration.sqlite3");
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("id", String.valueOf(id));
    List<Negative> negativeList = database.select(Negative.class, queryParams);
    negativeTraits = negativeList.get(0);
    System.out.println(negativeTraits);
  }

  public void setPositiveTraits() throws SQLException, ClassNotFoundException, InvocationTargetException,
      InstantiationException, IllegalAccessException, NoSuchMethodException {
    database = new Database("data/project-1/integration.sqlite3");
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("id", String.valueOf(id));
    List<Positive> positiveList = database.select(Positive.class, queryParams);
    positiveTraits = positiveList.get(0);
    System.out.println(positiveTraits);
  }

  public void setInterests() throws SQLException, ClassNotFoundException, InvocationTargetException,
      InstantiationException, IllegalAccessException, NoSuchMethodException {
    database = new Database("data/project-1/integration.sqlite3");
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("id", String.valueOf(id));
    List<Interests> interestsList = database.select(Interests.class, queryParams);
    interests = interestsList.get(0);
    System.out.println(interests);
  }

  @Override
  public ArrayList returnNumParams() {
    return null;
  }

  @Override
  public int returnID() {
    return 0;
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
