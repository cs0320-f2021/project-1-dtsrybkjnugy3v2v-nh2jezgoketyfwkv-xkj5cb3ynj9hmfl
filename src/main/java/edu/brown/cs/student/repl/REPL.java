package edu.brown.cs.student.repl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class REPL {
  public REPL() {
    List<Command> commandsList = this.listCommands();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = input.split(" ");
          // todo : hashmap better than list
          for (Command command : commandsList) {
            if (command.getCommand().equals(arguments[0])) {
              command.runCommand(arguments);
            }
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
    Users usersCommand = new Users();
    commands.add(usersCommand);
    commands.add(new Similar(usersCommand));
    commands.add(new Classify(usersCommand));
    commands.add(new Add());
    commands.add(new Subtract());
    commands.add(new ResponseGet());
    return commands;
  }
}
