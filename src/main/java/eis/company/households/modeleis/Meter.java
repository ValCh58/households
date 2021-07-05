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




@Entity(name="meter")
public class Meter implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "mtId";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="mt_id", unique=true, nullable=false, precision=10)
    private int mtId;
    @Column(name="uspd_id", nullable=false, precision=10)
    private int uspdId;
    @Column(name="uspd_ch", nullable=false, precision=10)
    private int uspdCh;
    @Column(name="mt_type", nullable=false, precision=10)
    private int mtType;
    @Column(name="FACTORY_NUMBER_EN", nullable=false, length=32)
    private String factoryNumberEn;
    @Column(name="mt_name", nullable=false, length=100)
    private String mtName;

    /** Default constructor. */
    public Meter() {
        super();
    }

    /**
     * Access method for mtId.
     *
     * @return the current value of mtId
     */
    public int getMtId() {
        return mtId;
    }

    /**
     * Setter method for mtId.
     *
     * @param aMtId the new value for mtId
     */
    public void setMtId(int aMtId) {
        mtId = aMtId;
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
     * Access method for uspdCh.
     *
     * @return the current value of uspdCh
     */
    public int getUspdCh() {
        return uspdCh;
    }

    /**
     * Setter method for uspdCh.
     *
     * @param aUspdCh the new value for uspdCh
     */
    public void setUspdCh(int aUspdCh) {
        uspdCh = aUspdCh;
    }

    /**
     * Access method for mtType.
     *
     * @return the current value of mtType
     */
    public int getMtType() {
        return mtType;
    }

    /**
     * Setter method for mtType.
     *
     * @param aMtType the new value for mtType
     */
    public void setMtType(int aMtType) {
        mtType = aMtType;
    }

    /**
     * Access method for factoryNumberEn.
     *
     * @return the current value of factoryNumberEn
     */
    public String getFactoryNumberEn() {
        return factoryNumberEn;
    }

    /**
     * Setter method for factoryNumberEn.
     *
     * @param aFactoryNumberEn the new value for factoryNumberEn
     */
    public void setFactoryNumberEn(String aFactoryNumberEn) {
        factoryNumberEn = aFactoryNumberEn;
    }

    /**
     * Access method for mtName.
     *
     * @return the current value of mtName
     */
    public String getMtName() {
        return mtName;
    }

    /**
     * Setter method for mtName.
     *
     * @param aMtName the new value for mtName
     */
    public void setMtName(String aMtName) {
        mtName = aMtName;
    }

    /**
     * Compares the key for this instance with another Meter.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Meter and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Meter)) {
            return false;
        }
        Meter that = (Meter) other;
        if (this.getMtId() != that.getMtId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Meter.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Meter)) return false;
        return this.equalKeys(other) && ((Meter)other).equalKeys(this);
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
        i = getMtId();
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
        StringBuffer sb = new StringBuffer("[Meter |");
        sb.append(" mtId=").append(getMtId());
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
        ret.put("mtId", Integer.valueOf(getMtId()));
        return ret;
    }

}
