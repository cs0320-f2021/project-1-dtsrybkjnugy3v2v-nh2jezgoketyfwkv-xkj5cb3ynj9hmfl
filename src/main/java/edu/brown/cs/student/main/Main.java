package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import freemarker.template.Configuration;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

  // use port 4567 by default when running server
  private static final int DEFAULT_PORT = 4567;
  private HashMap<String, Star> _stars;
  private String _file;
  //private HashMap<Star, String> _starsHM;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {
    // set up parsing of command line flags
    OptionParser parser = new OptionParser();

    // "./run --gui" will start a web server
    parser.accepts("gui");

    // use "--port <n>" to specify what port on which the server runs
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
        .defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);
    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = input.split(" ");
          MathBot mathBot = new MathBot();

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
              _file = arguments[1];
              _stars = this.createStarList(_file);
              break;
            case "naive_neighbors":
              if (arguments.length == 5 && arguments[2].charAt(0) != '"') {
                int numNeighbors = Integer.parseInt(arguments[1]);
                System.out.println("Read " + _stars.size() + " from " + _file);
                double x = Double.parseDouble(arguments[2]);
                double y = Double.parseDouble(arguments[3]);
                double z = Double.parseDouble(arguments[4]);
                NeighborCalculator neighborCalc = new NeighborCalculator(_stars);
                neighborCalc.nearest(numNeighbors, x, y, z);
              } else if (arguments.length >= 3) {
                System.out.println("Read " + _stars.size() + " stars" + " from " + _file);
                int numNeighbors = Integer.parseInt(arguments[1]);
                StringBuilder starName = new StringBuilder();
                // Concatenates name
                for (int i = 2; i < arguments.length; i++) {
                  starName.append(" ").append(arguments[i]);
                }
                NeighborCalculator neighborCalc = new NeighborCalculator(_stars);
                neighborCalc.nearest(numNeighbors, starName.substring(2,
                    starName.toString().length() - 1).strip());
              }
              break;
            default:
              System.out.println("ERROR: Invalid input for REPL");
          }

        } catch (Exception e) {
          System.out.println("ERROR: We couldn't process your input");
        }
      }
    } catch (Exception e) {
      System.out.println("ERROR: Invalid input for REPL");
    }
  }


  private HashMap<String, Star> createStarList(String file) {
    _stars = new HashMap<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String input;
      br.readLine();
      while ((input = br.readLine()) != null) {
        input = input.trim();
        String[] arguments = input.split(",");
        if (arguments[1].length() == 0) {
          arguments[1] = "Star ID: " + arguments[0];
        }
        _stars.put(arguments[1], new Star(arguments[0], arguments[1],
            arguments[2], arguments[3], arguments[4]));
      }
    } catch (Exception e) {
      System.out.println("ERROR: File not found");
    }
    return _stars;
  }

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration(Configuration.VERSION_2_3_0);

    // this is the directory where FreeMarker templates are placed
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n",
          templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  private void runSparkServer(int port) {
    // set port to run the server on
    Spark.port(port);

    // specify location of static resources (HTML, CSS, JS, images, etc.)
    Spark.externalStaticFileLocation("src/main/resources/static");

    // when there's a server error, use ExceptionPrinter to display error on GUI
    Spark.exception(Exception.class, new ExceptionPrinter());

    // initialize FreeMarker template engine (converts .ftl templates to HTML)
    FreeMarkerEngine freeMarker = createEngine();

    // setup Spark Routes
    Spark.get("/", new MainHandler(), freeMarker);
  }

  /**
   * Display an error page when an exception occurs in the server.
   */
  private static class ExceptionPrinter implements ExceptionHandler<Exception> {
    @Override
    public void handle(Exception e, Request req, Response res) {
      // status 500 generally means there was an internal server error
      res.status(500);

      // write stack trace to GUI
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }

  /**
   * A handler to serve the site's main page.
   *
   * @return ModelAndView to render.
   * (main.ftl).
   */
  private static class MainHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      // this is a map of variables that are used in the FreeMarker template
      Map<String, Object> variables = ImmutableMap.of("title",
          "Go go GUI");

      return new ModelAndView(variables, "main.ftl");
    }
  }
}
