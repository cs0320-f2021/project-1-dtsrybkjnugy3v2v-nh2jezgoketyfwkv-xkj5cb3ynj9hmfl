package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.GsonParser;
import edu.brown.cs.student.api.UserResponseData;
import edu.brown.cs.student.kdtree.Insertable;
import edu.brown.cs.student.kdtree.KDNode;
import edu.brown.cs.student.kdtree.KDTree;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class LoadResponses implements Command {
  private HashMap<Integer, Insertable> responsesHashMap;
  private KDNode root;
  @Override
  public String getCommand() {
    return "responseGet";
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

  public HashMap<Integer, Insertable> getHashMap() {
    return this.responsesHashMap;
  }
}
