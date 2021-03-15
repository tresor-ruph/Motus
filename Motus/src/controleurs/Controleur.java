/**
 * 
 */
package controleurs;

import modele.Partie;
import vues.Console;

/**
 * @author tresor
 *
 */

public class Controleur {

	private Partie model;
	private Console vueConsole = null;

	/**
	 * @param model : partie
	 *
	 */

	public Controleur(Partie model) {
		this.model = model;
	}

	public void setString(String s) {
		if (s.length() != model.getMot().length()) {
			System.out.println("la longeur du mot a definer est " + model.getMot().length());
		} else {
			this.setChance();
			model.setMot2(s);
		}
	}

	public String getMessag() {
		return model.getMess();
	}

	public void setChance() {
		Partie.setChance();
	}

	public void setWordcnt() {
		Partie.setWordIndex();
		model.setMot(model.dict[Partie.wordIndex]);
	}

	public void setLevel(String s) {
		model.setLvl(s);
	}
}
