package api;

import edu.brown.cs.student.api.GsonParser;
import edu.brown.cs.student.api.Rent;
import edu.brown.cs.student.api.RentData;
import edu.brown.cs.student.api.Review;
import edu.brown.cs.student.api.ReviewData;
import edu.brown.cs.student.api.User;
import edu.brown.cs.student.api.UserData;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class DataTests {
  @Test
  /**
   * Checks that the userData retrieved is what is expected
   * Tests against an api where we know the results. We use this small example, so that if it works
   * with a smaller dataset, it should also work with a larger one
   */
  public void getUsersFromApiTest(){
    UserData userData = new UserData();
    userData.getData();
    Set<User> userSet = userData.getSet();
    assertTrue(userSet.contains(new User(151944L)));
    assertTrue(userSet.contains(new User(154309L)));
    assertTrue(userSet.contains(new User(185966L)));
    assertTrue(userSet.contains(new User(273551L)));
    assertTrue(userSet.contains(new User(336066L)));
    assertTrue(userSet.contains(new User(391778L)));
    assertTrue(userSet.contains(new User(420272L)));
    assertTrue(userSet.contains(new User(499943L)));
    assertTrue(userSet.contains(new User(533900L)));
    assertTrue(userSet.contains(new User(721308L)));
    assertTrue(userSet.contains(new User(734848L)));
    assertTrue(userSet.contains(new User(829124L)));
    assertTrue(userSet.contains(new User(86661L)));
    assertTrue(userSet.contains(new User(87660L)));
    assertTrue(userSet.contains(new User(909926L)));
    assertSame(userSet.size(), 15);
  }

  @Test
  /**
   * Compares user data to a known file and and asserts they are the same
   * Ensures that the file parsing and api works correctly
   */
  public void compareUserDataToFile(){
    GsonParser gsonParser = new GsonParser();
    User[] users = gsonParser.openUserFile("data/project-1/justusersSMALL.json");
    UserData userData = new UserData();
    userData.getData();
    Set<User> userSet = userData.getSet();
    assertSame(userSet.size(), users.length);
    for(User user: users){
      assertTrue(userSet.contains(user));
    }
  }

  @Test
  /**
   * Compares review data to a known file and asserts they are the same
   * Ensures that the file parsing and api works correctly
   */
  public void compareReviewDataToFile(){
    GsonParser gsonParser = new GsonParser();
    Review[] reviews = gsonParser.openReviewFile("data/project-1/justreviewsSMALL.json");
    ReviewData reviewData = new ReviewData();
    reviewData.getData();
    Set<Review> reviewSet = reviewData.getSet();
    assertSame(reviewSet.size(), reviews.length);
    for(Review review: reviews){
      assertTrue(reviewSet.contains(review));
    }
  }

  @Test
  /**
   * Compares Rent data to a known file and asserts they are the same
   * Ensures that the file parsing and api works correctly
   */
  public void compareRentDataToFile(){
    GsonParser gsonParser = new GsonParser();
    Rent[] rents = gsonParser.openRentFile("data/project-1/justrentSMALL.json");
    RentData rentData = new RentData();
    rentData.getData();
    Set<Rent> rentSet = rentData.getSet();
    assertSame(rentSet.size(), rents.length);
    for(Rent rent: rents){
      assertTrue(rentSet.contains(rent));
    }
  }
}
