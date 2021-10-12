package edu.brown.cs.student.repl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * The command interface is implemented by all commands that you would like the REPL
 * to recognize and run. All that the REPL needs to do with a command is know which String
 * input is used to execute it and a means of telling it to run.
 */
public interface Command {
  String getCommand();
  void runCommand(String[] arguments)
      throws IllegalAccessException, SQLException, ClassNotFoundException,
      InvocationTargetException, InstantiationException, NoSuchMethodException;
}
