package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.GsonParser;
import edu.brown.cs.student.api.RentData;
import edu.brown.cs.student.api.ReviewData;
import edu.brown.cs.student.api.User;
import edu.brown.cs.student.api.UserData;
import edu.brown.cs.student.api.UserResponseData;
import edu.brown.cs.student.kdtree.IKDInsertable;
import edu.brown.cs.student.kdtree.KDCalculator;
import edu.brown.cs.student.kdtree.KDNode;
import edu.brown.cs.student.kdtree.KDTree;
import edu.brown.cs.student.stars.MathBot;
import edu.brown.cs.student.stars.NeighborCalculator;
import edu.brown.cs.student.stars.Star;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class REPL {

  private HashMap<String, Star> stars;
  private String file;
  private KDNode<User> root;
  private HashMap<Integer, IKDInsertable> userHashMap;
  public REPL() {
    // Set up global variables
    String url;
    GsonParser gsonParser = new GsonParser();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = input.split(" ");
          MathBot mathBot = new MathBot();

          for (Command command: this.listCommands()) {
            if (command.getCommand().equals(arguments[0])) {
              command.runCommand(arguments);
            }

          }
          switch (arguments[0]) {
            case "add":
              System.out.println(
                  mathBot.add(Double.parseDouble(arguments[1]),
                      Double.parseDouble(arguments[2])));
              break;
            case "subtract":
              System.out.println(
                  mathBot.subtract(Double.parseDouble(arguments[1]),
                      Double.parseDouble(arguments[2])));
              break;
            case "stars":
              this.file = arguments[1];
              this.stars = this.createStarList(file);
              break;
            case "naive_neighbors":
              if (arguments.length == 5 && arguments[2].charAt(0) != '"') {
                int numNeighbors = Integer.parseInt(arguments[1]);
                System.out.println("Read " + this.stars.size() + " from " + this.file);
                double x = Double.parseDouble(arguments[2]);
                double y = Double.parseDouble(arguments[3]);
                double z = Double.parseDouble(arguments[4]);
                NeighborCalculator neighborCalc = new NeighborCalculator(this.stars);
                neighborCalc.nearest(numNeighbors, x, y, z);
              } else if (arguments.length >= 3) {
                System.out.println("Read " + this.stars.size() + " stars" + " from " + this.file);
                int numNeighbors = Integer.parseInt(arguments[1]);
                StringBuilder starName = new StringBuilder();
                // Concatenates name
                for (int i = 2; i < arguments.length; i++) {
                  starName.append(" ").append(arguments[i]);
                }
                NeighborCalculator neighborCalc = new NeighborCalculator(this.stars);
                neighborCalc.nearest(numNeighbors, starName.substring(2,
                    starName.toString().length() - 1).strip());
              }
              break;
            case "userGet":
              UserData userData = new UserData();
              userData.getData();
              //client.makeRequest(userData.getData());
              //client.makeRequest(ClientRequestGenerator.getIntroGetRequest("https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/introResource"));
              break;
            case "reviewGet":
              ReviewData reviewData = new ReviewData();
              reviewData.getData();
              break;
            case "responseGet":
              UserResponseData userResponseData = new UserResponseData();
              userResponseData.getData();
              break;
            // Gathers rent objects from the api data
            case "rentGet":
              RentData rentData = new RentData();
              rentData.getData();
              break;
            case "users":
              url = arguments[1];
              List<IKDInsertable> users = gsonParser.openUserFile(url);
              KDTree userKDTree = new KDTree(users);
              //userKDTree.treeGenerator();
              this.root = userKDTree.getRoot();
              this.userHashMap = new HashMap<>();
              for (IKDInsertable user: users) {
                this.userHashMap.put(user.returnID(), user);
              }
              break;
            // retrieves rent data at a given file location
            case "rent":
              url = arguments[1];
              List<IKDInsertable> rents = gsonParser.openRentFile(url);
              KDTree rentKDTree = new KDTree(rents);
              for (IKDInsertable rent: rents) {
                System.out.println(rent.toString());
              }
              break;
            case "reviews":
              url = arguments[1];
              List<IKDInsertable> reviews = gsonParser.openReviewFile(url);
              for (IKDInsertable review: reviews) {
                System.out.println(review.toString());
              }
              break;
            case "similar":
              KDCalculator kdCalc = new KDCalculator();
              int numNeighbors = Integer.parseInt(arguments[1]);
              if (arguments.length == 3) {
                int targetUserID = Integer.parseInt(arguments[2]);
                User targetUser = (User) this.userHashMap.get(targetUserID);
                kdCalc.findNearestNeighbors(numNeighbors, targetUser, this.root);
                for (KDNode<?> neighbor: kdCalc.getNeighbors()) {
                  System.out.println(neighbor.getUserID());
                }
              } else if (arguments.length == 4) {
                double targetWeight = Double.parseDouble(arguments[2]);
                double targetAge = Double.parseDouble(arguments[3]);
                kdCalc.findNearestNeighbors(numNeighbors, targetWeight, targetAge, this.root);
                for (KDNode<?> neighbor: kdCalc.getNeighbors()) {
                  System.out.println(neighbor.getUserID());
                }
              } else {
                System.out.println("ERROR: Invalid input for REPL");
              }
              break;
            case "classify":
              KDCalculator kdCalc2 = new KDCalculator();
              int numNeighbors2 = Integer.parseInt(arguments[1]);
              if (arguments.length == 3) {
                int targetUserID = Integer.parseInt(arguments[2]);
                User targetUser = (User) this.userHashMap.get(targetUserID);
                kdCalc2.classifyUsers(numNeighbors2, targetUser, this.root);
              } else if (arguments.length == 4) {
                double targetWeight = Double.parseDouble(arguments[2]);
                double targetAge = Double.parseDouble(arguments[3]);
                kdCalc2.classifyUsers(numNeighbors2, targetWeight, targetAge, this.root);
              } else {
                System.out.println("ERROR: Invalid input for REPL");
              }
              break;
            default:
              System.out.println("ERROR: Invalid input for REPL");
          }

        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("ERROR: We couldn't process your input");
        }
      }
    } catch (Exception e) {
      System.out.println("ERROR: Invalid input for REPL");
    }
  }

  private List<Command> listCommands() {
    ArrayList<Command> commands = new ArrayList<>();
    Users users = new Users();
    commands.add(users);
    return commands;
  }

  private HashMap<String, Star> createStarList(String starFile) {
    this.stars = new HashMap<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(starFile));
      String input;
      br.readLine();
      while ((input = br.readLine()) != null) {
        input = input.trim();
        String[] arguments = input.split(",");
        if (arguments[1].length() == 0) {
          arguments[1] = "Star ID: " + arguments[0];
        }
        this.stars.put(arguments[1], new Star(arguments[0], arguments[1],
            arguments[2], arguments[3], arguments[4]));
      }
    } catch (Exception e) {
      System.out.println("ERROR: File not found");
    }
    return this.stars;
  }
}
