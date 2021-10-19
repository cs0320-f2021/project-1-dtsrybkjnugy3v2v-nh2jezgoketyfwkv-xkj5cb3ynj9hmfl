package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.UserResponse;
import edu.brown.cs.student.api.UserResponseData;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;

public class LoadResponses implements Command {
  private HashMap<String, UserResponse> responsesHashMap;
  @Override
  public String getCommand() {
    return "recsys_load";
  }

  @Override
  public void runCommand(String[] arguments)
      throws SQLException, ClassNotFoundException, InvocationTargetException,
      InstantiationException, IllegalAccessException, NoSuchMethodException {
    // Retrieve data from api and database
    UserResponseData userResponseData = new UserResponseData();
    userResponseData.setData();
    // Retrieve HashMap of data mapping user ids to user responses
    responsesHashMap = userResponseData.getHashMap();
  }

  public HashMap<String, UserResponse> getHashMap() {
    return this.responsesHashMap;
  }
}
