package edu.brown.cs.student.api;

import edu.brown.cs.student.kdtree.Insertable;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * This class is used for collecting user data and combining users.
 */
public class UserResponseData {
  private final String url;
  private final HashMap<Integer, Insertable> responseMap = new HashMap<>();
  private final ApiClient client;
  private final GsonParser gsonParser;

  public UserResponseData() {
    url = "https://runwayapi.herokuapp.com/integration";
    client = new ApiClient();
    gsonParser = new GsonParser();
  }

  /**
   * Stores users in a set.
   */
  public void setData() throws SQLException, ClassNotFoundException, InvocationTargetException,
      InstantiationException, IllegalAccessException, NoSuchMethodException {
    //Parse responses and add the responses to set
    UserResponse[] responses = gsonParser.parseUserResponse(client.makeRequest(
        ClientRequestGenerator.getPostRequest(url)));
    for (UserResponse response : responses) {
      response.instantiate();
//      response.returnStringParams();
//      response.returnNumParams();
      responseMap.put(response.returnID(), response);
    }
    //Print number of responses loaded
    System.out.println("Loaded " + responseMap.size() + " user responses");
  }

  /**
   * @return a HashMap of users
   */
  public HashMap<Integer, Insertable> getHashMap() {
    return responseMap;
  }
}
