package valtiot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValtioTest {

	private static Valtio valtio;

	@BeforeEach
	public void alkutoimet() {
		valtio = new Valtio("Suomi", "Helsinki", 634940);
	}

	@Test
	public void testValtio() {
		assertEquals("Suomi", valtio.getNimi(), "Nimi väärin ");
		assertEquals("Helsinki", valtio.getPääkaupunki(), "Pääkaupunki väärin ");
		assertEquals(634940, valtio.getAsukasluku(), "Asukasluku väärin ");
	}

	@Test
	public void testGetNimi() {
		assertEquals("Suomi", valtio.getNimi(), "Nimi väärin ");
	}

	@Test
	public void testSetNimi() {
		valtio.setNimi("Finland");
		assertEquals("Finland", valtio.getNimi(), "Nimi väärin ");
	}

	@Test
	public void testGetPääkaupunki() {
		assertEquals("Helsinki", valtio.getPääkaupunki(), "Pääkaupunki väärin ");
	}

	@Test
	public void testSetPääkaupunki() {
		valtio.setPääkaupunki("Helsingfors");
		assertEquals("Helsingfors", valtio.getPääkaupunki(), "Pääkaupunki väärin ");
	}

	@Test
	public void testGetAsukasluku() {
		assertEquals(634940, valtio.getAsukasluku(), "Asukasluku väärin ");
	}

	@Test
	public void testSetAsukasluku() {
		valtio.setAsukasluku(20);
		assertEquals(20, valtio.getAsukasluku(), "Asukasluku väärin ");
	}

	@Test
	public void testToString() {
		String str = valtio.toString();
		if (!str.contains(valtio.getNimi()))
			fail("Nimi väärin : pitäisi olla " + valtio.getNimi());
		if (!str.contains(valtio.getPääkaupunki()))
			fail("Pääkaupunki väärin : pitäisi olla " + valtio.getPääkaupunki());
		if (!str.contains(String.valueOf(valtio.getAsukasluku())))
			fail("Asukasluku väärin : pitäisi olla " + valtio.getAsukasluku());
	}

}
