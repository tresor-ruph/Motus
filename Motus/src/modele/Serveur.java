/**
 *  @author tresor
 */
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author tresor
 * host : String : hote hebergent la BDD
 * user : String : utilisateur
 * passwd : String : mot de passe
 * Lib : HashMap : association mot et indice
 * Lib2 : HashMAP : association mot et definition
 */
public class Serveur {

	int id;
	String mot;
	String signification;
	String niveau;
	public String[] dict;
	public String[] dict2;
	public String[] dict3;

	public HashMap<String, String> getLib() {
		return lib;
	}

	public void setLib(HashMap<String, String> lib) {
		this.lib = lib;
	}

	public HashMap<String, String> getLib2() {
		return lib2;
	}

	public void setLib2(HashMap<String, String> lib2) {
		this.lib2 = lib2;
	}

	/**
	 * permet de sauvegarder les mots et le niveau de dificulte recupere depuis la
	 * BDD
	 */
	HashMap<String, String> lib = new HashMap<String, String>();
	HashMap<String, String> lib2 = new HashMap<String, String>();

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	final private String host = "jdbc:mysql://sql7.freemysqlhosting.net";
	final private String user = "sql7343279";
	final private String passwd = "lm5ksRt97g";

	/**
	 * @throws Exception cette methode permet de recuperer les données depuis la BDD
	 */
	public void readDataBase() throws Exception {
		try {
			// Connexion a la BDD
			connect = DriverManager.getConnection(host, user, passwd);

			// Permet d'effectuer des requettes sur la BDD
			statement = connect.createStatement();
			statement.executeQuery("use sql7343279");
			// ResultSet recupère les resultats de la requette
			resultSet = statement.executeQuery("select * from dictionary");
			writeResultSet(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	/**
	 * @param resultSet
	 * @throws SQLException Cette methode va nous permettre de parcourire et
	 *                      afficher les resultats de notre requette
	 */

	private void writeResultSet(ResultSet resultSet) throws SQLException {

		while (resultSet.next()) {
			this.id = resultSet.getInt("id");
			this.mot = resultSet.getString("mot");
			this.signification = resultSet.getString("signification");
			this.niveau = resultSet.getString("niveau");

			lib.put(this.mot, this.niveau);
			lib2.put(this.mot, this.signification);

		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * @param x : String [] : array contenant les mots a melanger cette methode
	 *          permet de melanger les mots dans un arr
	 */

	public void shuffle(String[] x) {

		List<String> stringList = Arrays.asList(x);
		Collections.shuffle(stringList);
		stringList.toArray(x);

	}

	/**
	 * la taille de dict, dict2 et dict3 devra etre la taille de la bdd divise par 3
	 */
	public void initWord() {
		int cnt = 0;
		int cnt2 = 0;
		int cnt3 = 0;
		try {
			this.readDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dict = new String[this.getLib().size() / 3];
		this.dict2 = new String[this.getLib().size() / 3];
		this.dict3 = new String[this.getLib().size() / 3];

		for (String i : this.getLib().keySet()) {

			if (this.getLib().get(i).equalsIgnoreCase("facile")) {
				this.dict[cnt] = i;
				++cnt;
			} else if (this.getLib().get(i).equalsIgnoreCase("meduim")) {

				this.dict2[cnt2] = i;
				++cnt2;
			} else if (this.getLib().get(i).equals("dificile")) {

				this.dict3[cnt3] = i;
				++cnt3;
			} else {

			}
		}
		this.shuffle(this.dict);
		this.shuffle(this.dict2);
		this.shuffle(this.dict3);

	}

	public static void main(String[] args) throws Exception {
		Serveur BDD = new Serveur();
		BDD.initWord();

	}
}
