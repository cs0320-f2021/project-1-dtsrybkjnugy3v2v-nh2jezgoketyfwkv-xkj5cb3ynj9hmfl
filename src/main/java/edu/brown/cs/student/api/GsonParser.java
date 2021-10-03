package edu.brown.cs.student.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import edu.brown.cs.student.kdtree.IKDInsertable;

import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GsonParser {
  public User[] parseUser(String userJson) {
    Gson gson = new Gson();
    // Catches errors that arise from potential type mismatches
    // If an error occurs at a given uri, skip and go to the next one
    try {
      return gson.fromJson(userJson, User[].class);
    } catch (JsonSyntaxException e) {
      return null;
    }
  }

  public Review[] parseReview(String reviewJson) {
    Gson gson = new Gson();
    // Catches errors that arise from potential type mismatches
    // If an error occurs at a given uri, skip and go to the next one
    try {
      return gson.fromJson(reviewJson, Review[].class);
    } catch (JsonSyntaxException e) {
      return null;
    }
  }

  public Rent[] parseRent(String reviewJson) {
    Gson gson = new Gson();
    // Catches errors that arise from potential type mismatches
    // If an error occurs at a given uri, skip and go to the next one
    try {
      return gson.fromJson(reviewJson, Rent[].class);
    } catch (JsonSyntaxException e) {
      return null;
    }
  }

  public List<IKDInsertable> openRentFile(String url) {
    try {
      Gson gson = new Gson();

      // create a reader
      Reader reader = Files.newBufferedReader(Paths.get(url));

      // convert JSON string to User object
      Rent[] rent = gson.fromJson(reader, Rent[].class);
      // close reader
      reader.close();
      return Arrays.asList(rent);

    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public List<IKDInsertable> openUserFile(String url) {
    try {
      Gson gson = new Gson();

      // create a reader
      Reader reader = Files.newBufferedReader(Paths.get(url));

      // convert JSON string to User object
      User[] users = gson.fromJson(reader, User[].class);
      // close reader
      reader.close();
      return Arrays.asList(users);

    } catch (Exception ex) {
      return null;
    }
  }


  public List<IKDInsertable> openReviewFile(String url) {
    try {
      Gson gson = new Gson();

      // create a reader
      Reader reader = Files.newBufferedReader(Paths.get(url));

      // convert JSON string to User object
      Review[] reviews = gson.fromJson(reader, Review[].class);
      // close reader
      reader.close();
      return Arrays.asList(reviews);

    } catch (Exception ex) {
      return null;
    }
  }
}
