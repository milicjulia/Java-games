package drugi;

import java.awt.Color;
import java.awt.Graphics;

public class Tenk extends Figura implements Runnable{
	
Thread nit;
boolean radi=false;

public Tenk(Polje p) {
		super(p);
		super.boja=Color.BLACK;
		nit=new Thread(this);
		nit.start();
	}

@Override
public void run() {
	try {
		//System.out.println("TENK RUN");
	while(!Thread.interrupted()) {
		while(!radi) synchronized (this) { wait(); }
		pomeri();
		Thread.sleep(500);
	}
	}catch(InterruptedException ie) {}
	
}

public synchronized void pokreni() {
	radi=true;
	notify();
}

public synchronized void zaustavi() {
	radi=false;
	}
public void zavrsi() {
	nit.interrupt();
	
}
@Override
public void crt() {
	Graphics g=polje.getGraphics();
	g.setColor(boja);
	g.drawLine(0, 0, polje.getWidth(), polje.getHeight());
	g.drawLine(0, polje.getHeight(), polje.getWidth(), 0);
}

public void pomeri() {
	while(true) {
		Polje old=polje;
	int pom=(int)(Math.random()*4);
	switch(pom){
		case 0: if(polje.dohvPolje(-1, 0)!=null && polje.dohvPolje(-1, 0).mozeFig(this)) {
			pomeriFig(polje.dohvPolje(-1, 0)); old.repaint();return;}
		break;//nagore
		case 1:if(polje.dohvPolje(1, 0)!=null && polje.dohvPolje(1, 0).mozeFig(this)) {
			pomeriFig(polje.dohvPolje(1, 0));old.repaint(); return;}
		break;//nadole
		case 2:if(polje.dohvPolje(0, 1)!=null && polje.dohvPolje(0, 1).mozeFig(this)) {
			pomeriFig(polje.dohvPolje(0, 1)); old.repaint();return;}
		break;//nalevo
		case 3:if(polje.dohvPolje(0, -1)!=null && polje.dohvPolje(0, -1).mozeFig(this)) {
			pomeriFig(polje.dohvPolje(0, -1));old.repaint(); return;}
		break; //nadesno
	}
	}
}
}
