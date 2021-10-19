package edu.brown.cs.student.repl;

public class GenerateGroups implements Command {
  private Recommendations recCommand;
  public GenerateGroups(Recommendations recCommand) {
    this.recCommand = recCommand;
  }
  @Override
  public String getCommand() {
    return "recsys_gen_groups";
  }

  @Override
  public void runCommand(String[] arguments) {

  }
}
