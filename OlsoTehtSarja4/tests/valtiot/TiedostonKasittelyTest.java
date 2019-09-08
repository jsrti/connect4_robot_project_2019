package valtiot;

/**
* @author hakka. Muokattu 28.9.2019
*/

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TiedostonKasittelyTest {

	private static List<Valtio> valtioLista = new ArrayList<>();

	// Suoritetaan ennen kaikkia testejä
	@BeforeAll
	public static void luoLista() {
		valtioLista.add(new Valtio("Suomi", "Helsinki", 634940));
		valtioLista.add(new Valtio("Viro", "Tallinna", 440112));
	}

	// Suoritetaan kaikkien testien jälkeen
	@AfterAll
	public static void poistaTiedosto() {
		File f = new File("testi.dat");
		f.delete();
	}

	@Test
	public void testKirjoitaTiedosto() {
		TiedostonKäsittely.kirjoitaTiedosto("testi.dat", valtioLista);
		File f = new File("testi.dat");
		assertTrue(f.exists(), "Tiedostoa ei löydy");
	}

	@Test
	public void testLueTiedosto() {
		TiedostonKäsittely.kirjoitaTiedosto("testi.dat", valtioLista);
		List<Valtio> luetut = TiedostonKäsittely.lueTiedosto("testi.dat");
		assertEquals(valtioLista.toString(), luetut.toString(), "Luettu tiedosto ei vastaa kirjoitettua.");
	}

}
