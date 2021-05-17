package drugi;

import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Trava extends Polje{

Figura figura;
boolean imaFig=true;

public Trava(Mreza m) {
	super(m);
	super.boja=Color.green;
}

@Override
public void paint(Graphics g) {
	g.setColor(boja);
	g.fillRect(0, 0, getWidth(), getHeight());
}

public boolean mozeFig(Figura f) {
return imaFig;
}

}
