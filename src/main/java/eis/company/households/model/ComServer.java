// Generated with g9.

package eis.company.households.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name="com_server")
public class ComServer implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idComServer";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    @Version
    @Column(name="LOCK_FLAG")
    private Integer lockFlag;

    /**
     * Access method for the lockFlag property.
     *
     * @return the current value of the lockFlag property
     */
    public Integer getLockFlag() {
        return lockFlag;
    }

    /**
     * Sets the value of the lockFlag property.
     *
     * @param aLockFlag the new value of the lockFlag property
     */
    public void setLockFlag(Integer aLockFlag) {
        lockFlag = aLockFlag;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_com_server", unique=true, nullable=false, precision=11)
    private int idComServer;
    
    @Column(name="name_server", nullable=false, length=128)
    private String nameServer;
    
    @Column(name="ip_server", nullable=false, length=25)
    private String ipServer;
    
    @Column(name="port_server", nullable=false, length=10)
    private String portServer;
    
    @ManyToOne
    @JoinColumn(name="id_manag_company")
    private ManagCompany managCompany;
    
    @ManyToOne
    @JoinColumn(name="id_type_object")
    private TypeObject typeObject;
    
    /********** COM_SERVER ------< USPD_DEV ***************/
    @OneToMany(mappedBy="comServer")
    private List<UspdDev> uspdDev = new ArrayList<UspdDev>();

    /** Default constructor. */
    public ComServer() {
        super();
    }

    /**
     * Access method for idComServer.
     *
     * @return the current value of idComServer
     */
    public int getIdComServer() {
        return idComServer;
    }

    /**
     * Setter method for idComServer.
     *
     * @param aIdComServer the new value for idComServer
     */
    public void setIdComServer(int aIdComServer) {
        idComServer = aIdComServer;
    }

    /**
     * Access method for nameServer.
     *
     * @return the current value of nameServer
     */
    public String getNameServer() {
        return nameServer;
    }

    /**
     * Setter method for nameServer.
     *
     * @param aNameServer the new value for nameServer
     */
    public void setNameServer(String aNameServer) {
        nameServer = aNameServer;
    }

    /**
     * Access method for ipServer.
     *
     * @return the current value of ipServer
     */
    public String getIpServer() {
        return ipServer;
    }

    /**
     * Setter method for ipServer.
     *
     * @param aIpServer the new value for ipServer
     */
    public void setIpServer(String aIpServer) {
        ipServer = aIpServer;
    }

    /**
     * Access method for portServer.
     *
     * @return the current value of portServer
     */
    public String getPortServer() {
        return portServer;
    }

    /**
     * Setter method for portServer.
     *
     * @param aPortServer the new value for portServer
     */
    public void setPortServer(String aPortServer) {
        portServer = aPortServer;
    }

    /**
     * Access method for managCompany.
     *
     * @return the current value of managCompany
     */
    public ManagCompany getManagCompany() {
        return managCompany;
    }

    /**
     * Setter method for managCompany.
     *
     * @param aManagCompany the new value for managCompany
     */
    public void setManagCompany(ManagCompany aManagCompany) {
        managCompany = aManagCompany;
    }

    /**
     * Access method for typeObject.
     *
     * @return the current value of typeObject
     */
    public TypeObject getTypeObject() {
        return typeObject;
    }

    /**
     * Setter method for typeObject.
     *
     * @param aTypeObject the new value for typeObject
     */
    public void setTypeObject(TypeObject aTypeObject) {
        typeObject = aTypeObject;
    }

    /**
     * Access method for uspdDev.
     *
     * @return the current value of uspdDev
     */
    public List<UspdDev> getUspdDev() {
        return uspdDev;
    }

    /**
     * Setter method for uspdDev.
     *
     * @param aUspdDev the new value for uspdDev
     */
    public void setUspdDev(List<UspdDev> aUspdDev) {
        uspdDev = aUspdDev;
    }

    /**
     * Compares the key for this instance with another ComServer.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class ComServer and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof ComServer)) {
            return false;
        }
        ComServer that = (ComServer) other;
        if (this.getIdComServer() != that.getIdComServer()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another ComServer.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ComServer)) return false;
        return this.equalKeys(other) && ((ComServer)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getIdComServer();
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[ComServer |");
        sb.append(" idComServer=").append(getIdComServer());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("idComServer", Integer.valueOf(getIdComServer()));
        return ret;
    }

}
