package edu.brown.cs.student.repl;

public interface Command {
  String getCommand();
  void runCommand(String[] arguments);
}
