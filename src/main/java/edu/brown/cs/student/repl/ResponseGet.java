package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.UserResponseData;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ResponseGet implements Command {
  @Override
  public String getCommand() {
    return "responseGet";
  }

  @Override
  public void runCommand(String[] arguments)
      throws SQLException, ClassNotFoundException, InvocationTargetException,
      InstantiationException, IllegalAccessException, NoSuchMethodException {
    UserResponseData userResponseData = new UserResponseData();
    userResponseData.getData();
  }
}
