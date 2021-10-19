package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.UserResponse;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GenerateGroups implements Command {
  private Recommendations recCommand;
  public GenerateGroups(Recommendations recCommand) {
    this.recCommand = recCommand;
  }
  @Override
  public String getCommand() {
    return "recsys_gen_groups";
  }

  @Override
  public void runCommand(String[] arguments)
      throws SQLException, ClassNotFoundException, InvocationTargetException,
      IllegalAccessException, InstantiationException, NoSuchMethodException {
    HashMap<String, UserResponse> students = recCommand.getResponses().getHashMap();
    int numStudents = students.size();
    int numGroups = Math.floorDiv(numStudents, 3);
    // If there cannot be exactly three students in every group, create a new group
    if (numGroups % 3 != 0) {
      numGroups++;
    }
    // Stores each group as String array with each member's ID
    String[][] groups = new String[numGroups][3];
    int count = 0;
    // Iterates through each student and adds them to the array of groups based on
    // their best recommendation
    for (String studentID : students.keySet()) {
      groups[count] = recCommand.getRecs(2, studentID);
      count++;
    }
    // Prints each group
    for (String[] group : groups) {
      System.out.println(Arrays.toString(group));
    }
  }
}
