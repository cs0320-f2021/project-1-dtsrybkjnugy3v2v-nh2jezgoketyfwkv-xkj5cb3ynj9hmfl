package edu.brown.cs.student.api;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used for collecting user data and combining users.
 */
public class UserResponseData {
  private final String url;
  private final Set<UserResponse> responseSet = new HashSet<>();

  public UserResponseData() {
    url = "https://runwayapi.herokuapp.com/integration";
  }

  /**
   * Stores users in an array.
   */
  public void getData() {
    ApiClient client = new ApiClient();
    GsonParser gsonParser = new GsonParser();
    UserResponse[] responses = gsonParser.parseUserResponse(client.makeRequest(
        ClientRequestGenerator.getPostRequest(url)));

    Collections.addAll(responseSet, responses);
    for (UserResponse response: responseSet) {
      System.out.println(response.toString());
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
