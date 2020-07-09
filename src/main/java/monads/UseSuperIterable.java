package monads;

import java.util.Arrays;

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(
        Arrays.asList("Fred", "Jim", "Sheila")
    );

//    for (String s : sis) {
//      System.out.println("> " + s);
//    }

    sis
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println("> " + s));


  }
}
