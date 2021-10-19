package edu.brown.cs.student.repl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

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
  }
}
