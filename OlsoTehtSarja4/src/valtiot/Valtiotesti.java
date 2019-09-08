package valtiot;

/**
* @author laita tähän nimesi ja päiväys
*/

import java.util.List;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Valtiotesti {

	public static void main(String[] args) {

		// ArrayList-muuttuja valtio-olioiden tallettamiseksi
	    List<Valtio> valtiot = new ArrayList<>();
		Valtio valtio;
		String nimi;
		String paakaupunki;
		String asukaslukuStr;
		int asukasluku;

		// Kysy valtioita, kunnes syötteenä annetaan tyhjä
		
		nimi = JOptionPane.showInputDialog("Anna valtion nimi: ");
		while (nimi.compareTo("") != 0) {

			paakaupunki = JOptionPane.showInputDialog("Anna valtion " + nimi + " pääkaupunki ");
			asukaslukuStr = JOptionPane.showInputDialog("Anna valtion " + nimi + " asukasluku");
			asukasluku = Integer.parseInt(asukaslukuStr); // Tarvitsisi poikkeukseen varautumisen

			// Luo uusi Valtio-olio ja vie se listaan

			nimi = JOptionPane.showInputDialog("Anna valtion nimi: ");
		}

		// Tulosta valtiot listasta, käytä for/each-rakennetta

		// Kirjoita lista tiedostoon
		// TiedostonKasittely.kirjoitaTiedostoon("valtiot.dat", valtiot);
				
		// Lue lista tiedostosta
		// List<Valtio> luetut =  TiedostonKasittely.lueTiedostosta("valtiot.dat");		
		
		// Tulosta saatu lista
		// System.out.println(luetut);
	}

}
