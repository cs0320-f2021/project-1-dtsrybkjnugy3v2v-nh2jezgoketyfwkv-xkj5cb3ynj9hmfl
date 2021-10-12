package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.User;
import edu.brown.cs.student.kdtree.KDCalculator;
import edu.brown.cs.student.kdtree.KDNode;

public class Similar implements Command {
  private final Users userCommand;
  public Similar(Users users) {
    userCommand = users;
  }
  @Override
  public String getCommand() {
    return "similar";
  }

  @Override
  public void runCommand(String[] arguments) {
    KDCalculator kdCalc = new KDCalculator();
    int numNeighbors = Integer.parseInt(arguments[1]);
    if (arguments.length == 3) {
      int targetUserID = Integer.parseInt(arguments[2]);
      User targetUser = (User) userCommand.getHashMap().get(targetUserID);
      kdCalc.findNearestNeighbors(numNeighbors, targetUser, userCommand.getRoot());
      for (KDNode<User> neighbor: kdCalc.getNeighbors()) {
        System.out.println(neighbor.getUserID());
      }
    } else if (arguments.length == 4) {
      double targetWeight = Double.parseDouble(arguments[2]);
      double targetAge = Double.parseDouble(arguments[3]);
      kdCalc.findNearestNeighbors(numNeighbors, targetWeight, targetAge, userCommand.getRoot());
      for (KDNode<User> neighbor: kdCalc.getNeighbors()) {
        System.out.println(neighbor.getUserID());
      }
    } else {
      System.out.println("ERROR: Invalid input for REPL");
    }
  }
}
