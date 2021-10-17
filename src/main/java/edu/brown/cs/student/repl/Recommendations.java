package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.User;
import edu.brown.cs.student.api.UserResponse;
import edu.brown.cs.student.kdtree.Insertable;
import edu.brown.cs.student.kdtree.KDCalculator;
import edu.brown.cs.student.kdtree.KDNode;
import edu.brown.cs.student.kdtree.KDTree;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Recommendations implements Command {
  private final LoadResponses responses;
  private KDTree userKDTree;
  private KDNode root;

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
    if (arguments.length != 3) {
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
    UserResponse targetUser = (UserResponse) responses.getHashMap().get(targetUserID);
    kdCalc.findNearestNeighbors(numNeighbors, targetUser, root);

    for (KDNode<Insertable> neighbor : kdCalc.getNeighbors()) {
      System.out.println(neighbor.getUserID());
    }
  }
}
