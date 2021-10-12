package edu.brown.cs.student.repl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class REPL {
  public REPL() {
    HashMap<String, Command> commandsMap = this.listCommands();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = input.split(" ");
          commandsMap.get(arguments[0]).runCommand(arguments);
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("ERROR: We couldn't process your input");
        }
      }
    } catch (Exception e) {
      System.out.println("ERROR: Invalid input for REPL");
    }
  }

  private HashMap<String, Command> listCommands() {
    HashMap<String, Command> commands = new HashMap<>();
    Users usersCommand = new Users();
    commands.put(usersCommand.getCommand(), usersCommand);
    Similar similarCommand = new Similar(usersCommand);
    commands.put(similarCommand.getCommand(), similarCommand);
    Classify classifyCommand = new Classify(usersCommand);
    commands.put(classifyCommand.getCommand(), classifyCommand);
    Add addCommand = new Add();
    commands.put(addCommand.getCommand(), addCommand);
    Subtract subtractCommand = new Subtract();
    commands.put(subtractCommand.getCommand(), subtractCommand);
    ResponseGet responseCommand = new ResponseGet();
    commands.put(responseCommand.getCommand(), responseCommand);
    return commands;
  }
}
