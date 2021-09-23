package dao;

public class FactoryDAO {
	/**
	 * This method is essentially our factory design pattern implemented. It is given a parameter "type"
	 * and a case statement is used to determine what singleton DAO to return, either a Hibernate version, or a Traditional.
	 * By default it will return Hibernate, since hibernate is better than the traditional for optimization and other reasons..
	 * 
	 * @param type (Hibernate or Traditional)
	 * @return an instance of IFilmDAO
	 */
	public static IFilmDAO getFilmDAO(String type)
	{
		switch(type)
		{
			case "Hibernate" :
				return FilmHibernateDAO.getDAOSingleton();
			case "Traditional" :
				return FilmTraditionalDAO.getDAOSingleton();
			default:
				return FilmHibernateDAO.getDAOSingleton();
		}
	}
}
