
public class OdvodPolinoma {

	public static void main(String[] args) {

	}
	
	public static int[] odvajaj (int[] polinom, int n) {
		if (polinom.length < n) {
			return new int[0];
		}
		int[] odvod = polinom;
		for (int i = 0; i < n; i++){
			odvod = odvajaj(odvod);
		}
		return odvod;
	}
	
	public static int[] odvajaj (int[] polinom) {
		if (polinom.length == 0) {
			return new int[0];
		}
		int l = polinom.length;
		int[] odvod = new int[l-1];
		
		for (int i = 0; i < l-1; i++){
			odvod[i] = polinom[i+1] * (i+1);
		}
		
		return odvod;
	}

}
