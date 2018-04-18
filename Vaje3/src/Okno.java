import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.*;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener{
	public Platno platno;
	
	protected JMenuItem open, save, saveAs, exit,
						prazenG, polnG, cikelG, polnDvodelenG,
						povezavaC, tockaC, aktivnaC, oznacenaC;


	public Okno(String naslov, Platno platno) {
		super();
		this.setTitle(naslov);
		this.platno = platno;
		this.add(this.platno);
		
		JMenuBar mb = new JMenuBar();
		
		JMenu file = new JMenu("File");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		saveAs = new JMenuItem("Save As");
		exit = new JMenuItem("Exit");
		
		JMenu set = new JMenu("Set graph");
		prazenG = new JMenuItem("Prazen");
		polnG = new JMenuItem("Poln");
		cikelG = new JMenuItem("Cikel");
		polnDvodelenG = new JMenuItem("Poln dvodelen");
		
		JMenu colors = new JMenu("Set colors");
		povezavaC = new JMenuItem("Barva povezave");
		tockaC = new JMenuItem("Barva tocke");
		aktivnaC = new JMenuItem("Barva aktivne tocke");
		oznacenaC = new JMenuItem("Barva oznacenih tock");
		
		
		file.add(open);
		file.addSeparator();
		file.add(save);
		file.addSeparator();
		file.add(saveAs);
		file.addSeparator();
		file.add(exit);
		
		set.add(prazenG);
		set.addSeparator();
		set.add(polnG);
		set.addSeparator();
		set.add(cikelG);
		set.addSeparator();
		set.add(polnDvodelenG);
		
		colors.add(povezavaC);
		set.addSeparator();
		colors.add(tockaC);
		set.addSeparator();
		colors.add(aktivnaC);
		set.addSeparator();
		colors.add(oznacenaC);
		
		open.addActionListener(this);
		save.addActionListener(this);
		saveAs.addActionListener(this);
		exit.addActionListener(this);
		
		prazenG.addActionListener(this);
		polnG.addActionListener(this);
		cikelG.addActionListener(this);
		polnDvodelenG.addActionListener(this);
		
		povezavaC.addActionListener(this);
		tockaC.addActionListener(this);
		aktivnaC.addActionListener(this);
		oznacenaC.addActionListener(this);
		
		mb.add(file);
		mb.add(set);
		mb.add(colors);
		
		setJMenuBar(mb);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();
		Graf g;
		String n, m;
		Color c;
		if (src == prazenG){
			n = JOptionPane.showInputDialog("Vnesi število toèk: ");
			g = Graf.prazen(Integer.parseInt(n));
			g.razporedi(200, 200, 100);
			platno.narisi(g);
		}else if (src == polnG){
			n = JOptionPane.showInputDialog("Vnesi število toèk: ");
			g = Graf.poln(Integer.parseInt(n));
			g.razporedi(200, 200, 100);
			platno.narisi(g);
		} else if (src == cikelG){
			n = JOptionPane.showInputDialog("Vnesi število toèk: ");
			g = Graf.cikel(Integer.parseInt(n));
			g.razporedi(200, 200, 100);
			platno.narisi(g);
		} else if (src == polnDvodelenG){
			n = JOptionPane.showInputDialog("Vnesi število toèk: ");
			m = JOptionPane.showInputDialog("Vnesi število toèk: ");
			g = Graf.polnDvodelen(Integer.parseInt(n), Integer.parseInt(m));
			g.razporedi(200, 200, 100);
			platno.narisi(g);
		}
		
		else if (src == povezavaC){
			c = JColorChooser.showDialog(this, "Izberi barvo povezave: ", platno.barvaPovezava);
			if (c != null){
				platno.barvaPovezava = c;
			}
			platno.repaint();
		} else if(src == tockaC){
			c = JColorChooser.showDialog(this, "Izberi barvo toèke: ", platno.barvaTocka);
			if (c != null){
				platno.barvaTocka = c;
			}
			platno.repaint();
		}else if (src == aktivnaC){
			c = JColorChooser.showDialog(this, "Izberi barvo aktivne toèke: ", platno.barvaAktivna);
			if (c != null){
				platno.barvaAktivna = c;
			}
			platno.repaint();
		}else if (src == oznacenaC){
			c = JColorChooser.showDialog(this, "Izberi barvo povezave: ", platno.barvaOznacena);
			if (c != null){
				platno.barvaOznacena = c;
			}
			platno.repaint();
		}
		
		else if (src == exit){
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
		else if (src == open){
			JFileChooser fc = new JFileChooser();
			
			Vector<Tocka> ts = new Vector<Tocka>();
			
			int opn = fc.showOpenDialog(this);
			if (opn == JFileChooser.APPROVE_OPTION){
				String dat = fc.getSelectedFile().getPath();
				try {
					BufferedReader vhod = new BufferedReader(new FileReader(dat));
					int i=0;
					g = new Graf();
					while(vhod.ready()){
						String vrstica = vhod.readLine().trim();
						String[] args = vrstica.split(" ");
						if (args[0] == "#vertices"){
							i = Integer.parseInt(args[1]);
							
							for (int j = 0; j < i; j++){
								Tocka t = new Tocka(Integer.toString(j));
								ts.addElement(t);
							}
						}else if(args[0] == "#edges"){
							for (int j = 0; j < i && vhod.ready(); j++){
								String[] t_args = vhod.readLine().trim().split(" ");
								for (int k = 1; k < t_args.length; k++){
									ts.elementAt(j).sosedi.add(ts.elementAt(Integer.parseInt(t_args[k])));
								}
							}
							
							for (int j = 0; j < i; j++){
								g.dodajTocko(ts.elementAt(j));
							}
						}
						
					}
					vhod.close();
					platno.narisi(g);
				} catch (Exception e1) {
					// TODO
					e1.printStackTrace();
				}
			}
			
		}else if(src == save){
			JFileChooser fc = new JFileChooser();
			Vector<Tocka> ts = new Vector<Tocka>();
			int sv = fc.showSaveDialog(this);
			
			if (sv == JFileChooser.APPROVE_OPTION){
				String dat = fc.getSelectedFile().getPath();
				try {
					PrintWriter izhod = new PrintWriter(new FileWriter(dat));
					izhod.println("#vertices " + platno.graf.tocke.size());
					for (int j = 0; j < platno.graf.tocke.size(); j++){
						izhod.println(Integer.toString(j));
					}
					
					izhod.println("#edges");
					for (int j = 0; j < platno.graf.tocke.size(); j++){					
						
					}
					
					izhod.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}else if(src == saveAs){
			
		}
		
	}
	

}
