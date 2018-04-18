import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	protected int sirina, visina;
	public Graf graf;
	
	private int premer; // vedno liho število
	
	protected HashSet<Tocka> oznaceneTocke;
	protected Tocka aktivnaTocka;
	protected double aktivnaX, aktivnaY;
	protected double premikX, premikY;
	
	protected boolean vlecenje;
	
	protected Color barvaTocka = Color.blue,
					barvaOznacena = Color.green,
					barvaAktivna = Color.red,
					barvaPovezava = Color.black;
	
	public Platno(int sirina, int visina) {
		super();
		this.sirina = sirina;
		this.visina = visina;
		this.graf = null;
		this.setBackground(Color.white);
		
		this.premer = 21;
		this.aktivnaX = 0;
		this.aktivnaY = 0;
		
		this.oznaceneTocke = new HashSet<Tocka>();
		this.aktivnaTocka = null;
		this.barvaTocka = Color.BLUE;
		this.barvaOznacena = Color.GREEN;
		this.barvaAktivna = Color.RED;
		this.barvaPovezava = Color.BLACK;
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		
		this.setFocusable(true);
		this.vlecenje = false;
	}
	
	public void narisi(Graf g){
		this.graf = g;
		repaint();
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(this.sirina, this.visina);
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int zamik = (this.premer - 1) / 2;
		if (graf == null){
			return;
		}
		for (Tocka a : graf.tocke.values()){
			for (Tocka b : a.sosedi){
				g.setColor(this.barvaPovezava);
				g.drawLine(Tocka.round(a.x),
							Tocka.round(a.y),
							Tocka.round(b.x),
							Tocka.round(b.y));
			}
		}
		for (Tocka a : graf.tocke.values()){
			if (aktivnaTocka == a){
				g.setColor(this.barvaAktivna);
			} else if (this.oznaceneTocke.contains(a)) {
				g.setColor(barvaOznacena);
			} else {
				g.setColor(this.barvaTocka);
			}
			g.fillOval(Tocka.round(a.x)-10,
						Tocka.round(a.y)-10,
						21,
						21);
			g.setColor(Color.BLACK);
			g.drawOval(Tocka.round(a.x)-zamik,
					Tocka.round(a.y)-zamik,
					premer,
					premer);
		}
	}

	@Override
	public void keyPressed(KeyEvent k) {
		//System.out.println(k.getKeyChar());
		if (k.getKeyChar() == 'a'){
			this.oznaceneTocke.addAll(this.graf.tocke.values());
		
		} else if (k.getKeyChar() == 's'){
			this.oznaceneTocke.clear();
			
		} else if (k.getKeyChar() == 'f'){
			for (Tocka t : this.oznaceneTocke){
				for (Tocka a : this.oznaceneTocke){
					if (! graf.povezava(a, t)){
						graf.dodajPovezavo(a, t);
					}
				}
			}
		
		} else if (k.getKeyChar() == 'g'){
			for (Tocka t : this.oznaceneTocke){
				for (Tocka a : this.oznaceneTocke){
					if (graf.povezava(a, t)){
						graf.odstraniPovezavo(a, t);
					}
				}
			}
		
		} else if (k.getKeyChar() == 'd'){
			for (Tocka t : this.oznaceneTocke){
				this.graf.odstraniTocko(t);
			}
			this.oznaceneTocke.clear();
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.aktivnaTocka != null){
			this.aktivnaTocka.x = e.getX();
			this.aktivnaTocka.y = e.getY();
			repaint();
		} else {
			
			for (Tocka a : this.oznaceneTocke){
				a.x = a.x + e.getX() - premikX;
				a.y = a.y + e.getY() - premikY;
			}
			repaint();
		}
		premikX = e.getX();
		premikY = e.getY();
		this.vlecenje = true;
		return;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.premikX = e.getX();
		this.premikY = e.getY();
		this.vlecenje = false;
		
		int radij = (this.premer - 1) / 2;
		for (Tocka a : this.graf.tocke.values()){
			if ((e.getX() - a.x)*(e.getX() - a.x) + (e.getY() - a.y)*(e.getY() - a.y) < radij * radij){
				this.aktivnaTocka = a;
				this.aktivnaX = a.x;
				this.aktivnaY = a.y;
				repaint();
				return;
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (aktivnaTocka != null && aktivnaX == aktivnaTocka.x && aktivnaY == aktivnaTocka.y){
			if (this.oznaceneTocke.contains(aktivnaTocka)){
				this.oznaceneTocke.remove(aktivnaTocka);
			} else {
				this.oznaceneTocke.add(aktivnaTocka);
			}
			aktivnaTocka = null;
		} else if (aktivnaTocka != null) {
			aktivnaTocka = null;
		} else if (!vlecenje) {
			Tocka t = new Tocka(this.graf.tocke.size());
			t.x = e.getX();
			t.y = e.getY();
			HashSet<Tocka> sosednje = new HashSet<Tocka>();
			for (Tocka a : this.oznaceneTocke){
				sosednje.add(a);
			}
			t.sosedi = sosednje;
			this.graf.dodajTocko(t);
		}
		repaint();
		
	}
	
}
