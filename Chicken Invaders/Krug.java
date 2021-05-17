package treci;

import java.awt.Color;
import java.awt.Graphics;

public class Krug {
Vektor centar;
Color boja;
double precnik;

public Krug(Vektor c,Color b,double p) {
	centar=c;
	boja=b;
	precnik=p;
}

public Vektor dohvCentar() {
	return centar;
}

public void postCentar(Vektor v) {
	centar=v;
}
public double dohvPrecnik() {
	return precnik;
}

public void postPrecnik(double p) {
	precnik=p;
}

static public boolean preklapajuSe(Krug k1,Krug k2) {
	double c1c2=Math.sqrt(Math.pow((k1.dohvCentar().dohvX()-k2.dohvCentar().dohvX()), 2)+Math.pow((k1.dohvCentar().dohvY()-k2.dohvCentar().dohvY()),2));
	if(c1c2<((k1.dohvPrecnik()+k2.dohvPrecnik())/2)) return true;
	else return false;
}

protected void crt(Graphics g) {
	g.setColor(boja);
	g.fillOval((int)(dohvCentar().dohvX()-dohvPrecnik()/2), (int)(dohvCentar().dohvY()-dohvPrecnik()/2), (int)dohvPrecnik(), (int)dohvPrecnik());	
}

}
