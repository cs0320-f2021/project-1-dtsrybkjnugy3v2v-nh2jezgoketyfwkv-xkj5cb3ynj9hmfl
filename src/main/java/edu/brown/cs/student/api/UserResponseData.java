package edu.brown.cs.student.api;

import edu.brown.cs.student.orm.Database;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used for collecting user data and combining users.
 */
public class UserResponseData {
  private final String url;
  private final Set<UserResponse> responseSet = new HashSet<>();

  public UserResponseData() throws SQLException, ClassNotFoundException {
    url = "https://runwayapi.herokuapp.com/integration";
  }

  /**
   * Stores users in a set.
   */
  public void getData() throws SQLException, InvocationTargetException, InstantiationException,
      IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
    // Get the api data
    ApiClient client = new ApiClient();
    GsonParser gsonParser = new GsonParser();
    UserResponse[] responses = gsonParser.parseUserResponse(client.makeRequest(
        ClientRequestGenerator.getPostRequest(url)));

    Collections.addAll(responseSet, responses);
    //Cycle through responses and get data from the database using the api data's user ids
    for (UserResponse response: responseSet) {
      System.out.println(response);
      response.setSkills();
      response.setNegativeTraits();
      response.setPositiveTraits();
      response.setInterests();
      System.out.println();
    }
  }

  /**
   * Returns a list of all users.
   * @return a set of Users
   */
  public Set<UserResponse> getSet() {
    return responseSet;
  }
}
