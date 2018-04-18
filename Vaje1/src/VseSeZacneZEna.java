
public class VseSeZacneZEna {

	public static void main(String[] args) {
		String[] t = ena(5);
		for (int i = 0; i < t.length; i++) {
			System.out.println(t[i]);
		}
	}
	
	public static String[] ena (int n) {
		String a1 = "1";
		String[] rez = new String[n];
		rez[0] = a1;
		for (int i = 1; i < n; i++){
			String a = "";
			int j = 0;
			while (j < a1.length()){
				int k = j;
				char c = a1.charAt(j);
				while (j < a1.length() && a1.charAt(j) == c){
					j++;
				}
				a = a + Integer.toString(j-k) + c;
			}
			rez[i] =a;
			a1 = a;
		}
		return rez;
	}

}
