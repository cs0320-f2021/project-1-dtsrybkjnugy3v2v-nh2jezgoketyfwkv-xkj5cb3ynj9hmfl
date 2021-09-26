package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NeighborCalculator {

  private HashMap<String, Star> _stars;

  public NeighborCalculator(HashMap<String, Star> stars) {
    _stars = stars;
  }
  public void nearest(int k, String name) {
    Star origin = _stars.get(name);
    List<Star> starsArray = new ArrayList<>(_stars.values());
    for (Star neighbor : starsArray) {
      neighbor.setDistance(origin.getX(), origin.getY(), origin.getZ());
    }
    starsArray.sort(new DistanceComparator());
    int count = 0;
    while (count <= k) {
      if (!starsArray.get(count).equals(origin)) {
        System.out.println(starsArray.get(count).getID());
      }
      count++;
    }
  }

  public void nearest(int k, double x, double y, double z) {
    List<Star> starsArray = new ArrayList<>(_stars.values());
    for (Star neighbor : starsArray) {
      neighbor.setDistance(x, y, z);
    }
    starsArray.sort(new DistanceComparator());
    int count = 0;
    while (count < k) {
      System.out.println(starsArray.get(count).getID());
      count++;
    }
  }
}
