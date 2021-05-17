package drugi;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Igra extends Frame implements ActionListener, ItemListener {
int poens=0;
int old_poens=0;
Mreza m;
 boolean izmena=false,igranje=false,pocela=false,tz;
Button pocni;
TextField novci;
Label poeni;
Checkbox trava,zid;
CheckboxGroup grupa;

public Igra() {
	super("Igra");
	
	m=new Mreza(this);
	add(m,BorderLayout.CENTER);
	dodajMeni();
	dodajRez();
	dodajPodloga();
	this.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent we) {
			m.zavrsi();
			dispose();
		}
	});
	setSize(500, 400);
	this.setVisible(true);
}

public void dodajPodloga() {
	Label podloga=new Label("Podloga:");
	Panel pod=new Panel(new GridLayout(2,1));
	 Panel uk=new Panel(new GridLayout(1,2));
	grupa=new CheckboxGroup();
	trava=new Checkbox("Trava", false, grupa);
	  zid=new Checkbox("Zid", false, grupa);
	trava.addItemListener(this);
	  zid.addItemListener(this);
	trava.setBackground(Color.GREEN);
	  zid.setBackground(Color.LIGHT_GRAY);
	pod.add(trava);
	pod.add(zid);
	uk.add(podloga);
	uk.add(pod);
	add(uk,BorderLayout.EAST);
}

public void dodajRez() {
	poeni=new Label(Integer.toString(poens));
	novci=new TextField();
	pocni=new Button("Pocni");
	Label novcica=new Label("Novcica:");
	Label poena=new Label("Poena:");
	Panel rez=new Panel();
	rez.add(novcica);
	novci.addActionListener(this);
	rez.add(novci);
	rez.add(poena);
	rez.add(poeni);
	pocni.addActionListener(this);
	rez.add(pocni);
	add(rez,BorderLayout.PAGE_END);

}
public void dodajMeni() {
	MenuBar linija=new MenuBar();
	Menu rezim=new Menu("Rezim");
	MenuItem izm=new MenuItem("Rezim izmena");
	MenuItem igr=new MenuItem("Rezim igranje");
	izm.addActionListener(this);
	igr.addActionListener(this);
	rezim.add(izm);
	rezim.add(igr);
	rezim.addActionListener(this);
	linija.add(rezim);
	setMenuBar(linija);
}

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand()=="Rezim izmena") {
		if(izmena==false) m.zaustavi();
		izmena=true;
		igranje=false;
		return;
	}
	if(e.getActionCommand()=="Rezim igranje") {
		izmena=false;
		igranje=true;
		return;
	}
	if(e.getActionCommand()=="Pocni" && igranje==true) {
		m.brisiFigure();
		poens=0;
		poeni.setText(Integer.toString(0));
		pocela=true;
		old_poens=Integer.parseInt(novci.getText());
		m.dodajNovcice(Integer.parseInt(novci.getText()));
		m.dodajTenkove(Integer.parseInt(novci.getText())/3);
		m.dodajIgraca();
		m.pokreni();
		m.setVisible(true);
		return;
	}
	
}

public static void main(String[] args) {
	new Igra();

}

@Override
public void itemStateChanged(ItemEvent e) {
	if(((Checkbox)(e.getSource())).getLabel()=="Trava") tz=true;
	else tz=false;	
}

public void paint(Graphics g) {
	m.repaint();
}

}
