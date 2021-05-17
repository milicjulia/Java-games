package treci;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends KruznaFigura {

	public Igrac(Vektor centar, Color boja, double precnik, Vektor b, Scena s) {
		super(centar, boja, precnik, b, s);
	}
	
	public void pomeriL(double d) {
		dohvCentar().postX(dohvCentar().dohvX()-d);
		if(vanScene()) dohvCentar().postX(dohvPrecnik()/2);
	}
	public void pomeriD(double d) {
		dohvCentar().postX(dohvCentar().dohvX()+d);
		if(vanScene()) dohvCentar().postX(scena.getWidth()-dohvPrecnik()/2);
	}
	
	@Override
	public void crt(Graphics g) {
		Krug pom=new Krug(dohvCentar(),new Color(0,0,255),dohvPrecnik()/2);
		g.setColor(boja);
		g.fillOval((int)(dohvCentar().dohvX()-dohvPrecnik()/2), (int)(dohvCentar().dohvY()-dohvPrecnik()/2), (int)dohvPrecnik(), (int)dohvPrecnik());
		pom.crt(g);
		
	}

	@Override
	public void sudar(KruznaFigura f) {
		scena.zaustavi();
	}

}
