/**
 * 
 */
package vues;

import controleurs.Controleur;
import modele.Partie;
import java.util.Observable;
import java.util.Observer;

/**
 * @author tresor
 *
 */
public abstract class Vue implements Observer {

	protected Partie model;
	protected Controleur control;

	@SuppressWarnings("deprecation")
	public Vue(Partie model, Controleur control) {
		this.model = model;
		this.control = control;
		model.addObserver(this);
	}

}
