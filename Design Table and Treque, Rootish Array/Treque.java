	package comp2402a2;

	import java.util.AbstractList;
	import java.util.List;
	import java.util.Collections;
	import java.util.*;

	/**
	 */
	public class Treque<T> extends AbstractList<T> {
		protected Factory<T> f;

        /**
         * the front "half" of the deque
         */
        protected List<T> front;
        /**
         * the back "half" of the deque
         */
        protected List<T> back;


        /**
         * Create a new empty List data structure.
         * <p>
         * Subclasses should override this if they want to use
         * different structures for front and back.
         *
         * @return
         */
        protected List<T> newStack(Class<T> t)
        {
            return new ArrayDeque<T>(t);
        }

        /**
         * Constructor
         *
         * @param t0 the class of the objects stored in this list
         */
        public Treque(Class<T> t) {
            f = new Factory<T>(t);
            front = newStack(t);
            back = newStack(t);
        }

    public T get(int i) {
        if (i < front.size()) {
            return front.get(front.size()-i-1);
        } else {
            return back.get(i-front.size());
        }
    }

    public T set(int i, T x) {
        if (i < front.size()) {
            return front.set(front.size()-i-1, x);

        } else {
            return back.set(i-front.size(), x);
        }
    }

    public void add(int i, T x) {
        if (i < front.size()) {
            front.add(front.size()-i, x);
        } else {
            back.add(i-front.size(), x);
        }
        balance();
    }

    /**
     * Rebalance the elements between front and back
     * if necessary
     */
    protected void balance() {
        int n = size();
        if (41*front.size()/40 < back.size()) {
            int s = n/2 - front.size();
            List<T> l1 = newStack(f.type());
            List<T> l2 = newStack(f.type());
            l1.addAll(back.subList(0,s));
            Collections.reverse(l1);
            l1.addAll(front);
            l2.addAll(back.subList(s, back.size()));
            front = l1;
            back = l2;
        } else if (41*back.size()/40 < front.size()) {
            int s = front.size() - n/2;
            List<T> l1 = newStack(f.type());
            List<T> l2 = newStack(f.type());
            l1.addAll(front.subList(s, front.size()));
            l2.addAll(front.subList(0, s));
            Collections.reverse(l2);
            l2.addAll(back);
            front = l1;
            back = l2;
        }
    }

    public T remove(int i) {
        T x;
        if (i < front.size()) {
            x = front.remove(front.size()-i-1);
        } else {
            x = back.remove(i-front.size());
        }
        balance();
        return x;
    }

    public int size() {
        return front.size() + back.size();
    }


	}
