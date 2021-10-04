package edu.brown.cs.student.stars;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Star> {

  public int compare(Star star1, Star star2) {
    return star1.getDistance() - star2.getDistance();
  }
}
