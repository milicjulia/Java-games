package drugi;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public abstract class Polje extends Canvas {
	
Color boja;
Mreza mreza;
int[] pozicija;
private Polje ovo=this;

public Polje(Mreza m) {
	mreza=m;
	pozicija=new int[2];
	this.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent me) {
			if(mreza.igra.igranje) return;
			if(mreza.igra.tz==true) {
				if(ovo instanceof Trava)return;
				int poz[]=ovo.dohvPoz();
				mreza.remove(mreza.duzina*poz[0]+poz[1]);
				mreza.polje[poz[0]][poz[1]]=new Trava(mreza);
				mreza.add(mreza.polje[poz[0]][poz[1]],mreza.duzina*poz[0]+poz[1]);
				mreza.igra.setVisible(true);
				mreza.igra.repaint();
			}
			else {
				if(ovo instanceof Zid)return;
				int poz[]=ovo.dohvPoz();
				mreza.remove(mreza.duzina*poz[0]+poz[1]);
				mreza.polje[poz[0]][poz[1]]=new Zid(mreza);
				mreza.add(mreza.polje[poz[0]][poz[1]],mreza.duzina*poz[0]+poz[1]);
				mreza.igra.setVisible(true);
				mreza.igra.repaint();
			}
		    }
		});
	this.addKeyListener(new KeyAdapter() {
	@Override
	public void keyTyped(KeyEvent ke) {
		
		Polje old=m.igrac.dohvPolje();
		char pom=ke.getKeyChar();
		Polje polje=m.igrac.dohvPolje();
		switch(pom){
			case 'w': if(polje.dohvPolje(-1, 0)!=null && polje.dohvPolje(-1, 0).mozeFig(m.igrac)) {
				m.igrac.pomeriFig(polje.dohvPolje(-1, 0)); 
				old.repaint();
				return;
				}break;
			case 's':if(polje.dohvPolje(1, 0)!=null && polje.dohvPolje(1, 0).mozeFig(m.igrac)) {
				m.igrac.pomeriFig(polje.dohvPolje(1, 0));
				old.repaint();
				return;
				}break;
			case 'd':if(polje.dohvPolje(0, 1)!=null && polje.dohvPolje(0, 1).mozeFig(m.igrac)) {
				m.igrac.pomeriFig(polje.dohvPolje(0, 1));
				old.repaint();
				return;
				}break;
			case 'a':if(polje.dohvPolje(0, -1)!=null && polje.dohvPolje(0, -1).mozeFig(m.igrac)) {
				m.igrac.pomeriFig(polje.dohvPolje(0, -1));
				old.repaint();
				return;
				}break;
		}
		
	}
} );
}

public int[] dohvPoz() {
	for(int i=0;i<mreza.duzina;i++)
		for(int j=0;j<mreza.duzina;j++)
	if(mreza.polje[i][j]==this) {
		pozicija[0]=i;
		pozicija[1]=j;
		return pozicija;
	}
	return null;
}

public Polje dohvPolje(int x,int y) {
	int pom[]=dohvPoz();
	if(pom==null)return null;
	if((pom[0]+x)<0 || (pom[0]+x)>=mreza.duzina || (pom[1]+y)<0 || (pom[1]+y)>=mreza.duzina )return null;
	else return mreza.polje[pom[0]+x][pom[1]+y];
}

public Mreza dohvMrezu() {
	return mreza;
}
	
public abstract boolean mozeFig(Figura f);

}
