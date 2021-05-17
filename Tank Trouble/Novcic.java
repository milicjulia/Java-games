package drugi;

import java.awt.Color;
import java.awt.Graphics;

public class Novcic extends Figura{

	public Novcic(Polje p) {
		super(p);
		super.boja=Color.YELLOW;
	}

	@Override
	public void crt() {
		Graphics g=polje.getGraphics();
		g.setColor(boja);
		g.fillOval(polje.getWidth()/4, polje.getHeight()/4, polje.getWidth()/2, polje.getHeight()/2);
		
	}

}
