package model;

/**
 * @author Olli Kaivola 7.10.2019
 */

public interface IValuuttaDAO {
	public abstract boolean createValuutta(Valuutta valuutta);
	public abstract Valuutta readValuutta(String lyhenne);
	public abstract Valuutta[] readValuutat();
	public abstract boolean updateValuutta(Valuutta valuutta);
	public abstract boolean deleteValuutta(String lyhenne);
}
