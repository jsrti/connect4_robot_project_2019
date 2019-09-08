/**
* @author laita tähän nimesi ja päiväys
*/

import java.io.*;

public class PerusTyypitettyIO {

	public static void kirjoita(int luvut[], String tiedosto) {
		FileOutputStream fileOut = null;
		DataOutputStream dataOut = null;

		// TODO: täydennä

	}

	public static int kerroKoko(String tiedosto) {
		FileInputStream f = null;

		// TODO: täydennä

		return 0;
	}

	public static byte[] annaTavuina(String tiedosto) {
		FileInputStream f = null;

		// TODO: täydennä

		return null; // Palauta byte-taulukko
	}

	public static int[] annaKokonaislukuina(String tiedosto) {
		FileInputStream f = null;
		DataInputStream din = null;

		// TODO: täydennä

		return null; // Palauta int-taulukko
	}


	public static void main(String args[]) {
		String tiedosto = "Luvut.bin";
		int luvut[] = { 1, 200, 3, 4, 5 };
		byte tavut[];
		int iLuvut[];

		kirjoita(luvut, tiedosto);

		System.out.println("Tiedostossa on " + kerroKoko(tiedosto) + " tavua");

		System.out.println ("Tiedoston sisältö tavuittain:");
		tavut = annaTavuina(tiedosto);
		for (int i = 0; i < tavut.length; i++) {
			System.out.print(tavut[i] + " ");
		}

		System.out.println ("\nTiedoston sisältö kokonaislukuina:");
		iLuvut = annaKokonaislukuina(tiedosto);
		for (int i = 0; i < iLuvut.length; i++) {
			System.out.print(iLuvut[i] + " ");
		}
	}

}
