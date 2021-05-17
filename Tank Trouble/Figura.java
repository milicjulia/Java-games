package drugi;

import java.awt.Color;

public abstract class Figura {
Polje polje;
Color boja;

public Figura(Polje p) {
	polje=p;
}

public Polje dohvPolje() {
	return polje;
}
public void pomeriFig(Polje p) {
	polje=p;
}

public boolean jednakeFig(Figura f) {
	if(f.polje==this.polje) return true;
	else return false;
}

public abstract void crt();
}
