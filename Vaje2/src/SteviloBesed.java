import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SteviloBesed {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

	}
	
	public static int stBesed (String ime_vhod, String ime_izhod) throws IOException {
		int st_besed = 0;
		BufferedReader vhod = new BufferedReader(new FileReader(ime_vhod));
		PrintWriter izhod = new PrintWriter(new FileWriter(ime_izhod));
		
		while(vhod.ready()){
			String vrstica = vhod.readLine().trim();
			if (vrstica.equals("")){continue;}
			String[] besede = vrstica.split(" +");
			
			st_besed = st_besed + besede.length;
			for (int i = 0; i < besede.length; i++){
				izhod.println(besede[i]);
			}
		}
		
		vhod.close();
		izhod.close();
		return st_besed;
	}

}
