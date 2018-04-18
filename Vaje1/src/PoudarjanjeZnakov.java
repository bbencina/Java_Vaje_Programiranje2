
public class PoudarjanjeZnakov {

	public static void main(String[] args) {
		String a = poudari("Zadnja novica");
		System.out.println(a);
		
		String b = povecaj("Zadnja *novica* danes!");
		System.out.println(b);
	}
	
	public static String poudari (String niz) {
		String vmesni = niz, rezultat = "";
		
		vmesni = niz.toUpperCase();
		if (vmesni.length() == 1) {
			return vmesni;
		}
		rezultat =  rezultat + vmesni.charAt(0);
		for (int i = 1; i < vmesni.length(); i++){
			rezultat = rezultat + ' ' + vmesni.charAt(i);
		}
		
		return rezultat;
	}
	
	public static String povecaj (String niz){
		String rezultat = "";
		boolean f_upp = false;
		for (int i = 0; i < niz.length(); i++){
			String vmesni = "";
			if (niz.charAt(i) == '*'){
				f_upp = !(f_upp);
				continue;
			} else {
				if (f_upp) {
					vmesni = "" + niz.charAt(i);
					rezultat = rezultat + vmesni.toUpperCase();
				} else {
					rezultat = rezultat + niz.charAt(i);
				}
			}
		}
		return rezultat;
	}

}
