package modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

@SuppressWarnings("unused")
class testDB {

	@SuppressWarnings("deprecation")
	@Test
	void testReadDataBase() {
		Serveur serv = new Serveur();
		try {
			serv.readDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(serv.id, 11);
		Assert.assertEquals(serv.mot, "button");
		Assert.assertEquals(serv.signification, "lol");
		Assert.assertEquals(serv.niveau, "facile");

	}

}
