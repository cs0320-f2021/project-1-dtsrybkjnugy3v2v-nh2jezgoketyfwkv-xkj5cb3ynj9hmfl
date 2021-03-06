package edu.brown.cs.student.orm;

import java.util.Map;

public class Interests {
  private String interest;

  public Interests(Map<String, String> mapper) {
    this.interest = mapper.get("interest");
  }

  @Override
  public String toString() {
    return "Interests{" + "interest='" + interest + '\'' + '}';
  }

  public String getInterest() {
    return interest;
  }
}
