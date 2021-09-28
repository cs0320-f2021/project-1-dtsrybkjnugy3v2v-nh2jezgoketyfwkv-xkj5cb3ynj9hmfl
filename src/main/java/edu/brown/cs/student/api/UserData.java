package edu.brown.cs.student.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is used for collecting user data and combining users
 */
public class UserData {
  // These are used for concatenating URIs together for get requests
  String URIP1; //part 1
  String URIP2; //part 2
  List<String> nums;

  Set<User> userSet = new HashSet<>();
  public UserData(){
    nums = Arrays.asList("one","two", "three", "four", "five");
    URIP1 = "https://runwayapi.herokuapp.com/users-";
    URIP2 = "?auth=csims&key=" + ClientAuth.getApiKey();
  }

  /**
   * Stores users in an array
   */
  public void getData(){
    ApiClient client = new ApiClient();


    // Cycles through all URIs so we can build a list of Users
    for(String num: nums){
      User[] users;
      GsonParser gsonParser = new GsonParser();
      users = gsonParser.parseUser(client.makeRequest(ClientRequestGenerator.getRequest(URIP1 + num +URIP2)));
      if(users != null){
        Collections.addAll(userSet, users);
      }
    }
    // Removes the null user from the set
    User nullUser = new User();
    userSet.remove(nullUser);

    //Prints each user's data
    for (User user: userSet){
      System.out.println(user.toString());
    }
    System.out.println(userSet.size());
  }

  /**
   * Returns a list of all users
   */
  public Set<User> getUsers(){
    return userSet;
  }
}
