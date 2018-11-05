public class TestBalls {
	public static void main(String[] args) {
	    int[] coord = {1,1,10,10};
	    Balls test = new Balls(coord);
		test.translate(2, 5);
		System.out.println(test);
		test.reInit();
		System.out.println(test);
	}
}
