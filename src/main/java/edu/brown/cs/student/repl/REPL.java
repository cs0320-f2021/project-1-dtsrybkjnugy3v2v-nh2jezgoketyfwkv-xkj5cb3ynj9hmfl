package edu.brown.cs.student.repl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class REPL {

  /**
   * The REPL! instantiated by Main and does not need to be edited by other engineers.
   * Stores a map of executable commands.
   */
  public REPL() {
    HashMap<String, Command> commandsMap = this.mapCommands();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = input.split(" ");
          // finds which command to run based on the string input from the map and runs it
          commandsMap.get(arguments[0]).runCommand(arguments);
        } catch (Exception e) {
//          e.printStackTrace();
          System.out.println("ERROR: We couldn't process your input");
        }
      }
    } catch (Exception e) {
      System.out.println("ERROR: Invalid input for REPL");
    }
  }

  /**
   * Creates a hashmap of the commands that the repl recognizes and can run.
   * This is where other engineers can add/delete their own commands.
   * A hashset is used instead of a list so that the repl can easily
   * find which command to run based on the string input.
   * @return a hashmap of the commands to run
   */

  private HashMap<String, Command> mapCommands() {
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

    LoadResponses responseCommand = new LoadResponses();
    commands.put(responseCommand.getCommand(), responseCommand);

    PrintBest printCommand = new PrintBest();
    commands.put(printCommand.getCommand(), printCommand);

    Recommendations recommendationsCommand = new Recommendations(responseCommand);
    commands.put(recommendationsCommand.getCommand(), recommendationsCommand);
    return commands;
  }
}
