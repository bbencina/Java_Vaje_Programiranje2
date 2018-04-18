
public class Collatz {
	

	public static void main(String[] args) {
		

	}
	
	public static int col (int a1) {
		if (a1 == 1) {
			return (a1);
		}
		if (a1 % 2 == 0) {
			return (a1 / 2);
		} else {
			return (a1 * 3 + 1);
		}
	}
	
	public static int dolzina_zap(int a1) {
		int dolzina = 1, a = a1;
		if (a1 != 1) {
			while (a != 1) {
				a = col(a);
				dolzina++;
			}
		}
		return (dolzina + 1);
	}
	
	public static int najvecji (int a1) {
		int naj = a1, a = a1;
		if (a1 == 1){
			return (naj);
		}
		while (a != 1) {
			a = col(a);
			if (a > naj) {
				naj = a;
			}
		}
		return (naj);
	}
	
	public static void izpisi (int a1) {
		System.out.println(a1);
		if (a1 == 1){
			return;
		}
		int a = a1;
		while (a != 1) {
			a = col(a);
			System.out.println(a);
		}
		System.out.println(1);
		return;
	}
	 
}
