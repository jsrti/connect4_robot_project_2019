package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;

/**
 * @author Olli Kaivola 7.10.2019
 */

public class ValuuttaAccessObject implements IValuuttaDAO {
	private SessionFactory istuntotehdas;
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	
	//Konstruktori
	public ValuuttaAccessObject() {
		//SessionFactoryn luonti
		try {
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch(Exception e) {
			System.out.println("Istuntotehtaan luonti ei onnistunut");
			StandardServiceRegistryBuilder.destroy( registry );
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	@Override
	public boolean createValuutta(Valuutta valuutta) {
		Transaction transaktio = null;
		try(Session istunto = istuntotehdas.openSession()){
			transaktio = istunto.beginTransaction();
			valuutta = new Valuutta(valuutta.getNimi(), valuutta.getLyhenne(), valuutta.getKurssi());
			istunto.saveOrUpdate(valuutta);
			transaktio.commit();
			return true;
		}
		catch(Exception e){
			if (transaktio!=null) transaktio.rollback();
			throw e;
		}
		
	}

	@Override
	public Valuutta readValuutta(String lyhenne) {
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();

		Valuutta val = new Valuutta();
		try{
			istunto.load(val, lyhenne);
			istunto.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("Valuutta not found");
		}
		istunto.close();
		return val;
	}

	@Override
	public Valuutta[] readValuutat() {
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		List result = istunto.createQuery( "from Valuutta" ).list();
		for ( Valuutta v : (List<Valuutta>) result ) {
			System.out.println( "Valuutta (" + v.getLyhenne() + ") : " + v.getKurssi() );
		}
		istunto.getTransaction().commit();
		istunto.close();
		Valuutta[] returnArray = new Valuutta[result.size()];
		return (Valuutta[])result.toArray(returnArray);
	}

	@Override
	public boolean updateValuutta(Valuutta valuuttaU) {
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Valuutta v = (Valuutta)istunto.get(Valuutta.class, valuuttaU.getLyhenne());
		if(v != null) {
			v.setKurssi(valuuttaU.getKurssi());
			istunto.getTransaction().commit();
			return true;
		}else {
			System.out.println("Ei löytynyt päivitettävää");
		}
		istunto.close();
		return false;
	}

	@Override
	public boolean deleteValuutta(String lyhenne) {
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Valuutta v = (Valuutta)istunto.get(Valuutta.class, lyhenne);
		if(v != null) {
			istunto.delete(v);
			istunto.getTransaction().commit();
			return true;
		}else {
			System.out.println("Ei löytynyt poistettavaa");
		}
		istunto.close();
		return false;
	}
	
}
