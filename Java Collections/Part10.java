package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

 
public class Part10 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		// Your code goes here - see Part0 for an example
		

		Deque<String> s = new ArrayDeque<>();
		
		for (String line = r.readLine(); line != null; line = r.readLine()) {
			s.addLast(line);
		}
		List<String> result = new ArrayList<>();
		while(s.size() != 0){
		int checking = 0;
		String hello = s.getFirst();
		int index = 0;
		int a = 0;
		for(String text:s){
			if(text.compareTo(hello) > 0){ 
				checking = text.length();
				hello = text;
				index = a;
				}
			a++;	
			}
		result.add(hello);
		
		for(int i=0; i <= index;i++){
			s.removeFirst();
			}
			
		}
		
		
		for(String text: result)
			w.println(text);
	
		
		
		

		
		
		
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
