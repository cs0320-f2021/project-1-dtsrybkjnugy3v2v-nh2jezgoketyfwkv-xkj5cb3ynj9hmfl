package edu.brown.cs.student.api;

import com.google.gson.Gson;

public class GsonParser {
  public User[] parseUser(String userJson){
    Gson gson = new Gson();
    return gson.fromJson(userJson, User[].class);
  }
}
