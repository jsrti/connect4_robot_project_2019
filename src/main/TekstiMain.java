package main;
 
import java.util.InputMismatchException;
import java.util.Scanner;
import model.*;

/**
 * @author Olli Kaivola 7.10.2019
 */

public class TekstiMain {
	static ValuuttaAccessObject valuuttaDAO = new ValuuttaAccessObject();
	static Scanner scanner = new Scanner(System.in);
	private static String nimi;
	private static String lyhenne;
	private static double kurssi;
	

	public static void listaaValuutat() {
		valuuttaDAO.readValuutat();
	}
	
	public static void lueValuutta(String lyhenne) {
		Valuutta valuuttaR = valuuttaDAO.readValuutta(lyhenne);
		System.out.println(valuuttaR.getNimi() + " " + valuuttaR.getLyhenne() + " " + valuuttaR.getKurssi());
	}

	public static void lisääValuutta() {
		Valuutta valuuttaC = new Valuutta(nimi, lyhenne, kurssi);
		valuuttaDAO.createValuutta(valuuttaC);
		
	}

	public static void päivitäValuutta() {
		Valuutta valuuttaU = new Valuutta(nimi, lyhenne, kurssi);
		valuuttaDAO.updateValuutta(valuuttaU);
	}

	public static void poistaValuutta() {
		valuuttaDAO.deleteValuutta(lyhenne);
	}

	public static void main(String[] args) {
		char valinta = 0;
		final char LIST = 'L', CREATE = 'C', READ = 'R', UPDATE = 'U', DELETE = 'D', QUIT = 'Q';

		do {
			try {
				System.out.print("C: Lisää uusi valuutta tietokantaan\nL: Listaa tietokannassa olevien valuuttojen "
						+ "tiedot\nR: Listaa yhden tietokannassa olevan valuutan arvo\nU: Päivitä valuutan "
						+ "vaihtokurssi tietokantaan\nD: Poista valuutta tietokannasta\nQ: Lopetus\nValintasi: ");
				valinta = (scanner.nextLine().toUpperCase()).charAt(0);
				switch (valinta) {
				case LIST:
					listaaValuutat();
					break;
			
				case READ:
					System.out.print("Kirjoita halutun valuutaan tunnus: ");
					lyhenne = scanner.nextLine();
					lueValuutta(lyhenne);
					break;
			
				case CREATE:
					System.out.println("Kirjoita halutun valuutaan nimi: ");
					nimi = scanner.next();
					nimi += scanner.nextLine();
					System.out.println("Kirjoita halutun valuutaan lyhenne: ");
					lyhenne = scanner.nextLine();
					System.out.println("Kirjoita halutun valuutaan kurssi: ");
					kurssi = scanner.nextDouble();
					lisääValuutta();
					break;
					
				case UPDATE:
					System.out.print("Kirjoita halutun valuutaan lyhenne: ");
					lyhenne = scanner.nextLine();
					System.out.println("Kirjoita uusi nimi: ");
					nimi = scanner.next();
					nimi += scanner.nextLine();
					System.out.println("Kirjoita uusi kurssi: ");
					kurssi = scanner.nextDouble();
					päivitäValuutta();
					break;
					
				case DELETE:
					System.out.println("Kirjoita poistettavan valuutan lyhenne");
					lyhenne = scanner.nextLine();
					poistaValuutta();
					break;
				}
			}catch(InputMismatchException e) {
				System.out.println("Error");
				continue;
			}
		} while (valinta != QUIT);
	}
}
