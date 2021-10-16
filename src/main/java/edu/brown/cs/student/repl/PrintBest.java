package edu.brown.cs.student.repl;

public class PrintBest implements Command {
  @Override
  public String getCommand() {
    return "printBest";
  }

  @Override
  public void runCommand(String[] arguments) {
    System.out.println("Air Ox");
  }
}
