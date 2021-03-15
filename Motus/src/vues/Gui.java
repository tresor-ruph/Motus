package vues;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import controleurs.*;
import modele.Partie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("deprecation")
public class Gui extends Vue implements Observer, ActionListener {
	/**
	 * f : JFrame : fenettre du jeu
	 */
	JFrame f;
	/**
	 * f1 : JFrame : fenettre permettant de choisir le niveau de dificulte
	 */
	JFrame f1;
	Panneau pan;
	private JTextField field1;
	JRadioButton jRadioButton1;
	JRadioButton jRadioButton2;
	JRadioButton jRadioButton3;
	JButton jButton;
	ButtonGroup G1;
	JLabel L1;

	public static int index = 0;

	public static char arr[][] = new char[5][6];
	public static char arr2[][] = new char[5][6];
	public static char arr3[][] = new char[5][6];

	/**
	 * @param model      : Partie
	 * @param controleur : control
	 *
	 */
	public Gui(Partie model, Controleur control) {
		super(model, control);
		this.home();
		this.f = new JFrame();
		this.pan = new Panneau(model.getMot2(), model.getMot(), model.getMess(), Gui.arr, Gui.arr2, Gui.arr3);
		f.setVisible(false);

	}

	/**
	 * affiche et permet a l'utilisateur de choisir son niveau de dificulte
	 */
	public void home() {
		f1 = new JFrame();
		f1.setTitle("MOTUS");
		f1.setSize(500, 550);

		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// f1.setLocationRelativeTo(null);

		f1.setResizable(false);

		f1.setLayout(null);
		JLabel char1 = new JLabel("M O T U S");
		char1.setForeground(Color.BLUE);
		char1.setFont(new Font("Verdana", Font.PLAIN, 45));

		jRadioButton1 = new JRadioButton();

		jRadioButton2 = new JRadioButton();

		jRadioButton3 = new JRadioButton();

		jButton = new JButton("commencer");

		G1 = new ButtonGroup();

		L1 = new JLabel("veuillez choisir votre niveau de difficulte");

		jRadioButton1.setText("Facile");

		jRadioButton2.setText("Normal");
		jRadioButton3.setText("difficile");
		char1.setBounds(100, 50, 250, 48);
		f1.add(char1);

		jRadioButton1.setBounds(120, 190, 120, 50);

		jRadioButton2.setBounds(120, 250, 120, 50);
		jRadioButton3.setBounds(120, 310, 80, 50);

		jButton.setBounds(125, 370, 120, 30);

		L1.setBounds(100, 130, 250, 50);

		f1.add(jRadioButton1);

		f1.add(jRadioButton2);
		f1.add(jRadioButton3);

		f1.add(jButton);

		f1.add(L1);

		G1.add(jRadioButton1);
		G1.add(jRadioButton2);
		G1.add(jRadioButton3);

		f1.setVisible(true);

		jButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (jRadioButton1.isSelected()) {
					control.setLevel("facile");

				}

				else if (jRadioButton2.isSelected()) {
					control.setLevel("meduim");
				} else {

					control.setLevel("dificile");
				}
				model.setWord();
				Gui.arr = new char[5][model.getMot().length()];
				Gui.arr2 = new char[5][model.getMot().length()];
				Gui.arr3 = new char[5][model.getMot().length()];
			}
		});
	}

	/**
	 * 
	 * @param mot   : String : mot a deviner
	 * @param mot2  : String : mot entre par l'utilisateur
	 * @param mess  : String : message a afficher a l'utilisateur
	 * @param char2
	 * @param char3
	 * @param char4
	 */

	public void fenetre(String mot, String mot2, String mess, char[][] char2, char[][] char3, char[][] char4) {
		f.setTitle("MOTUS");
		f.setSize(650, 550);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.setResizable(false);
		f.setContentPane(new Panneau(mot, mot2, mess, char2, char3, char4));
		f.getContentPane().setLayout(null);

		JLabel motx = new JLabel("Entrez Le Mot :");
		motx.setBounds(50, 305, 100, 65);
		f.getContentPane().add(motx);

		field1 = new JTextField();
		field1.setBounds(143, 320, 150, 25);
		f.getContentPane().add(field1);
		field1.setColumns(10);

		JButton b = new JButton("ok");
		b.setBounds(293, 320, 50, 25);
		f.getContentPane().add(b);
		b.addActionListener(this);
		JLabel point1 = new JLabel(modele.Partie.total + " sur " + modele.Partie.end);
		point1.setBounds(143, 350, 250, 65);
		f.getContentPane().add(point1);

		JLabel point = new JLabel(modele.Partie.success + " mot(s) trouvé sur " + modele.Partie.end);
		point.setBounds(143, 380, 250, 65);
		f.getContentPane().add(point);
		f.setVisible(true);
	}

	public void setArr2() {
		if ((model.getMess() == "reussi") || (model.getMess() == "echouer")) {
			Gui.arr = new char[5][model.getMot().length()];
			Gui.arr2 = new char[5][model.getMot().length()];
			Gui.arr3 = new char[5][model.getMot().length()];

			Gui.index = 0;
		}

		try {
			for (int j = 0; j < model.getMot().length(); j++) {
				Gui.arr2[Gui.index][j] = model.getMot2().charAt(j);

			}
		} catch (Exception e) {
			System.out.println();
		}

	}

	public void setArr() {

		for (int j = 0; j < model.getMot().length(); j++) {
			Gui.arr[Gui.index][j] = Partie.index[j];
		}

	}

	public void setArr3() {
		if ((model.getMess() == "reussi") || (model.getMess() == "echouer")) {
			Gui.arr = new char[5][model.getMot().length()];
			Gui.arr2 = new char[5][model.getMot().length()];
			Gui.arr3 = new char[5][model.getMot().length()];

			Gui.index = 0;
		}
		for (int j = 0; j < model.getMot().length(); j++) {
			Gui.arr3[Gui.index][j] = Partie.index2[j];

		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		f1.setVisible(false);
		f.setVisible(true);
		this.setArr();
		this.setArr2();
		this.setArr3();

		if ((model.getMess() == "reussi") || (model.getMess() == "echouer")) {
			if (model.getMess() == "reussi") {
				Gui.index = 0;

				Gui.arr = new char[5][model.getMot().length()];
				Gui.arr2 = new char[5][model.getMot().length()];
				Gui.arr3 = new char[5][model.getMot().length()];

				--Gui.index;

			}

		}

		if (model.getMess() == "reussi") {
			if (model.getLvl().equals("facile")) {
				JOptionPane.showMessageDialog(f, "BINGO \n" + model.dict[Partie.wordIndex - 1].toUpperCase());
			} else if (model.getLvl().equals("meduim")) {
				JOptionPane.showMessageDialog(f, "BINGO \n" + model.dict2[Partie.wordIndex - 1].toUpperCase());
			} else if (model.getLvl().equals("dificile")) {
				JOptionPane.showMessageDialog(f, "BINGO \n" + model.dict3[Partie.wordIndex - 1].toUpperCase());
			}
		} else if (model.getMess() == "echouer") {
			if (model.getLvl().equals("facile")) {
				JOptionPane.showMessageDialog(f,
						"LE MOT A DEVINER ETAIT \n" + model.dict[Partie.wordIndex - 1].toUpperCase());
			} else if (model.getLvl().equals("meduim")) {
				JOptionPane.showMessageDialog(f,
						"LE MOT A DEVINER ETAIT \n" + model.dict2[Partie.wordIndex - 1].toUpperCase());
			} else if (model.getLvl().equals("dificile")) {
				JOptionPane.showMessageDialog(f,
						"LE MOT A DEVINER ETAIT \n" + model.dict3[Partie.wordIndex - 1].toUpperCase());
			}
		} else if (model.getMess() == "termine") {
			if (Partie.success == Partie.end) {
				JOptionPane.showMessageDialog(f, "FELICITATION !!!\n VOUS AVEZ TROUVER TOUT LES MOTS");
			} else {
				JOptionPane.showMessageDialog(f,
						"LE MOT A DEVINER ETAIT \n" + model.dict3[Partie.wordIndex - 1].toUpperCase());

				JOptionPane.showMessageDialog(f,
						"FIN DE PARRTIE !!!\n" + "VOUS AVEZ TROUVER  " + Partie.success + "MOT(s) SUR  " + Partie.end);
			}
		}
		if (model.getMess() == "termine") {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			f.setVisible(false);
		} else {
			this.fenetre(model.getMot2(), model.getMot(), model.getMess(), Gui.arr, Gui.arr2, Gui.arr3);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String x = field1.getText();
		if ((x.length() < model.getMot().length()) || (x.length() > model.getMot().length())) {
			JOptionPane.showMessageDialog(f, "LA LONGEUR DU MOT A DEVINER EST DE " + model.getMot().length());

		} else {
			control.setString(x);

		}
	}

}
