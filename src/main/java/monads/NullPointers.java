package monads;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NullPointers {
  public static void main(String[] args) {
    Map<String, String> names = new HashMap<>();
    names.put("Fred", "Jones");

    // computation to find first name
    String first = "Fred";

    // Prepare letter introduction:
    String last = names.get(first);
    if (last != null) {
      String upper = last.toUpperCase();
      String message = "Dear " + upper;
      System.out.println(message);
    }

    SuperIterable<Map<String, String>> simss = new SuperIterable<>(
        Arrays.asList(names)
    );

    simss
        .map(m -> m.get(first))
        .map(s -> s.toUpperCase())
        .map(s -> "Dear " + s)
        .forEach(s -> System.out.println(s));
    ;

    Optional.of(names)
        .map(m -> m.get(first))
        .map(s -> s.toUpperCase())
        .map(s -> "Dear " + s)
        .ifPresent(s -> System.out.println(s));
  }
}
