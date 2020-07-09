package handlecause;

// left => bad (sinistra)
// right => good

import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Stream;

interface ExFunction<A, B> {
  B apply(A a) throws Throwable;
}

// "Union" type...
class Either<L, R> {
  private L left;
  private R right;

  private Either(L left, R right) {
    this.left = left;
    this.right = right;
  }

  public static <L, R> Either<L, R> success(R r) {
    return new Either<>(null, r);
  }

  public static <L, R> Either<L, R> failure(L l) {
    return new Either<>(l, null);
  }

  public boolean isLeft() {
    return left != null;
  }

  public boolean isRight() {
    return left == null;
  }

  public R get() {
    if (left == null) {
      return right;
    } else {
      throw new NoSuchElementException();
    }
  }

  public void reportError(PrintStream os) {
    if (left != null) {
      os.println(left);
    }
  }

  public <R1> Either<L, R1> map(Function<R, R1> op) {
    if (left == null) {
      return Either.success(op.apply(right));
    } else {
      return Either.failure(left);
    }
  }

  // Note: recover is kinda flatMapOfLeft
  public <L1> Either<L1, R> recover(Function<L, Either<L1, R>> op) {
    if (left == null) {
      return Either.success(right);
    } else {
      return op.apply(left);
    }
  }

  public static <A, B> Function<A, Either<Throwable, B>> wrap(ExFunction<A, B> op) {
    return a -> {
      try {
        return Either.success(op.apply(a));
      } catch (Throwable t) {
        return Either.failure(t);
      }
    };
  }
}

public class UsingEither {
  public static void main(String[] args) {
    Stream.of("A.txt", "B.txt", "C.txt")
        .map(Either.wrap(fn -> Files.lines(Paths.get(fn))))
        .peek(e -> e.reportError(System.out))
        .map(e -> e.recover(Either.wrap(t -> Files.lines(Paths.get("B2.txt")))))
        .filter(e -> e.isRight())
        .flatMap(e -> e.get())
        .forEach(s -> System.out.println(s));

  }
}
