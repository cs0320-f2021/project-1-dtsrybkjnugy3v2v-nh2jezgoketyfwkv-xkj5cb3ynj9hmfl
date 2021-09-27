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
    // The resource we want is hosted at https://cq2gahtw4j.execute-api.us-east-1.amazonaws.com/.
    // See https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html and
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.Builder.html
    return HttpRequest.newBuilder()
        .uri(URI.create(reqUri))
        .build();
  }
}
