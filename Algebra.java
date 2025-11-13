// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		if (x2 < 0) { // check if it's x1 + (-x2). 
			for (int i = 0; i > x2; i--){
				x1--;
			}

			return x1;
		}

		else { 
			for (int i = 0; i < x2; i++){
				x1++;
			}

			return x1;
		}
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		if (x2 < 0) { // check if it's x1 - (-x2). 
			for (int i = 0; i > x2; i--){
				x1++;
			}

			return x1;
		}

		else { 
			for (int i = 0; i < x2; i++){
				x1--;
			}

			return x1;
		}
	}


	// Returns x1 * x2
	public static int times(int x1, int x2) {
		if (x1 == 0 || x2 == 0) { // if one of the parameters is 0.
			return 0;
		}

		if (x1 < 0 && x2 < 0) { // if both of the parameters are less then 0.
			x1 = -x1;
			x2 = -x2;
			int temp = x1;

			for (int i = 1; i < x2; i++){
				x1 = plus(x1, temp);
			}

			return x1;
		}
		else if (x1 < 0 && x2 > 0) { // if x1 is less then 0 and x2 is not.
			int temp = x1;

			for (int i = 1; i < x2; i++){
				x1 = plus(x1, temp);
			}

			return x1;
		}
		else if (x1 > 0 && x2 < 0) { // if x2 is less then 0 and x1 is not.
			int temp = -x1;
			for (int i = 0; i >= x2; i--){
				x1 = plus(x1, temp);
			}

			return x1;
			
		}
		else { // x1, x2 > 0.
			int temp = x1;

			for (int i = 1; i < x2; i++){
				x1 = plus(x1, temp);
			}

			return x1;
		} 
	}

	

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {

		if (n == 0) {
			return 1;
		}

		int temp = x;
		
		for (int i = 1; i < n; i++){
			x = times(x, temp);
		}

		return x;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		if (x1 >= 0 && x2 > 0) {

			if (x1 == 0) {
				return 0;
			}
			else {
				int counter = 1;
				int temp = x2;

				while (x2 < x1) {
					x2 = plus(x2, temp);

					if (x2 <= x1) {
						counter++;
					}
				}
				return counter;
			}

		}
		else if (x1 < 0 && x2 < 0) {
			x1 = -x1;
			x2 = -x2;
			int counter = 1;
			int temp = x2;

			while (x2 < x1) {
				x2 = plus(x2, temp);
				counter++;
			}
			return counter;
		}
		else if (x1 < 0 && x2 > 0) {
			x1 = -x1;
			int counter = 1;
			int temp = x2;

			while (x2 < x1) {
				x2 = plus(x2, temp);
				counter++;
			}
			return -counter;
		}
		else {
			x2 = -x2;
			int counter = 1;
			int temp = x2;

			while (x2 < x1) {
				x2 = plus(x2, temp);
				counter++;
			}
			return -counter;
		}	
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int div = div(x1, x2);
		int sum = times(div, x2);
		int mod = minus(x1, sum);

		return mod;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		for (int i = 0; i <= x; i++){
			if (times(i, i) == x) {
				return i;
			}
			if (times(i, i) < x && times(plus(i, 1), plus(i, 1)) > x) {
				return i;
			}
		}
		return 0;
	}	  	  
}