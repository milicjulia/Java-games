package prvi;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

@SuppressWarnings("serial")
public class Basta extends Panel implements Runnable {
	
Thread nit=new Thread(this);
Color boja=new Color(0,255,0);
Rupa[][] rupe;
int povrce=100;
double cekanje;
int br_koraka;
boolean pise=false;
int vrste,kolone;

	public Basta(int v,int k) {
		nit.start();
		vrste=v;
		kolone=k;
		inicRupe(v, k);
		setSize(80, 80);
		setVisible(true);
		setBackground(boja);
	}
	
	public void inicRupe(int v, int k) {
		setLayout(new GridLayout(v,k));
		rupe=new Rupa[v][k];
		for(int i=0;i<v;i++)
			for(int j=0;j<k;j++) {
				rupe[i][j]=new Rupa(this);
				add(rupe[i][j]);
			}
	}
	
	public int dohvBrKoraka() { return br_koraka; }
	
	public void postBrKoraka(int k) { 
		br_koraka=k;
		for(int i=0;i<vrste;i++)
			for(int j=0;j<kolone;j++) {
		rupe[i][j].br_koraka=br_koraka;
		}
	}
	
	public void smanjiPovrce() {
		povrce--;
		Igra.azuriraj();
	}
	
	public void postCekanje(double c) { cekanje=c; }
	
	synchronized public void pokreni() {
		pise=true;
		notify();
	}
	
	synchronized public void zaustavi() {
		pise=false;
	}
	
	public void zavrsi() {
		for(int i=0;i<vrste;i++)
			for(int j=0;j<kolone;j++) rupe[i][j].zavrsi();
		nit.interrupt();
		//pokreni();
	}

	
	@Override
	public void run() {
		try {
			//System.out.println("Basta run");
		while(!Thread.interrupted()) {
			while(!pise)synchronized (this) { wait(); }
			int x=(int)(Math.random()*vrste);
			int y=(int)(Math.random()*kolone);
			while(rupe[x][y].daLiPokrenula()) {
				 x=(int)(Math.random()*vrste);
				 y=(int)(Math.random()*kolone);
			}
			Krtica k=new Krtica(rupe[x][y]);
			rupe[x][y].postZiv(k);
			rupe[x][y].pokreni();
			postCekanje(cekanje*0.99);
			Thread.sleep((long) this.cekanje);
		}
		}catch(Exception e) {}
		//System.out.println("Basta end");
	}

}
