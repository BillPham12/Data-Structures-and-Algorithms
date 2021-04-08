package comp2402a2;
import java.util.List;


/**
	This class should orchestrate the testing of each part of the assignment.	
	Testing should include correctness testing and efficiency testing.
	The testing methods below (e.g. testPart1()) should return false if the provided List is not correct/efficient.
	If the provided List is correct and efficient the method should return true within a short amount of time.
	
	Correctness testing involves verifying that all of the required operations are implemented and behave correctly. 
	E.g. If your class implements the list interface does it work the same as any other list? 
	A good way to test is to do the same operations on both your implementation and a known correct list implementation.
		An incorrect implementation will result in a different state of stored data.
	
	Efficiency testing involves verifying that the operations are implemented efficiently (according to the assignment guidelines). 
	E.g. If an operation is supposed to run in constant time then it should be fast regardless of the size of your data structure.
	A good way to test is to do lots of fast operations and ensure they are completed within a short time.
		An inefficient implementation will take a long time to complete operations that should be fast.
		
	Note: the server will impose its own time limit on your tests, but you can do your own timing using the Stopwatch class:
	Stopwatch s = new Stopwatch();
	System.out.println("Starting test: ");
	s.start();
	... do some testing ... 
	s.stop();
	System.out.println("Done in " + s.elapsedSeconds() + " seconds");
	
 */
public class Tester {
	
	public static boolean testPart1(List<Integer> ell) {
		//do correctness tests on Treque
		//do efficiency tests on Treque
		return true;
	}

	public static boolean testPart2(List<Integer> ell) {
		//do correctness tests on RootishArrayDeque
		//doo efficiency tests on RootishArrayDeque
		return true;
	}

	public static boolean testPart3(AbstractTable<Integer> ell) {
		return true;
	}
	
	public static void main(String[] args) {
        List<Integer> tr = new Treque<Integer>(Integer.class);
        System.out.println("testPart1 returns " + testPart1(tr));

        List<Integer> rad = new RootishArrayDeque<Integer>(Integer.class);
        System.out.println("testPart2 returns " + testPart2(rad));

		Table<Integer> tbl = new Table<>(Integer.class);
		System.out.println("testPart3 returns " + testPart3(tbl));
    }

}
