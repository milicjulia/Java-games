package treci;

public class Vektor implements Cloneable {
double x,y;

public Vektor(double xx,double yy) {
	x=xx;
	y=yy;
}

public double dohvX() {
	return x;
}
public double dohvY() {
	return y;
}
public void postX(double xx) {
	x=xx;
}
public void postY(double yy) {
	y=yy;
}
public void pomnozi(double a) {
	x=x*a;
	y=y*a;
}

public void saberiSa(Vektor v) {
	x=x+v.dohvX();
	y=y+v.dohvY();
}

public Vektor clone() {
	Vektor v;
	try {
		v = (Vektor)super.clone();
	} catch (CloneNotSupportedException e) {
		return null;
	}
	v.x=x;
	v.y=y;
	return v;
}
}
