import java.util.*;

public class Tocka{
	protected Object ime;
	public Set<Tocka> sosedi;
	public double x, y;
	
	public Tocka(Object ime) {
		this.ime = ime;
		this.sosedi = new HashSet<Tocka>();
		this.x = 0;
		this.y = 0;
	}
	
	public int stopnja(){
		return this.sosedi.size();
	}
	
	public static int round(double x){
		return (int)(x + 0.5);
	}

}
