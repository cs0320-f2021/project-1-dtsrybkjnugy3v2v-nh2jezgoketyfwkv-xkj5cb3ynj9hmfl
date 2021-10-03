package edu.brown.cs.student.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is used for collecting user data and combining users.
 */
public class RentData {
  // These are used for concatenating URIs together for get requests
  private final String url1; //part 1
  private final String url2; //part 2
  private final List<String> nums;
  private final Set<Rent> rentSet = new HashSet<>();

  public RentData() {
    nums = Arrays.asList("one", "two", "three", "four", "five");
    url1 = "https://runwayapi.herokuapp.com/rent-";
    url2 = "?auth=csims&key=" + ClientAuth.getApiKey();
  }

  /**
   * Stores users in an array.
   */
  public void getData() {
    ApiClient client = new ApiClient();

    // Cycles through all URIs so we can build a list of Users
    for (String num: nums) {
      GsonParser gsonParser = new GsonParser();
      Rent[] rents = gsonParser.parseRent(client.makeRequest(ClientRequestGenerator.getRequest(
          url1 + num + url2)));
      if (rents != null) {
        Collections.addAll(this.rentSet, rents);
      }
    }
    // Removes the null rent from the set
    Rent nullRent = new Rent();
    rentSet.remove(nullRent);
  }

  /**
   * Returns a list of all users.
   * @return a set of Users
   */
  public Set<Rent> getSet() {
    return rentSet;
  }
}
