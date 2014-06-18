public class Y extends X implements Inter{
	public int l;

	public static void main(String[] args){
		X x = new X();
		Y y = new Y();
		Y[] ya = new Y[4];
		X[] xa = new X[4];
		//ya = (Y[]) xa; 			// 1a. no compiler error 1b. run-time error
		y.testMethod();
	}
}