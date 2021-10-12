package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.GsonParser;
import edu.brown.cs.student.kdtree.IKDInsertable;
import edu.brown.cs.student.kdtree.KDNode;
import edu.brown.cs.student.kdtree.KDTree;

import java.util.HashMap;
import java.util.List;

public class Users implements Command {
  private KDNode root;
  private HashMap<Integer, IKDInsertable> userHashMap;

  public Users() {
  }

  @Override
  public String getCommand() {
    return "users";
  }

  @Override
  public void runCommand(String[] arguments) throws IllegalAccessException {
    String url = arguments[1];
    GsonParser gsonParser = new GsonParser();
    List<IKDInsertable> users = gsonParser.openUserFile(url);
    KDTree userKDTree = new KDTree(users);
    this.root = userKDTree.getRoot();
    this.userHashMap = new HashMap<>();
    for (IKDInsertable user : users) {
      this.userHashMap.put(user.returnID(), user);
    }
  }

  public KDNode getRoot() {
    return this.root;
  }

  public HashMap<Integer, IKDInsertable> getHashMap() {
    return this.userHashMap;
  }
}
