/**
 * FilmTraditionalDAO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package dao;

public interface FilmTraditionalDAO extends java.rmi.Remote {
    public int insertFilm(model.Film film) throws java.rmi.RemoteException;
    public int updateFilm(model.Film film) throws java.rmi.RemoteException;
    public model.Film[] getAllFilmsByTitle(java.lang.String searchName) throws java.rmi.RemoteException;
    public model.Film getFilmByID(int id) throws java.rmi.RemoteException;
    public model.Film[] getAllFilms() throws java.rmi.RemoteException;
    public int deleteFilm(int filmID) throws java.rmi.RemoteException;
}
