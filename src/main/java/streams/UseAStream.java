package streams;

import java.util.stream.Stream;

public class UseAStream {
  public static void main(String[] args) {
    Stream.of(
        new Student("Fred", "Math", "Physics"),
        new Student("Jim", "Art"),
        new Student("Sheila", "Math", "Physics", "Quantum Mechanics")
    )
        .flatMap(s -> s.getClasses().stream())
        .forEach(s -> System.out.println(s));
  }
}
