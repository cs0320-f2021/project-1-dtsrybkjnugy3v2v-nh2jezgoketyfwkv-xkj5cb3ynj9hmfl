package edu.brown.cs.student.repl;

import edu.brown.cs.student.stars.MathBot;

public class Subtract implements Command {

  @Override
  public String getCommand() {
    return "subtract";
  }

  @Override
  public void runCommand(String[] arguments) {
    MathBot mathBot = new MathBot();
    System.out.println(
        mathBot.subtract(Double.parseDouble(arguments[1]),
            Double.parseDouble(arguments[2])));
  }
}
