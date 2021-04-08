import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.*;

/**
 *  This is just a copy of A2Table, your job is to make it faster
 */
public class FasterTable<T> implements Table<T> {
    int nrows ;
    List<List<T>> tab;
    List<Integer> public_index;
    Hashtable<Integer,Integer> hello;
    int ncols;

    public FasterTable(Class<T> t) {
        public_index = new ArrayList<>();
        hello = new Hashtable<Integer,Integer>();
        ncols = 0;
        nrows = 0;
        tab = new ArrayList<List<T>>();
    }

    public int rows() {
        return nrows;
    }

    public int cols() {
        return ncols;
    }

    public T get(int i, int j) {
        if (i < 0 || i > rows() - 1 || j < 0 || j > cols()-1) throw new IndexOutOfBoundsException();
        //T x = tab.get(i).get(hello.get(j));
        T x= tab.get(i).get(public_index.indexOf(j));
        return x;
    }

    public T set(int i, int j, T x) {
        if (i < 0 || i > rows() - 1 || j < 0 || j > cols()-1) throw new IndexOutOfBoundsException();
        return tab.get(i).set(public_index.indexOf(j),x);

    }

    public void addRow(int i) {
        if (i < 0 || i > rows()) throw new IndexOutOfBoundsException();
        nrows++;
        List<T> row = new ArrayList<T>();
        for (int j = 0; j < cols(); j++){
            row.add(null);
        }
        tab.add(i,row);
    }

    public void removeRow(int i) {
        if (i < 0 || i > rows() - 1) throw new IndexOutOfBoundsException();
        nrows--;
        tab.remove(i);
    }

    public void addCol(int j) {
        // this method is too slow!
        if (j < 0 || j > cols()) throw new IndexOutOfBoundsException();
        ncols++;
        public_index.add(j);
        if(rows() > 0) {
            for (int i = 0; i < rows(); i++) {
                tab.get(i).add(null);
            }
            if (j < cols() - 1) {
                for (int i = j; i < cols() - 1; i++)
                    if (public_index.get(i) >= j && i != cols()-2)
                        public_index.set(i, public_index.get(i+1));
                    else if (i == cols()-2)
                        public_index.set(i,public_index.get(i)+1);
            }
        }
        //hello = new Hashtable<>();
       // for(int i =0; i < public_index.size();i++)
        //    hello.put(public_index.get(i),i);
    }

    public void removeCol(int j) {
        // this method is too slow!
        if (j < 0 || j > cols() - 1) throw new IndexOutOfBoundsException();
        ncols--;
        if(ncols == 0) {
            for(int i =0; i <rows();i++) {
                List<T> hello = new ArrayList<>();
                tab.set(i,hello);
            }
            public_index = new ArrayList<>();
        }
        else if(j == ncols+1){
            for(int i =0; i < rows();i++)
                tab.get(i).remove(j);
            public_index.remove(public_index.size()-1);
        }
        else if (j == 0){
            public_index.remove(public_index.size()-1);
            for(int i =0; i < rows();i++)
                tab.get(i).set(j,null);
        }
        else {
            for (int i = public_index.size() - 1; i > j; i--) {
                if (public_index.get(i) == j)
                    public_index.set(i, Integer.MAX_VALUE);
                else if (public_index.get(i) > j && i !=0)
                    public_index.set(i, public_index.get(i-1));
                else if (i ==0)
                    public_index.set(0,public_index.get(i)-1);
            }
        }
        //hello = new Hashtable<>();
        //for(int i =0; i < public_index.size();i++)
        //    hello.put(public_index.get(i),i);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                sb.append(String.valueOf(get(i, j)));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int nrows = 1, ncols = 50000;
        Table<Integer> t = new FasterTable<Integer>(Integer.class);
        Table<Integer> t2 = new A2Table<Integer>(Integer.class);
        System.out.println(Tester.testPart1(t));
        long start = System.nanoTime();
        for(int i =0; i < nrows; i++)
            t.addRow(t.rows());
        for(int i =0; i <ncols;i++)
            t.addCol(0);
        //for(int i =0; i < ncols/2;i++)
         //   t.get(0,i);
        long stop = System.nanoTime();
        System.out.println((stop-start)/1e9);

        start = System.nanoTime();
        for(int i =0; i < nrows; i++)
            t2.addRow(t2.rows());
        for(int i =0; i <ncols;i++)
            t2.addCol(0);
        //for(int i =0; i < ncols/2;i++)
        //    t2.get(0,i);
        stop = System.nanoTime();
        System.out.println((stop-start)/1e9);



    }
}
