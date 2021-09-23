/**
 * FilmTraditionalDAOServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package dao;

public class FilmTraditionalDAOServiceLocator extends org.apache.axis.client.Service implements dao.FilmTraditionalDAOService {

    public FilmTraditionalDAOServiceLocator() {
    }


    public FilmTraditionalDAOServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FilmTraditionalDAOServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FilmTraditionalDAO
    private java.lang.String FilmTraditionalDAO_address = "http://localhost:8080/FilmSOAPWebService/services/FilmTraditionalDAO";

    public java.lang.String getFilmTraditionalDAOAddress() {
        return FilmTraditionalDAO_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FilmTraditionalDAOWSDDServiceName = "FilmTraditionalDAO";

    public java.lang.String getFilmTraditionalDAOWSDDServiceName() {
        return FilmTraditionalDAOWSDDServiceName;
    }

    public void setFilmTraditionalDAOWSDDServiceName(java.lang.String name) {
        FilmTraditionalDAOWSDDServiceName = name;
    }

    public dao.FilmTraditionalDAO getFilmTraditionalDAO() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FilmTraditionalDAO_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFilmTraditionalDAO(endpoint);
    }

    public dao.FilmTraditionalDAO getFilmTraditionalDAO(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            dao.FilmTraditionalDAOSoapBindingStub _stub = new dao.FilmTraditionalDAOSoapBindingStub(portAddress, this);
            _stub.setPortName(getFilmTraditionalDAOWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFilmTraditionalDAOEndpointAddress(java.lang.String address) {
        FilmTraditionalDAO_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (dao.FilmTraditionalDAO.class.isAssignableFrom(serviceEndpointInterface)) {
                dao.FilmTraditionalDAOSoapBindingStub _stub = new dao.FilmTraditionalDAOSoapBindingStub(new java.net.URL(FilmTraditionalDAO_address), this);
                _stub.setPortName(getFilmTraditionalDAOWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("FilmTraditionalDAO".equals(inputPortName)) {
            return getFilmTraditionalDAO();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://dao", "FilmTraditionalDAOService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://dao", "FilmTraditionalDAO"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FilmTraditionalDAO".equals(portName)) {
            setFilmTraditionalDAOEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
