package edu.brown.cs.student.repl;

import edu.brown.cs.student.api.UserResponseData;

public class ResponseGet implements Command {
  @Override
  public String getCommand() {
    return "responseGet";
  }

  @Override
  public void runCommand(String[] arguments) {
    UserResponseData userResponseData = new UserResponseData();
    userResponseData.getData();
  }
}
