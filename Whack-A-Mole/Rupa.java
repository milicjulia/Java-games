package prvi;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Rupa extends Canvas implements Runnable{

Thread nit=new Thread(this);
Color pozadina=new Color(105,74,12);
Zivotinja zivotinja;
Basta basta;
private boolean pokrenula=false;
private double d=0;
int br_koraka;

	public Rupa(Basta b){
		nit.start();
		basta=b;
		br_koraka=basta.br_koraka;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if(zivotinja!=null) zivotinja.ispoljiEfekatU();
			}
		});
		setSize(20, 20);
		setVisible(true);
	}
	
	public void postZiv(Zivotinja z) { zivotinja=z; }
	
	public Zivotinja dohvZiv() { return zivotinja; }
	
	public boolean daLiPokrenula() { return pokrenula; }
	
	synchronized public void pokreni() {
		pokrenula=true;
		notify();
	}
	
	synchronized public void zaustavi() { pokrenula=false; }
	
	public void zavrsi() { nit.interrupt(); }
	
	public void paint(Graphics g) {
		g.setColor(pozadina);
		g.fillRect(0, 0, getWidth()-10, getHeight()-10);
		if(zivotinja!=null) zivotinja.crt(g, d);
		
	}
	
	@Override
	public void run() {
		try {
			//System.out.println("Rupa run");
		while(!Thread.interrupted()) {
			while(!pokrenula) synchronized (this) { wait();}
			for(int i=1;i<=basta.dohvBrKoraka();i++) {
				d=(double)(i)/basta.dohvBrKoraka();
				repaint();
				Thread.sleep(100);
			}
			Thread.sleep(2000);
			if(!zivotinja.daLiJeUdarena()) zivotinja.ispoljiEfekatP();
			zivotinja=null;
			repaint();
			pokrenula=false;
		}
		}catch(Exception e) {}
	//	System.out.println("Rupa end");
	}

	


}
