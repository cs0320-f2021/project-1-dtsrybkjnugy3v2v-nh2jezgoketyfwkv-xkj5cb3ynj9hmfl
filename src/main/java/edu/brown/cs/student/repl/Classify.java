package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.User;
import edu.brown.cs.student.kdtree.KDCalculator;

public class Classify implements Command {
  private final Users usersCommand;
  public Classify(Users users) {
    this.usersCommand = users;
  }
  @Override
  public String getCommand() {
    return "classify";
  }

  @Override
  public void runCommand(String[] arguments) {
    KDCalculator kdCalc = new KDCalculator();
    int numNeighbors = Integer.parseInt(arguments[1]);
    if (arguments.length == 3) {
      int targetUserID = Integer.parseInt(arguments[2]);
      User targetUser = (User) usersCommand.getHashMap().get(targetUserID);
      kdCalc.classifyUsers(numNeighbors, targetUser, usersCommand.getRoot());
    } else if (arguments.length == 4) {
      double targetWeight = Double.parseDouble(arguments[2]);
      double targetAge = Double.parseDouble(arguments[3]);
      kdCalc.classifyUsers(numNeighbors, targetWeight, targetAge, usersCommand.getRoot());
    } else {
      System.out.println("ERROR: Invalid input for REPL");
    }
  }
}
