package edu.brown.cs.student.repl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface Command {
  String getCommand();
  void runCommand(String[] arguments)
      throws IllegalAccessException, SQLException, ClassNotFoundException,
      InvocationTargetException, InstantiationException, NoSuchMethodException;
}
