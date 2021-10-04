package edu.brown.cs.student.kdtree;

import edu.brown.cs.student.stars.Star;

import java.util.Comparator;

public class DistanceComparator implements Comparator<KDNode> {

  public int compare(Star star1, Star star2) {
    return star1.getDistance() - star2.getDistance();
  }

  @Override
  public int compare(KDNode node1, KDNode node2) {
    return (int) (node1.distance - node2.distance);
  }
}
