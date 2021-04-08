package comp2402a5;
import java.lang.reflect.Array;
import java.util.*;

/**
 * An unfinished implementation of an Countdown tree (for exercises)
 * @author morin
 *
 * @param <T>
 */
public class CountdownTree<T> extends
        BinarySearchTree<CountdownTree.Node<T>, T> implements SSet<T> {

    // countdown delay factor
    double d;

    public static class Node<T> extends BSTNode<Node<T>,T> {
        int timer;  // the height of the node
    }

    public CountdownTree(double d) {
        this.d = d;
        sampleNode = new Node<T>();
        c = new DefaultComparator<T>();
    }
    int num;
    public boolean add(T x) {
        Node<T> u = new Node<T>();
        u.timer = (int)Math.ceil(d);
        u.x = x;
        if (super.add(u)) {
            // add some code here
            Node<T> a = u.parent;
            int i =0;
            while(true){
                if(a == null)
                    break;
                else{
                    a.timer--;
                    if(a.timer == 0){
                        num = size(a);
                        explode(a);
                    }
                    a = a.parent;
                }
            }
            return true;
        }
        return false;
    }

    public void splice(Node<T> u) {
        Node<T> w = u.parent;
        super.splice(u);
        // add some code here (we just removed u from the tree)

    }

    protected void explode(Node<T> u) {
        // Write this code to explode u
        // Make sure to update u.parent and/or r (the tree root) as appropriate
        int ns = size(u);
        Node<T> p = u.parent;
        Node<T>[] a = (Node<T>[]) Array.newInstance(Node.class, ns);
        packIntoArray(u, a, 0);
        if (p == nil) {
            r = buildBalanced(a, 0, ns);
            r.parent = nil;
        } else if (p.right == u) {
            p.right = buildBalanced(a, 0, ns);
            p.right.parent = p;
        } else {
            p.left = buildBalanced(a, 0, ns);
            p.left.parent = p;
        }
    }

    private Node<T> buildBalanced(Node<T>[] a, int i, int ns) {
        if (ns == 0)
            return nil;
        int m = ns / 2;
        a[i + m].left = buildBalanced(a, i, m);
        if (a[i + m].left != nil){
            a[i + m].left.parent = a[i + m];}
        a[i + m].right = buildBalanced(a, i + m + 1, ns - m - 1);
        if (a[i + m].right != nil){
            a[i + m].right.parent = a[i + m];}
        return a[i + m];
    }
    private int packIntoArray(Node<T> u, Node<T>[] a, int i) {
        if (u == nil) {
            return i;
        }
        u.timer = (int)Math.ceil(num*d);
        i = packIntoArray(u.left, a, i);
        a[i++] = u;
        return packIntoArray(u.right, a, i);
    }

    // Here is some test code you can use
    public static void main(String[] args) {
        Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(1)), 1000);
        Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)), 1000);
        Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)), 1000);

        java.util.List<SortedSet<Integer>> ell = new java.util.ArrayList<SortedSet<Integer>>();
        ell.add(new java.util.TreeSet<Integer>());
        ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(1)));
        ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)));
        ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)));
        Testum.sortedSetSpeedTests(ell, 1000000);
    }
}
