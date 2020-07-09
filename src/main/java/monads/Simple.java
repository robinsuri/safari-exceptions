package monads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Simple {
  public static <E, F> List<F> map(Iterable<E> ls, Function<E, F> op) {
    List<F> res = new ArrayList<>();
    for (E s : ls) {
      F r = op.apply(s);
//      String r = op(s);
      res.add(r);
    }
    return res;
  }

  public static void main(String[] args) {
    List<String> ls = Arrays.asList("Fred", "Jim", "Sheila");
    // Dear Fred
    List<String> salut = map(ls, s -> "Dear " + s);
    for (String s : salut) {
      System.out.println("> " + s);
    }

    List<Integer> sizes = map(ls, s -> s.length());
    for (Integer i : sizes) {
      System.out.println(">> " + i);
    }
  }
}
