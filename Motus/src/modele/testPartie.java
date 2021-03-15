package modele;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
class testPartie {

	@Test
	/*
	 * On verfie qu'il ya un mot pour le niveau choisi 
	 * par l'utilisateur
	 */
	void testSetWord() {
		Partie mod = new Partie();
		Serveur serv = new Serveur();
		mod.lvl = "facile";
		try {
			serv.readDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(serv.niveau, mod.lvl);
		
		
	}

	@Test
	
	/**
	 * On verifie juste is la comparaiso des mot estt bien effectue
	 */
	void testWord() {
		boolean statement = false;
		Partie mod =new Partie();
		mod.setMot("test");
		mod.mot2 = "unit";
		Partie.index = new char[mod.mot.length()];
		Partie.index2 = new char[mod.mot.length()];
		mod.word();
		
		for(int i = 0; i< Partie.index.length; i++) {
			if((Partie.index[i] >='a' )||(Partie.index[i]>='A')){
				statement = true;
				break;
			}else {
				if(i == Partie.index.length -1) {
					statement = false;
				}
			}
		}
		Assert.assertEquals(statement, true);
	}

}
