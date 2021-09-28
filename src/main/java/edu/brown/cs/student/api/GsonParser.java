package edu.brown.cs.student.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonParser {
  public User[] parseUser(String userJson) {
    Gson gson = new Gson();
    // Catches errors that arise from potential type mismatches
    try {
      return gson.fromJson(userJson, User[].class);
    } catch (JsonSyntaxException e) {
      return null;
    }
  }

  public Review[] parseReview(String reviewJson) {
    Gson gson = new Gson();
    // Catches errors that arise from potential type mismatches
    try {
      return gson.fromJson(reviewJson, Review[].class);
    } catch (JsonSyntaxException e) {
      return null;
    }
  }
}
