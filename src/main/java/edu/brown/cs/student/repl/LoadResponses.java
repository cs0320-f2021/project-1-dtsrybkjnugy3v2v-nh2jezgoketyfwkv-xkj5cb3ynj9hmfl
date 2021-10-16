package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.UserResponseData;
import edu.brown.cs.student.kdtree.IKDInsertable;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;

public class LoadResponses implements Command {
  private HashMap<Integer, IKDInsertable> responsesHashMap;
  @Override
  public String getCommand() {
    return "responseGet";
  }

  @Override
  public void runCommand(String[] arguments)
      throws SQLException, ClassNotFoundException, InvocationTargetException,
      InstantiationException, IllegalAccessException, NoSuchMethodException {
    UserResponseData userResponseData = new UserResponseData();
    userResponseData.setData();
    responsesHashMap = userResponseData.getHashMap();
  }
}
