package monads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  // Functor
  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> res = new ArrayList<>();
    self.forEach(e -> res.add(op.apply(e)));
    return new SuperIterable<>(res);
  }

  public Iterator<E> iterator() {
    return self.iterator();
  }
}
