package edu.brown.cs.student.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is used for collecting user data and combining users.
 */
public class UserData {
  // These are used for concatenating URIs together for get requests
  private final String url1; //part 1
  private final String url2; //part 2
  private final List<String> nums;
  private final Set<User> userSet = new HashSet<>();

  public UserData() {
    nums = Arrays.asList("one", "two", "three", "four", "five");
    url1 = "https://runwayapi.herokuapp.com/users-";
    url2 = "?auth=csims&key=" + ClientAuth.getApiKey();
  }

  /**
   * Stores users in an array.
   */
  public void getData() {
    ApiClient client = new ApiClient();

    // Cycles through all URIs so we can build a list of Users
    for (String num: nums) {
      User[] users;
      GsonParser gsonParser = new GsonParser();
      users = gsonParser.parseUser(client.makeRequest(ClientRequestGenerator.getRequest(
          url1 + num + url2)));
      if (users != null) {
        Collections.addAll(userSet, users);
      }
    }
    // Removes the null user from the set
    User nullUser = new User(0L);
    userSet.remove(nullUser);
  }

  /**
   * Returns a list of all users.
   * @return a set of Users
   */
  public Set<User> getSet() {
    return userSet;
  }
}
