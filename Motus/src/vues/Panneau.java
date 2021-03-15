/**
 * 
 */
package vues;

import javax.imageio.ImageIO;

/**
 * @author treso
 *
 */

import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@SuppressWarnings("serial")
public class Panneau extends JPanel {

	public static int chancecnt = 1;
	public String mot;

	public String mot2;
	public String mess;
	public char[][] char1;
	public char[][] char2;
	public char[][] char3;

	/**
	 * @param x  : String : Mot a definer par l'utilisateur
	 * @param x2 : String : Mot entre par l'utilisateur;
	 * @param x3 : String : message a afficher a l'utilisateur;
	 * @param y  : char[][] : array contenat chaque character du mot a definer.
	 *           L'emplacement de chaque character est bien precise
	 * @param z  : char[][] : array contenat chaque character present dans l'array
	 *           index de la classe Partie. L'emplacement de chaque character est
	 *           precise et permet de mettre en evidence les character trouve par
	 *           l'utilisateur
	 * @param z2 : char[][] : array contenat chaque character de l'array index2 de
	 *           la classee Partie. L'emplacement de chaque character est bien
	 *           precise
	 */
	public Panneau(String x, String x2, String x3, char[][] y, char[][] z, char[][] z2) {
		this.mot = x2;
		this.mot2 = x;
		this.char1 = y;
		this.char2 = z;
		this.mess = x3;
		this.char3 = z2;
	}

	public void paintComponent(Graphics g) {

		try {
			BufferedImage img = ImageIO.read(Panneau.class.getResource("/image/motus.jpg"));
			g.drawImage(img, 0, 0, 750, 550, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Aucune image n'a ete retrouvee !");
		}
		g.setColor(Color.gray);
		g.fill3DRect(47, 47, this.mot.length() * 50 + 7, 257, true);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < this.mot.length(); j++) {
				g.setColor(Color.DARK_GRAY);
				g.fill3DRect((j + 1) * 50, (i + 1) * 50, 50, 50, true);
			}
		}

		if (Partie.chance == 5) {

			JLabel char1 = new JLabel(this.mot.charAt(0) + " ");
			char1.setForeground(Color.ORANGE);
			char1.setFont(new Font("Verdana", Font.PLAIN, 30));
			char1.setBounds(52, 50, 48, 48);
			this.add(char1);

			for (int k = 0; k < this.mot.length(); k++) {

				JLabel char2 = new JLabel(". ");
				char2.setForeground(Color.ORANGE);
				char2.setFont(new Font("Verdana", Font.PLAIN, 30));
				char2.setBounds((k + 1) * 52, 50, 48, 48);
				this.add(char2);

			}
		} else if (Partie.chance < 5) {

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < this.mot2.length(); j++) {
					if (Gui.arr2[i][j] >= 'a' || Gui.arr2[i][j] >= 'A') {
						JLabel char2 = new JLabel(Gui.arr2[i][j] + " ");
						char2.setForeground(Color.ORANGE);
						char2.setFont(new Font("Verdana", Font.PLAIN, 30));
						char2.setBounds((j + 1) * 52, (i + 1) * 50, 48, 48);
						this.add(char2);

						if (Gui.arr[i][j] >= 'a' || Gui.arr[i][j] >= 'A') {
							if (Gui.arr[i][j] == Gui.arr2[i][j]) {
								Color mycolor = new Color(0, 255, 0, 127);
								g.setColor(mycolor);
								g.fillRoundRect((j + 1) * 50, (i + 1) * 50, 50, 50, 50, 50);
							}

						}
						if (Gui.arr3[i][j] >= 'a' || Gui.arr3[i][j] >= 'A') {
							if (Gui.arr2[i][j] == Gui.arr3[i][j]) {
								Color mycolor = new Color(208, 96, 57, 190);
								g.setColor(mycolor);
								g.fillRoundRect((j + 1) * 50, (i + 1) * 50, 50, 50, 50, 50);
							}
						}

					}

				}
			}

		}

	}

}
