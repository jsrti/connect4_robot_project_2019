package valtiot;

/**
* @author laita tähän nimesi ja päiväys
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class TiedostonKasittely {

    /* Kirjoittaa parametrina annettuun tiedostoon
	 * paremetrina annetun listan (olio), jossa Valtio-olioita
	 */
	static void kirjoitaTiedosto(String tiedostoNimi, List<Valtio> lista){

		FileOutputStream tiedosto = null;
		ObjectOutputStream os = null;

		// Avaa FileOutputStream-virta tiedostoon

		// Luo objektivirta tiedostoon kirjoittamista varten

		// Kirjoita objektivirtaan lista

		// Sulje tietovirrat

	    System.out.println("Tiedot tallennettu tiedostoon " + tiedostoNimi);
	}

	/* Lukee parametrina annetusta tiedostosta listan, jossa Valtio-olioita
     * Palauttaa listan tai null, jos listaa ei pystytä muodostamaan
	 */
	@SuppressWarnings("unchecked")
	static List<Valtio> lueTiedosto(String tiedostonNimi){

		List<Valtio> lista = null;
		FileInputStream tiedosto = null;
		ObjectInputStream is = null;

		// Avaa FileInputStream-virta tiedostoon

		// Luo objektivirta tiedostosta lukemista varten

		// Lue objektivirran sisältö listaan

		// Sulje tietovirrat

		System.out.println("Tiedot haettu tiedostosta " + tiedostoNimi);
		return lista;
	}

}
