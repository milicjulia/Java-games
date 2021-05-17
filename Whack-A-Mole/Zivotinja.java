package prvi;

import java.awt.Graphics;

public abstract class Zivotinja {
	
Rupa rupa;//=new Rupa(Igra.basta);
protected boolean udarena=false;

	public Zivotinja(Rupa r) {
		rupa=r;
	}
	
	public boolean daLiJeUdarena() { return udarena; }
	
	abstract public void ispoljiEfekatP();
	
	abstract public void ispoljiEfekatU();
	
	protected abstract void crt(Graphics g,double d);
	
  
}
