import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FrekvenceBesed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static Map<String, Integer> frekvenca(String ime_vhod, String izvzete) throws IOException {
		Map<String, Integer> slovar = new HashMap<String, Integer>();
		BufferedReader vhod = new BufferedReader(new FileReader(ime_vhod));
		BufferedReader izvzete_besede = new BufferedReader(new FileReader(izvzete));
		HashSet<String> izjeme = new HashSet<String>();
		
		while (izvzete_besede.ready()){
			String beseda = izvzete_besede.readLine().trim().toLowerCase();
			izjeme.add(beseda);
		}
		izvzete_besede.close();
		
		while (vhod.ready()){
			String vrstica = vhod.readLine().trim();
			StringTokenizer strtok = new StringTokenizer(vrstica, " .,!?()\"\'/\\\n");
			while (strtok.hasMoreTokens()){
				String beseda = strtok.nextToken().toLowerCase();
				if (izjeme.contains(beseda)) continue;
				if (slovar.containsKey(beseda)){
					int v = slovar.get(beseda);
					slovar.put(beseda, v+1);
				} else {
					slovar.put(beseda, 1);
				}
			}
		}
		
		vhod.close();
		return slovar;
	}
	
	public static Vector<String> mapToVector(HashMap<String,Integer> slovar){
		Vector<String> besede = new Vector<String>();
		
		
		for (int i = 0; i < slovar.size(); i++){
			
		}
		
		return besede;
	}

}
