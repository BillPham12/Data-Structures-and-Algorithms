package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

 
public class Part6 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		// Your code goes here - see Part0 for an example
		Map<String,Integer> s = new HashMap<>();
		
		for (String line = r.readLine(); line != null; line = r.readLine()) {
			s.put(line,line.length());
        }
		Set<Entry<String,Integer>> s1 = s.entrySet();
		List<Entry<String,Integer>> list = new ArrayList<Entry<String,Integer>>(s1);
		Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
			public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2){
				if(o1.getValue().compareTo(o2.getValue()) == 0){
					return (o1.getKey()).compareTo(o2.getKey());}	
				else return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		
		for(Map.Entry<String,Integer> entry: list)
			w.println(entry.getKey());
		
		
	}

	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);				
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 10e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
