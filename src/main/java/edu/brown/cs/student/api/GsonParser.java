package edu.brown.cs.student.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import edu.brown.cs.student.kdtree.Insertable;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class GsonParser {
  public UserResponse[] parseUserResponse(String userResponseJson) {
    Gson gson = new Gson();
    // Catches errors that arise from potential type mismatches
    // If an error occurs at a given uri, skip and go to the next one
    try {
      return gson.fromJson(userResponseJson, UserResponse[].class);
    } catch (JsonSyntaxException e) {
      return null;
    }
  }
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

  public List<Insertable> openRentFile(String url) {
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

  public List<Insertable> openUserFile(String url) {
    try {
      Gson gson = new Gson();

      // create a reader
      Reader reader = Files.newBufferedReader(Paths.get(url));

      // convert JSON string to User object
      User[] users = gson.fromJson(reader, User[].class);
      // close reader
      reader.close();
      for (Insertable user: users) {
        System.out.println(user.returnNumParams());
      }
      return Arrays.asList(users);

    } catch (Exception ex) {
      return null;
    }
  }


  public List<Insertable> openReviewFile(String url) {
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
