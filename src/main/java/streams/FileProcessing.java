package streams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

// library VAVR
interface ExFunction<A, R> {
  R apply(A a) throws Throwable;

  static <A, R> Function<A, Optional<R>> wrap(ExFunction<A, R> fn) {
    return a -> {
      try {
        return Optional.of(fn.apply(a));
      } catch (Throwable t) {
        return Optional.empty();
      }
    };
  }
}

public class FileProcessing {
//  public static Optional<Stream<String>> getContents(String filename) {
//    try {
//      return Optional.of(Files.lines(Paths.get(filename)));
//    } catch (Throwable  t) {
//      return Optional.empty();
//    }
//  }

  public static void main(String[] args) {
    Stream.of("A.txt", "B.txt", "C.txt")
//        .flatMap(fn -> Files.lines(Paths.get(fn)))
//        .flatMap(fn -> getContents(fn))
//        .map(fn -> getContents(fn))
        .map(ExFunction.wrap(fn -> Files.lines(Paths.get(fn))))
//        .peek(o -> {
//          if (! o.isPresent()) {
//            System.out.println("Problem");
//          }
//        })
        .filter(o -> o.isPresent())
        .flatMap(o -> o.get())
        .forEach(s -> System.out.println(s));

  }
}
