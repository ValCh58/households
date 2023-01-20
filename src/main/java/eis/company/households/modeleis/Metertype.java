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


@Entity(name="metertype")
public class Metertype implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "mtType";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="mt_type", unique=true, nullable=false, precision=10)
    private int mtType;
    @Column(name="type_name", nullable=false, length=50)
    private String typeName;

    /** Default constructor. */
    public Metertype() {
        super();
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
     * Access method for typeName.
     *
     * @return the current value of typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Setter method for typeName.
     *
     * @param aTypeName the new value for typeName
     */
    public void setTypeName(String aTypeName) {
        typeName = aTypeName;
    }

    /**
     * Compares the key for this instance with another Metertype.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Metertype and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Metertype)) {
            return false;
        }
        Metertype that = (Metertype) other;
        if (this.getMtType() != that.getMtType()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Metertype.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Metertype)) return false;
        return this.equalKeys(other) && ((Metertype)other).equalKeys(this);
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
        i = getMtType();
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
        StringBuffer sb = new StringBuffer("[Metertype |");
        sb.append(" mtType=").append(getMtType());
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
        ret.put("mtType", Integer.valueOf(getMtType()));
        return ret;
    }

}
