package treci;

import java.awt.Color;

public abstract class KruznaFigura extends Krug{
Vektor brzina;
Scena scena;
boolean istekaoperiod=false;

public KruznaFigura(Vektor centar,Color boja,double precnik,Vektor b,Scena s) {
	super(centar,boja,precnik);
	brzina=b;
	scena=s;
}

public void menjaPolozaj(double t) {
	Vektor pom=(Vektor) brzina.clone();
	super.centar.saberiSa(pom);
	scena.repaint();
	if(vanScene()) {}
}

public abstract void sudar(KruznaFigura f) ;

public boolean vanScene() {
	if(scena.getWidth()<centar.x+precnik/2 || scena.getHeight()<centar.y+precnik/2
			|| 0>centar.x-precnik/2 || 0>centar.y-precnik/2) return true;
	else return false;
}

public void protekaoPeriod(double t) {
	istekaoperiod=true;
	menjaPolozaj(t);
}
}
