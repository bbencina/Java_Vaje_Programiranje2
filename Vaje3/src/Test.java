
public class Test {

	public static void main(String[] args) {
		Platno platno = new Platno(400, 400);
		Okno root = new Okno("Okence", platno);
		Graf g = Graf.poln(6);
		g.razporedi(200, 200, 100);
		
		//g.odstraniTocko(g.tocka(1));
		//System.out.println(g.tocke.size());
		
		
		root.pack();
		root.setVisible(true);
		platno.narisi(g);
		
		return;

	}

}
