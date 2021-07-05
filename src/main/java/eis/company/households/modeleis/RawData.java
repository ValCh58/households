// Generated with g9.

package eis.company.households.modeleis;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="raw_data")
public class RawData implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idRawData";

   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_raw_data", unique=true, nullable=false, length=10)
    private int idRawData;
    
    @Column(name="event_time")
    private LocalDateTime eventTime;
    
    @Column(name="ip_server", length=45)
    private String ipServer;
    
    @Column(precision=11)
    private int port;
    
    @Column(name="num_uspd", length=45)
    private String numUspd;
    
    @Column(name="raw_data", length=512)
    private String rawData;

    /** Default constructor. */
    public RawData() {
        super();
    }

    /**
     * Access method for idRawData.
     *
     * @return the current value of idRawData
     */
    public int getIdRawData() {
        return idRawData;
    }

    /**
     * Setter method for idRawData.
     *
     * @param aIdRawData the new value for idRawData
     */
    public void setIdRawData(int aIdRawData) {
        idRawData = aIdRawData;
    }

    /**
     * Access method for eventTime.
     *
     * @return the current value of eventTime
     */
    public LocalDateTime getEventTime() {
        return eventTime;
    }

    /**
     * Setter method for eventTime.
     *
     * @param aEventTime the new value for eventTime
     */
    public void setEventTime(LocalDateTime aEventTime) {
        eventTime = aEventTime;
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
     * Access method for port.
     *
     * @return the current value of port
     */
    public int getPort() {
        return port;
    }

    /**
     * Setter method for port.
     *
     * @param aPort the new value for port
     */
    public void setPort(int aPort) {
        port = aPort;
    }

    /**
     * Access method for numUspd.
     *
     * @return the current value of numUspd
     */
    public String getNumUspd() {
        return numUspd;
    }

    /**
     * Setter method for numUspd.
     *
     * @param aNumUspd the new value for numUspd
     */
    public void setNumUspd(String aNumUspd) {
        numUspd = aNumUspd;
    }

    /**
     * Access method for rawData.
     *
     * @return the current value of rawData
     */
    public String getRawData() {
        return rawData;
    }

    /**
     * Setter method for rawData.
     *
     * @param aRawData the new value for rawData
     */
    public void setRawData(String aRawData) {
        rawData = aRawData;
    }

    /**
     * Compares the key for this instance with another RawData.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class RawData and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof RawData)) {
            return false;
        }
        RawData that = (RawData) other;
        if (this.getIdRawData() != that.getIdRawData()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another RawData.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RawData)) return false;
        return this.equalKeys(other) && ((RawData)other).equalKeys(this);
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
        i = getIdRawData();
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
        StringBuffer sb = new StringBuffer("[RawData |");
        sb.append(" idRawData=").append(getIdRawData());
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
        ret.put("idRawData", Integer.valueOf(getIdRawData()));
        return ret;
    }

}
