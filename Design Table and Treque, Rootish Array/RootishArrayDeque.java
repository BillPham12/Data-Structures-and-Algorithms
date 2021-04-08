package comp2402a2;

import java.util.AbstractList;
import java.util.List;
import java.util.ArrayList;

/**
 */
public class RootishArrayDeque<T> extends AbstractList<T> {
	/**
	 * You decide on the instance variables you need.
	 */

	public RootishArrayDeque(Class<T> t) {
		// Put your own code here
		throw new UnsupportedOperationException("Constructor not yet implemented");
	}

	public T get(int i) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		throw new UnsupportedOperationException("get(i) not yet implemented");
	}

	public T set(int i, T x) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		throw new UnsupportedOperationException("set(i,x) not yet implemented");
	}

	public void add(int i, T x) {
		if (i < 0 || i > size()) throw new IndexOutOfBoundsException();
		// Put your own code here
		throw new UnsupportedOperationException("add(i,x) not yet implemented");
	}

	public T remove(int i) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here
		throw new UnsupportedOperationException("size(i) not yet implemented");
	}

	public int size() {
		// Put your own code here
		throw new UnsupportedOperationException("size() not yet implemented");
	}

}
