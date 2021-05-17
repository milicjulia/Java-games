package prvi;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {
	
Color boja=Color.DARK_GRAY;

	Krtica(Rupa r){
		super(r);
	}

	@Override
	public void ispoljiEfekatP() { rupa.basta.smanjiPovrce(); }

	@Override
	public void ispoljiEfekatU() { udarena=true; super.rupa.repaint(); }
	
	@Override
	protected void crt(Graphics g, double d) {
		g.setColor(boja);
		if(super.udarena==false)
		g.fillOval((int)((super.rupa.getWidth()-10)*(1-d)/2), (int)((super.rupa.getHeight()-10)*(1-d)/2),(int)((super.rupa.getWidth()-10)*d) , (int)((super.rupa.getHeight()-10)*d));	
	
	}

}
		