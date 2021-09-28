package edu.brown.cs.student.api;

import com.google.gson.Gson;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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

//    for(String num: nums){
//      client.makeRequest(ClientRequestGenerator.getRequest(URIP1 + num +URIP2));
//    }
    User[] users;
    GsonParser gsonParser = new GsonParser();
    users = gsonParser.parseUser(client.makeRequest(ClientRequestGenerator.getRequest(URIP1 + "two" +URIP2)));
    Collections.addAll(userSet, users);
    for (User user: userSet){
      System.out.println(user.user_id);
    }
  }

  /**
   * Returns a list of all users
   */
  public void getUsers(){

  }
}
