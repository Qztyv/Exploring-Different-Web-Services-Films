package dao;

public class FilmTraditionalDAOProxy implements dao.FilmTraditionalDAO {
  private String _endpoint = null;
  private dao.FilmTraditionalDAO filmTraditionalDAO = null;
  
  public FilmTraditionalDAOProxy() {
    _initFilmTraditionalDAOProxy();
  }
  
  public FilmTraditionalDAOProxy(String endpoint) {
    _endpoint = endpoint;
    _initFilmTraditionalDAOProxy();
  }
  
  private void _initFilmTraditionalDAOProxy() {
    try {
      filmTraditionalDAO = (new dao.FilmTraditionalDAOServiceLocator()).getFilmTraditionalDAO();
      if (filmTraditionalDAO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)filmTraditionalDAO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)filmTraditionalDAO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (filmTraditionalDAO != null)
      ((javax.xml.rpc.Stub)filmTraditionalDAO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public dao.FilmTraditionalDAO getFilmTraditionalDAO() {
    if (filmTraditionalDAO == null)
      _initFilmTraditionalDAOProxy();
    return filmTraditionalDAO;
  }
  
  public int insertFilm(model.Film film) throws java.rmi.RemoteException{
    if (filmTraditionalDAO == null)
      _initFilmTraditionalDAOProxy();
    return filmTraditionalDAO.insertFilm(film);
  }
  
  public int updateFilm(model.Film film) throws java.rmi.RemoteException{
    if (filmTraditionalDAO == null)
      _initFilmTraditionalDAOProxy();
    return filmTraditionalDAO.updateFilm(film);
  }
  
  public model.Film[] getAllFilmsByTitle(java.lang.String searchName) throws java.rmi.RemoteException{
    if (filmTraditionalDAO == null)
      _initFilmTraditionalDAOProxy();
    return filmTraditionalDAO.getAllFilmsByTitle(searchName);
  }
  
  public model.Film getFilmByID(int id) throws java.rmi.RemoteException{
    if (filmTraditionalDAO == null)
      _initFilmTraditionalDAOProxy();
    return filmTraditionalDAO.getFilmByID(id);
  }
  
  public model.Film[] getAllFilms() throws java.rmi.RemoteException{
    if (filmTraditionalDAO == null)
      _initFilmTraditionalDAOProxy();
    return filmTraditionalDAO.getAllFilms();
  }
  
  public int deleteFilm(int filmID) throws java.rmi.RemoteException{
    if (filmTraditionalDAO == null)
      _initFilmTraditionalDAOProxy();
    return filmTraditionalDAO.deleteFilm(filmID);
  }
  
  
}