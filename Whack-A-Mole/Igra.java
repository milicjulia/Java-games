package prvi;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



@SuppressWarnings("serial")
public class Igra extends Frame implements ActionListener, ItemListener {
	
static Basta basta=new Basta(4,4);
Label tezina=new Label("Tezina: ",Label.CENTER);
Label povrc=new Label("Povrce: ");
static Label povrce=new Label(Integer.toString(basta.povrce));
Checkbox[] chb=new Checkbox[3];
CheckboxGroup 	chbg=new CheckboxGroup();
Button stani=new Button("Kreni");
Panel tezine=new Panel(new GridLayout(5, 1));
static Panel povrca=new Panel();
Panel tezpo=new Panel(new GridLayout(2,1));

	private Igra() {
		super("Igra");
		dodajKomp();
		pratiPovrce();
		tezpo.setVisible(true);
		add(tezpo,BorderLayout.EAST);
		add(basta);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				basta.zavrsi();
				dispose();
			}
		});
		this.setSize(600,500);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawRect(basta.getX(), basta.getY(), 200, 200);
	}
	
	public void dodajKomp() {
		tezina.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		tezine.add(tezina);
	    chb[0]=new Checkbox("Lako", false, chbg);
	    chb[1]=new Checkbox("Srednje", false, chbg);
	    chb[2]=new Checkbox("Tesko", false, chbg);
		for(int i=0;i<3;i++) {
			chb[i].addItemListener(this);
		    tezine.add(chb[i]);
		}
		stani.addActionListener(this);
		tezine.add(stani);
		tezine.setVisible(true);
		tezpo.add(tezine);
	}
	
	public void pratiPovrce() {
		povrc.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		povrce.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		povrca.add(povrc);
		povrca.add(povrce);
		tezpo.add(povrca);
	}
	
	public static void azuriraj() {
		povrce.setText(Integer.toString(basta.povrce));
		if(basta.povrce==0) {basta.zavrsi();}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(((Checkbox)e.getSource()).getLabel()=="Lako") {
			basta.postBrKoraka(10);
			basta.postCekanje(1000);
		}
		if(((Checkbox)e.getSource()).getLabel()=="Srednje") {
			basta.postBrKoraka(8);
			basta.postCekanje(750);
		}
		if(((Checkbox)e.getSource()).getLabel()=="Tesko") {
			basta.postBrKoraka(6);
			basta.postCekanje(500);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Kreni") {
			basta.pokreni();
			chb[0].setEnabled(false);
			chb[1].setEnabled(false);
			chb[2].setEnabled(false);
			stani.setLabel("Stani");
			stani.setActionCommand("Stani");
			return;
			}
		stani.setLabel("Kreni");
		stani.setActionCommand("Kreni");
		basta.zaustavi();
	}
	
	public static void main(String[] args) {
		new Igra();
	}

}
