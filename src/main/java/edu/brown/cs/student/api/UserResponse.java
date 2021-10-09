package edu.brown.cs.student.api;

import edu.brown.cs.student.kdtree.IKDInsertable;

import java.util.ArrayList;
import java.util.Objects;

public class UserResponse implements IKDInsertable {
  private double id;
  private String name;
  private String meeting;
  private String grade;
  private double years_of_experience;
  private String horoscope;
  private String meeting_times;
  private String preferred_language;
  private String marginalized_groups;
  private String prefer_group;
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
