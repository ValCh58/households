// Generated with g9.

package eis.company.households.modeleis;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity(name="uspd")
public class Uspd implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "uspdId";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="uspd_id", unique=true, nullable=false, precision=10)
    private int uspdId;
    @Column(name="agr_id", nullable=false, precision=10)
    private int agrId;
    @Column(name="FACTORY_NUMBER_USPD", nullable=false, length=32)
    private String factoryNumberUspd;

    /** Default constructor. */
    public Uspd() {
        super();
    }

    /**
     * Access method for uspdId.
     *
     * @return the current value of uspdId
     */
    public int getUspdId() {
        return uspdId;
    }

    /**
     * Setter method for uspdId.
     *
     * @param aUspdId the new value for uspdId
     */
    public void setUspdId(int aUspdId) {
        uspdId = aUspdId;
    }

    /**
     * Access method for agrId.
     *
     * @return the current value of agrId
     */
    public int getAgrId() {
        return agrId;
    }

    /**
     * Setter method for agrId.
     *
     * @param aAgrId the new value for agrId
     */
    public void setAgrId(int aAgrId) {
        agrId = aAgrId;
    }

    /**
     * Access method for factoryNumberUspd.
     *
     * @return the current value of factoryNumberUspd
     */
    public String getFactoryNumberUspd() {
        return factoryNumberUspd;
    }

    /**
     * Setter method for factoryNumberUspd.
     *
     * @param aFactoryNumberUspd the new value for factoryNumberUspd
     */
    public void setFactoryNumberUspd(String aFactoryNumberUspd) {
        factoryNumberUspd = aFactoryNumberUspd;
    }

    /**
     * Compares the key for this instance with another Uspd.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Uspd and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Uspd)) {
            return false;
        }
        Uspd that = (Uspd) other;
        if (this.getUspdId() != that.getUspdId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Uspd.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Uspd)) return false;
        return this.equalKeys(other) && ((Uspd)other).equalKeys(this);
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
        i = getUspdId();
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
        StringBuffer sb = new StringBuffer("[Uspd |");
        sb.append(" uspdId=").append(getUspdId());
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
        ret.put("uspdId", Integer.valueOf(getUspdId()));
        return ret;
    }

}
