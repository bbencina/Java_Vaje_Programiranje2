import java.util.*;

public class Graf {
	public Map<Object, Tocka> tocke;
	
	public Graf() {
		this.tocke = new HashMap<Object, Tocka>();
	}
	
	public Tocka tocka(Object ime){
		return this.tocke.get(ime);
	}
	
	public boolean povezava(Tocka a, Tocka b){
		return a.sosedi.contains(b);
	}
	
	public void dodajTocko(Tocka a){
		if (!tocke.containsKey(a.ime)){
			tocke.put(a.ime, a);
		}
		return;
	}
	
	public void dodajPovezavo(Tocka a, Tocka b){
		if (a == b) return;
		if (a.sosedi.contains(b)) return;
		a.sosedi.add(b);
		b.sosedi.add(a);
		return;
	}
	
	public void odstraniPovezavo(Tocka a, Tocka b){
		if (a.sosedi.contains(b)){
			a.sosedi.remove(b);
			b.sosedi.remove(a);
		}
		return;
	}
	
	public void odstraniTocko(Tocka a){
		for (Tocka b: a.sosedi){
			b.sosedi.remove(a);
		}
		this.tocke.remove(a.ime);
		return;
	}
	
	public static Graf prazen(int n){
		Graf g = new Graf();
		for (int i = 0; i < n; i++){
			Tocka t = new Tocka(i);
			g.dodajTocko(t);
		}
		return g;
	}
	
	public static Graf cikel(int n){
		Graf g = new Graf();
		for (int i = 0; i < n; i++){
			Tocka t = new Tocka(i);
			g.dodajTocko(t);
		}
		for (int i = 0; i < n; i++){
			g.dodajPovezavo(g.tocka(i), g.tocka((i+1)%n));
		}
		return g;
	}
	
	public static Graf poln(int n){
		Graf g = new Graf();
		for (int i = 0; i < n; i++){
			Tocka t = new Tocka(i);
			g.dodajTocko(t);
		}
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				g.dodajPovezavo(g.tocka(i), g.tocka(j));
			}
		}
		return g;
	}
	
	public static Graf polnDvodelen(int n, int m){
		Graf g = new Graf();
		for (int i = 0; i < n + m; i++){
			Tocka t = new Tocka(i);
			g.dodajTocko(t);
		}
		for (int i = 0; i < n; i++){
			for (int j = n; j < n+m; j++){
				g.dodajPovezavo(g.tocka(i), g.tocka(j));
			}
		}
		for (int i = n; i < n+m; i++){
			for (int j = 0; j < n; j++){
				g.dodajPovezavo(g.tocka(i), g.tocka(j));
			}
		}
		return g;
	}
	
	
	
	public void razporedi (double x, double y, double r){
		int n = this.tocke.size();
		int i = 0;
		for (Tocka t : tocke.values()){
			t.x = x + r * Math.cos(2 * (double)i * Math.PI / (double)n);
			t.y = y + r * Math.sin(2 * (double)i * Math.PI / (double)n);
			i++;
		}
	}

}
