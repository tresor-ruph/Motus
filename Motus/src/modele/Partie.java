/**
 * 
 */
package modele;

import java.util.Observable;

import vues.Gui;

@SuppressWarnings("deprecation")
public class Partie extends Observable {
	/**
	 * lvl : String : niveau de dificulte(facile, intermediare, dificile)
	 *
	 */
	public String lvl = " ";
	/**
	 * nombre de tentative restant par tour
	 */
	public static int chance = 5;
	/**
	 * nombre de reussite
	 */
	public static int success = 0;
	/**
	 * 
	 */
	public static int total = 0;
	/**
	 * nombre de tour total
	 */
	public static final int end = 5;

	public static int wordIndex = 0;
	/**
	 * mot a deviner
	 */
	public String mot;
	/**
	 * arr contient les mot facile
	 */
	public String[] dict;
	/**
	 * array contient les mot intermediare
	 */
	public String[] dict2;
	/**
	 * array contient les mots dificile
	 */
	public String[] dict3;
	public String mot2;
	/**
	 * message a afficher a l'utilisateur
	 */
	public static String mess;
	public static char[] index;
	public static char[] index2;

	public Partie() {
		Serveur serv = new Serveur();
		serv.initWord();
		this.dict = serv.dict;
		this.dict2 = serv.dict2;
		this.dict3 = serv.dict3;
	}

	/**
	 * recupere la valeur de chance
	 *
	 */

	public static int getChance() {
		return chance;
	}

	/**
	 * incremente la variable chance
	 *
	 */
	public static void setChance() {
		Partie.chance = --chance;
	}

	public char[] getIndex() {
		return index;
	}

	/**
	 * 
	 * @param i : int
	 * @param j : int
	 */
	public void setIndex(int i, char j) {
		Partie.index[i] = j;
	}

	public char[] getIndex2() {
		return index2;
	}

	/**
	 * @param i : int
	 * @param j : int
	 *
	 */

	public void setIndex2(int i, char j) {
		Partie.index2[i] = j;
	}

	public String getMess() {
		return mess;
	}

	/**
	 * 
	 * @param mess : String : message a afficher a l'utilisateur
	 */

	public void setMess(String mess) {
		Partie.mess = mess;

	}

	public static int getWordIndex() {
		return wordIndex;
	}

	public static void setWordIndex() {
		++Partie.wordIndex;
	}

	public String getMot2() {
		return mot2;
	}

	/**
	 * @param mot2 : String : mot a definer
	 */

	public void setMot(String mot) {
		this.mot = mot;

	}

	/**
	 * 
	 * @param mot2 : String : mot entre par l'utilisateur
	 */
	public void setMot2(String mot2) {
		this.mot2 = mot2;
		this.word();
		setChanged();
		notifyObservers();
		++Gui.index;

	}

	public String getMot() {
		return mot;
	}

	public String getLvl() {
		return lvl;
	}

	/**
	 * 
	 * @param lvl : niveau de dificulte du jeu. peut prendre 3 valeur facile, meduim
	 *            et dificile.
	 */

	public void setLvl(String lvl) {
		this.lvl = lvl;

		this.setWord();
		Gui.arr = new char[5][this.getMot().length()];
		Gui.arr2 = new char[5][this.getMot().length()];
		Gui.arr3 = new char[5][this.getMot().length()];

		setChanged();
		notifyObservers();
	}

	/**
	 * La method setWord va comparer choisir un mot a definer en fonction du niveau
	 * de dificulte entre par l'utilisateur
	 */

	public void setWord() {

		if (this.getLvl().equalsIgnoreCase("facile")) {
			this.setMot(this.dict[Partie.wordIndex]);
		} else if (this.getLvl().equalsIgnoreCase("meduim")) {
			this.setMot(this.dict2[Partie.wordIndex]);

		} else if (this.getLvl().equalsIgnoreCase("dificile")) {
			this.setMot(this.dict3[Partie.wordIndex]);

		} else {
			System.out.println("something went wrong");
		}
		Partie.index = new char[this.getMot().length()];
		Partie.index2 = new char[this.getMot().length()];

	}

	/**
	 * La methode word permet va comparer le mot a definer a celui entre par
	 * l'utilisateur
	 * 
	 */
	public void word() {
		this.setMess(" ");
		for (int i = 0; i < this.getMot().length(); i++) {
			if (this.getMot().toUpperCase().charAt(i) == this.getMot2().toUpperCase().charAt(i)) {
				this.setIndex(i, this.getMot2().charAt(i));

			} else {
				innerloop: for (int j = 0; j < this.getMot().length(); j++) {
					if (this.getMot().toUpperCase().charAt(j) == this.getMot2().toUpperCase().charAt(i)) {

						this.setIndex2(i, this.getMot2().charAt(i));

						break innerloop;

					}
				}
			}
		}

		if (this.getMot().equalsIgnoreCase(this.getMot2())) {
			for (int i = 0; i < this.getMot().length(); i++) {
				this.setIndex(i, this.getMot().charAt(i));
			}
			this.setMess("reussi");

			++Partie.success;
			++Partie.total;
			++Partie.wordIndex;
			Partie.chance = 5;
			Partie.index = new char[this.getMot().length()];
			Partie.index2 = new char[this.getMot().length()];
			if (this.getLvl().equals("facile")) {
				this.setMot(this.dict[Partie.wordIndex]);
			} else if (this.getLvl().equals("meduim")) {
				this.setMot(this.dict2[Partie.wordIndex]);

			} else if (this.getLvl().equals("dificile")) {
				this.setMot(this.dict3[Partie.wordIndex]);

			}

		}

		if (Partie.getChance() == 0) {
			Gui.index = 0;
			Gui.arr = new char[5][6];
			Gui.arr2 = new char[5][6];
			this.setMess("echouer");
			Partie.chance = 5;
			++Partie.total;

		}
		if (Partie.total == Partie.end) {

			this.setMess("termine");

			Partie.index = new char[this.getMot().length()];
			Partie.index2 = new char[this.getMot().length()];

		}

	}

}
