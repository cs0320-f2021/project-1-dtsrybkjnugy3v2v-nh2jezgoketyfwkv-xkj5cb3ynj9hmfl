package edu.brown.cs.student.repl;


import edu.brown.cs.student.api.UserResponse;
import edu.brown.cs.student.bloomFilter.bloomfilter.AndSimilarityComparator;
import edu.brown.cs.student.bloomFilter.bloomfilter.BloomFilter;
import edu.brown.cs.student.bloomFilter.bloomfilter.BloomFilterRecommender;
import edu.brown.cs.student.kdtree.Insertable;
import edu.brown.cs.student.kdtree.KDCalculator;
import edu.brown.cs.student.kdtree.KDNode;
import edu.brown.cs.student.kdtree.KDTree;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Recommendations implements Command {
  private final LoadResponses responses;
  private KDTree userKDTree;
  private KDNode root;
  private ArrayList<String> neighbors;

  public Recommendations(LoadResponses responses) {
    this.responses = responses;
  }

  @Override
  public String getCommand() {
    return "recsys_rec";
  }

  @Override
  public void runCommand(String[] arguments)
      throws IllegalAccessException, SQLException, ClassNotFoundException,
      InvocationTargetException, InstantiationException, NoSuchMethodException {
    if (arguments.length != 3 || Double.parseDouble(arguments[1])
        >= responses.getHashMap().size()) {
      throw new NoSuchMethodException("Invalid number of arguments");
    }
    //Create a KDT
    userKDTree = new KDTree(responses.getHashMap().values());
    this.root = userKDTree.getRoot();
    // Instantiate calculator
    KDCalculator kdCalc = new KDCalculator();
    // Argument 1 is number of recommendations requested
    int numNeighbors = Integer.parseInt(arguments[1]);
    // Argument 2 is student id who we are finding recommendations for
    int targetUserID = Integer.parseInt(arguments[2]);
    UserResponse targetUser = responses.getHashMap().get(String.valueOf(targetUserID));
    try {
      kdCalc.findNearestNeighbors(numNeighbors + 1, targetUser, root);
    } catch(NullPointerException n) {}
    // Create a new bloomfilter
    BloomFilterRecommender<UserResponse> bloomFilter = new BloomFilterRecommender<>(responses.getHashMap(), 0.05);
    // Target bloom filter
    BloomFilter<String> target = bloomFilter.getBloomFilters().get(String.valueOf(targetUserID));
    // Set comparator
    bloomFilter.setBloomFilterComparator(new AndSimilarityComparator(target));
    // Get recommendations
    List<UserResponse> recommendations = bloomFilter.getTopKRecommendations(targetUser, numNeighbors);

    ArrayList<KDNode<Insertable>> kdRecs = kdCalc.getNeighbors();
    HashSet<Integer> ids = new HashSet<>();
    for (KDNode<Insertable> node: kdRecs) {
      ids.add(node.datum.returnID());
    }

    this.neighbors = new ArrayList<>();
    // Cycles through Bloom Filter recommendations. If there is a user in both kdtree and bloomfilter
    // recommendations, we add it to the final neighbor list.
    for (UserResponse user : recommendations) {
      if (ids.contains(user.returnID())) {
        this.neighbors.add(user.getId());
      }
    }
    // We cycle through the bloom filter recommendations again until the neighbor set is of size k
    int count = 0;
    while (this.neighbors.size() < numNeighbors) {
      if (!this.neighbors.contains(recommendations.get(count).getId())) {
        this.neighbors.add(recommendations.get(count).getId());
      }
      count++;
    }
    for (String id : this.neighbors) {
      System.out.println(id);
    }
  }

  public ArrayList<String> getRecs() {
    return this.neighbors;
  }
}
