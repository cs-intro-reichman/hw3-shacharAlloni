// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		//System.out.println("End balance: " + endBalance(loan, rate, n, 12333));

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {
		rate = rate / 100;
		for (int i = 0; i < n; i++) {
			loan = (loan - payment) * (1 + rate);
		}
		return loan;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		double balance = loan;
		double payment = loan / n;

		while (balance > 0) {
			balance = endBalance(loan, rate, n, payment);
			payment = payment + epsilon;
			iterationCounter ++;
		}
		return payment;

    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		double highLim = loan;
		double lowLim = loan / n;
		double mid = (highLim + lowLim) / 2;
		double payment = endBalance(loan, rate, n, mid);

		while (!((payment > 0) && (payment < epsilon)) || ((payment < 0) && (payment > -epsilon))) {
			if (payment < 0) {

				highLim = mid;
				mid = (mid + lowLim) / 2;
			}
			else {
				lowLim = mid;
				mid = (mid + highLim) / 2;

			}
			iterationCounter++;
			payment = endBalance(loan, rate, n, mid);
		}

		return mid;

    }
}