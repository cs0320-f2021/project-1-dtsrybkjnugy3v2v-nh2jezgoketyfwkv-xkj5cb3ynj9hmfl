package edu.brown.cs.student.orm;

import java.util.Map;

public class Skills {
  private String name;
  private int commenting;
  private int testing;
  private int OOP;
  private int algorithms;
  private int teamwork;
  private int frontend;

  @Override
  public String toString() {
    return "Skills{"
        + ", name='" + name + '\''
        + ", commenting=" + commenting
        + ", testing=" + testing
        + ", OOP=" + OOP
        + ", algorithms=" + algorithms
        + ", teamwork=" + teamwork
        + ", frontend=" + frontend
        + '}';
  }

  public Skills(Map<String, String> mapper) {
    this.name = mapper.get("name");
    this.commenting = Integer.parseInt(mapper.get("commenting"));
    this.testing = Integer.parseInt(mapper.get("testing"));
    this.OOP = Integer.parseInt(mapper.get("OOP"));
    this.algorithms = Integer.parseInt(mapper.get("algorithms"));
    this.teamwork = Integer.parseInt(mapper.get("teamwork"));
    this.frontend = Integer.parseInt(mapper.get("frontend"));
  }
}

