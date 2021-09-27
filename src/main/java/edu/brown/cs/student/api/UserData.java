package edu.brown.cs.student.api;

import java.net.http.HttpRequest;

public class UserData {
  String URI;
  public UserData(){
    URI = "https://runwayapi.herokuapp.com/users-two?auth=csims&key=" + ClientAuth.getApiKey();
  }
  public HttpRequest getOne(){
    return ClientRequestGenerator.getRequest(URI);
  }
}
