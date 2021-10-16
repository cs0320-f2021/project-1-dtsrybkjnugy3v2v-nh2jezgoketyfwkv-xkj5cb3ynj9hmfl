package edu.brown.cs.student.orm;

import java.util.Map;

public class Positive {
  private String trait;
  public Positive(Map<String, String> mapper) {
    this.trait = mapper.get("trait");
  }


  @Override
  public String toString() {
    return "Positive{" + "trait='" + trait + '\'' + '}';
  }
  public String getTrait() {
    return trait;
  }
}
