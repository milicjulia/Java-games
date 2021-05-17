package treci;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Scena extends Canvas implements Runnable{
Igra igra;
Thread nit=new Thread(this);
Igrac igrac;
boolean radi=false;
ArrayList<KruznaFigura> baloni=new ArrayList<>();

public Scena(Igra i) {
	igra=i;
	nit.start();
	this.addKeyListener(new KeyAdapter() {
	public void keyPressed(KeyEvent ke) {
		if(ke.getExtendedKeyCode()==KeyEvent.VK_RIGHT) {
			igrac.pomeriD(10.0);
			return;
		}
		if(ke.getExtendedKeyCode()==KeyEvent.VK_LEFT) {
			igrac.pomeriL(10.0);
			return;
		}
	}
});
}
public synchronized void pokreni() {
	radi=true;
	notify();
}
public synchronized void zaustavi() {
	radi=false;
}
public synchronized void zavrsi() {
	nit.interrupt();
}

public void pokreniScenu() {
	igrac=new Igrac(new Vektor(getWidth()/2,getHeight()-20),new Color(0,255,0),30.0,new Vektor(0.0, 0.0),this);
}

@Override
public void run() {
	try {
	while(!Thread.interrupted()) {
		while(!radi) synchronized (this) { wait(); }
		if(Math.random()>0.9) baloni.add(new Balon(new Vektor(Math.random()*getWidth(),0),new Color(255,0,0),20,new Vektor(0,20),this));
		repaint();
		for(KruznaFigura k:baloni) {
			k.menjaPolozaj(0.06);
		}
		Thread.sleep(60);
		for(KruznaFigura k:baloni) {
			if(Krug.preklapajuSe(igrac, k)) {
				igrac.sudar(k);
				k.sudar(igrac);
			}
		}
	}
	}catch(InterruptedException ie) {}
}

public void paint(Graphics g) {
	for(KruznaFigura k:baloni) {
		k.crt(g);
	}
	if(igrac!=null)
	igrac.crt(g);
	
}
}
