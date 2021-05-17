package treci;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Igra extends Frame{
	
Scena scena;

public Igra() {
	super("Igra");
	setSize(400, 400);
	scena=new Scena(this);
	add(scena);
	this.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent we) {
			scena.zavrsi();
			dispose();
		}
	});
	setVisible(true);
	this.setResizable(false);
	scena.pokreni();
	scena.pokreniScenu();
}

public static void main(String[] s) {
	new Igra();
}
}
