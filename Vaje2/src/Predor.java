import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Predor {

	public static void main(String[] args) throws IOException {
		int st = krsitelji("vhod.txt", "izhod.txt");
		System.out.println(st);

	}
	
	public static int krsitelji (String ime_vhod, String ime_izhod) throws IOException {
		double DOLZINA = 622.0;
		double OMEJITEV = 80.0 / 3.6;
		int st_krsiteljev = 0;
		
		BufferedReader vhod = new BufferedReader(new FileReader(ime_vhod));
		PrintWriter izhod = new PrintWriter(new FileWriter(ime_izhod));
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("0.00", symbol);
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		while(vhod.ready()){
			String vrstica = vhod.readLine().trim();
			if (vrstica.equals("")) continue;
			String[] podatki = vrstica.split(" +");
			int s, t;
			s = Integer.parseInt(podatki[0]);
			t = Integer.parseInt(podatki[1]);
			double cas = t - s;
			String reg = podatki[2];
			if (DOLZINA / cas > OMEJITEV){
				izhod.println(reg + df.format(DOLZINA / cas));
				st_krsiteljev++;
			}
		}
		
		izhod.close();
		vhod.close();
		return st_krsiteljev;
	}

}
