package edu.brown.cs.student.api;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {

  /**
   * @param reqUri the uri for the data we are requesting
   * @return an HttpRequest object for accessing json data at a given url
   */
  public static HttpRequest getRequest(String reqUri) {
    return HttpRequest.newBuilder()
        .uri(URI.create(reqUri))
        .build();
  }
  public static HttpRequest getPostRequest(String reqUri) {
    String apiKey = ClientAuth.getApiKey();
    return HttpRequest.newBuilder()
        .POST(HttpRequest.BodyPublishers.ofString("{\"auth\":\"" + "csims" + "\"}"))
        .header("x-api-key", apiKey)
        .uri(URI.create(reqUri))
        .build();
  }
}
