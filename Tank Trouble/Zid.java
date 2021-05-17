package drugi;

import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Zid extends Polje{

boolean imaFig=false;
	
	public Zid(Mreza m) {
		super(m);
		super.boja=Color.LIGHT_GRAY;
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
