package tp.pr0;

public class FuncsMatematicas {
	public static int factorial (int n) {
		int k = 0;
		
		 if (n >= 0) {
			k = 1;
			for(int i = 1; i <= n ; i++) {
				k = i * k;
			}
			
		}
		
		return k;
	}
	public static int combinatorio (int n, int k) {
		int s = 0;
		if ((n < 0) || (k < 0)){
			s = -1;
		}
		else if ((k <= n) && (k >= 0)) {
			s = factorial(n) /(factorial(k) * factorial(n-k));
		}
		
		return s;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 6; ++i) {
			  for (int j = 0; j <= i; ++j) {
			    System.out.print(FuncsMatematicas.combinatorio(i,j) + " ");
			  }
			  System.out.println();
			}
	}
}
