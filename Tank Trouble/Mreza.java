package drugi;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Random;


@SuppressWarnings("serial")
public class Mreza extends Panel implements Runnable{
	
Igrac igrac;
int duzina=17;
Thread nit;
Polje[][] polje;
ArrayList<Tenk> tenkovi=new ArrayList<Tenk>();
ArrayList<Novcic> novcici=new ArrayList<Novcic>();
Igra igra;
boolean radi=false;

public Mreza(int d,Igra i) {
	nit=new Thread(this);
	setLayout(new GridLayout(d,d));
	duzina=d;
	polje=new Polje[d][d];
	igra=i;
	dodajPolje();
	nit.start();
}

synchronized public void dodajTenkove(int num) {
	if(igra.igranje) {
		while(num>0) {
		Random rand=new Random();
		int pozx=rand.nextInt(duzina);
		int pozy=rand.nextInt(duzina);
		Tenk n=new Tenk(polje[pozx][pozy]);
		if(polje[pozx][pozy].mozeFig(n)) {
			tenkovi.add(n);
			num--;
		}
		else n.zavrsi();
		}
	}
}

public Mreza(Igra i) {
	this(17,i);
}

synchronized public void  dodajNovcice(int num) {
	if(igra.igranje) {
		while(num>0) {
		Random rand=new Random();
		int pozx=rand.nextInt(duzina);
		int pozy=rand.nextInt(duzina);
		Novcic n=new Novcic(polje[pozx][pozy]);
		if(polje[pozx][pozy].mozeFig(n) && !imaNovcic(n)) {
			novcici.add(n);
			num--;
		}
		}
	}
}

synchronized public void dodajIgraca() {
	Random rand=new Random();
	while(true) {
	int pozx=rand.nextInt(duzina);
	int pozy=rand.nextInt(duzina);
	Igrac i=new Igrac(polje[pozx][pozy]);
	if(polje[pozx][pozy].mozeFig(i) && slobodnoPolje(i)) {
		igrac=new Igrac(polje[pozx][pozy]);
		return;
	}
	}
}
public boolean slobodnoPolje(Figura f) {
	for(Novcic nn:novcici) {
		if(nn.jednakeFig(f)) return false;
	}
	for(Tenk tt:tenkovi) {
		if(tt.jednakeFig(f)) return false;
	}
	return true;
}

public boolean imaNovcic(Novcic n) {
	for(Novcic nn:novcici) {
		if(nn.jednakeFig(n)) return true;
	}
	return false;
}
public void dodajPolje() {
	Random rand=new Random();
	for(int i=0;i<duzina;i++)
		for(int j=0;j<duzina;j++) {
		if(rand.nextInt(100)<=80) polje[i][j]=new Trava(this);
		else polje[i][j]=new Zid(this);
		add(polje[i][j]);
		}
}

public synchronized void pokreni() {
	 radi = true;
	 for(Tenk t:tenkovi) t.pokreni();
	notify();
}

public synchronized void zaustavi() {
	radi=false;
	 for(Tenk t:tenkovi) t.zaustavi();
	 brisiFigure();
	}

public void zavrsi() {
	nit.interrupt();
	for(Tenk t:tenkovi)t.zavrsi();
	
}

synchronized public void brisiFigure() {
	 for(Tenk t:tenkovi) t.zavrsi();
	tenkovi.clear();
	novcici.clear();
	for(int i=0;i<duzina;i++)
		for(int j=0;j<duzina;j++) polje[i][j].repaint();
	//System.out.println("Obrisao");
	repaint();
}
	@Override
	public void run() {
		try {
			nastavi: while(!Thread.interrupted()) {
				while(!radi) synchronized (this) { wait(); }
				synchronized (this) {for(Novcic n:novcici) n.crt();}
				synchronized (this) {for(Tenk t:tenkovi) t.crt();}
				igrac.crt();
				for(int i=0;i<novcici.size();i++) {
					Novcic n=novcici.get(i);
					if(n.jednakeFig(igrac)) {
						igra.poens++;
						if(igra.poens==igra.old_poens) { igra.poeni.setText(Integer.toString(igra.poens)); zaustavi(); continue nastavi;}
						igra.poeni.setText(Integer.toString(igra.poens));
						novcici.remove(i);
						n.dohvPolje().repaint();
					}
				}
				for(int i=0;i<tenkovi.size();i++) {
					Tenk n=tenkovi.get(i);
					if(n.jednakeFig(igrac)) zaustavi();
				}
				Thread.sleep(40);
			}
			}catch(InterruptedException ie) {}
		
	}

	public void paint(Graphics g) {
		for(int i=0;i<duzina;i++)
			for(int j=0;j<duzina;j++)
				polje[i][j].repaint();
		
	}

}
