package edu.brown.cs.student.orm;

import java.util.ArrayList;
import java.util.Map;

public class Skills {
  private String name;
  private double commenting;
  private double testing;
  private double OOP;
  private double algorithms;
  private double teamwork;
  private double frontend;

  @Override
  public String toString() {
    return "Skills{"
        + "commenting=" + commenting
        + ", testing=" + testing
        + ", OOP=" + OOP
        + ", algorithms=" + algorithms
        + ", teamwork=" + teamwork
        + ", frontend=" + frontend
        + '}';
  }

  /**
   * Constructor used for converting a hashmap into Skills.
   * @param mapper
   */
  public Skills(Map<String, String> mapper) {
    this.name = mapper.get("name");
    this.commenting = Double.parseDouble(mapper.get("commenting"));
    this.testing = Double.parseDouble(mapper.get("testing"));
    this.OOP = Double.parseDouble(mapper.get("OOP"));
    this.algorithms = Double.parseDouble(mapper.get("algorithms"));
    this.teamwork = Double.parseDouble(mapper.get("teamwork"));
    this.frontend = Double.parseDouble(mapper.get("frontend"));
  }

  /**
   * Sets the numerical values of the class.
   * @return numerical values of the class
   */
  public ArrayList<Double> getCoords() {
    ArrayList<Double> coords = new ArrayList<>();
    coords.add(commenting);
    coords.add(testing);
    coords.add(OOP);
    coords.add(algorithms);
    coords.add(teamwork);
    coords.add(frontend);
    return coords;
  }
}

