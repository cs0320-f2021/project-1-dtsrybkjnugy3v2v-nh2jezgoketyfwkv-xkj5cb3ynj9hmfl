package edu.brown.cs.student.orm;

import java.util.Map;
public class Negative {
  private String trait;
  public Negative(Map<String, String> mapper) {
    this.trait = mapper.get("trait");
  }
  @Override
  public String toString() {
    return "Negative{" + "trait='" + trait + '\'' + '}';
  }

  public String getTrait() {
    return trait;
  }
}
