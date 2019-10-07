package model;

import javax.persistence.*;

/**
 * @author Olli Kaivola 7.10.2019
 */
 
@Entity
@Table(name="valuutta")
public class Valuutta {


	@Column(name ="nimi")
	private String nimi;
	
	@Id
	@Column(name ="lyhenne")
	private String lyhenne;
	
	@Column(name ="kurssi")
	private double kurssi;

	public Valuutta(String nimi, String lyhenne, double kurssi) {
		super();
		this.nimi = nimi;
		this.lyhenne = lyhenne;
		this.kurssi = kurssi;
	}

	public Valuutta() {
		super();
	}


	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getLyhenne() {
		return lyhenne;
	}

	public void setLyhenne(String lyhenne) {
		this.lyhenne = lyhenne;
	}

	public double getKurssi() {
		return kurssi;
	}

	public void setKurssi(double kurssi) {
		this.kurssi = kurssi;
	}

}