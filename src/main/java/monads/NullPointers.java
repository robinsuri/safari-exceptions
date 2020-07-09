package monads;

import java.util.HashMap;
import java.util.Map;

public class NullPointers {
  public static void main(String[] args) {
    Map<String, String> names = new HashMap<>();
    names.put("Fred", "Jones");

    // computation to find first name
    String first = "Fred";

    // Prepare letter introduction:
    String last = names.get(first);
    String upper = last.toUpperCase();
    String message = "Dear " + upper;

    System.out.println(message);
  }
}
