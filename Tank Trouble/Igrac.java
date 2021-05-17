package drugi;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends Figura{

	public Igrac(Polje p) {
		super(p);
		boja=new Color(255,0,0);
	}

	@Override
	public void crt() {
		Graphics g=polje.getGraphics();
		g.setColor(boja);
		g.drawLine(polje.getWidth()/2, 0, polje.getWidth()/2, polje.getHeight());
		g.drawLine(0, polje.getHeight()/2, polje.getWidth(), polje.getHeight()/2);
		
		
	}

}
