
public class RazcepNaravnegaStevila {
	
	public static void main(String[] args) {
		razcep (420);

	}
	
	public static void razcep (int st) {
		int stevilo = st;
		char op = '=';
		
		System.out.print(stevilo);
		
		for (int i = 2; i * i < st; i++) {
			int faktor = i;
			int kolicina = 0;
			while (stevilo % i == 0){
				stevilo = stevilo / i;
				kolicina++;
			}
			if(kolicina == 0){
				continue;
			} else if (kolicina == 1) {
				System.out.print(" " + op + ' ' + faktor);
			} else {
				System.out.print(" " + op + ' ' + faktor + '^' + kolicina);
			}
			if (kolicina != 0 && op == '='){
				op = '*';
			}
			if (stevilo == 1) {
				break;
			}
		}
		
		return;
	}
}
