package edu.brown.cs.student.bloomFilter.recommender;

import java.util.List;

public interface Item {
  List<String> getVectorRepresentation();
  String getId();
  //student class should extend Item AND KDNode
}
