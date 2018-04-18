
public class MnozenjeMatrik {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static double[][] zmnozi(double[][] a, double[][] b){
		int av, as, bv, bs;
		av = a.length;
		as = a[0].length;
		bv = b.length;
		bs = b[0].length;
		if (as != bv) {
			return null;
		}
		double[][] rez = new double[av][bs];
		for (int i = 0; i < av; i++){
			for (int j = 0; j < bs; i++) {
				double val = 0;
				for (int v = 0; v < as; v++){
					val = val + a[i][v] * b[v][j];
				}
				rez[i][j] = val;
			}
		}
		return rez;
	}

}
