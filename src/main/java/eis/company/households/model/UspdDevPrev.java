// Generated with g9.

package eis.company.households.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="uspd_dev_prev")
public class UspdDevPrev implements Serializable {

    
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idUspdDevPrev";

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_uspd_dev_prev", unique=true, nullable=false)
    private int idUspdDevPrev;
    @Column(name="name_uspd_dev_prev", nullable=false, length=128)
    private String nameUspdDevPrev;
    @Column(name="num_uspd_dev_prev", nullable=false, length=28)
    private String numUspdDevPrev;

    /** Default constructor. */
    public UspdDevPrev() {
        super();
    }

    /**
     * Access method for idUspdDevPrev.
     *
     * @return the current value of idUspdDevPrev
     */
    public int getIdUspdDevPrev() {
        return idUspdDevPrev;
    }

    /**
     * Setter method for idUspdDevPrev.
     *
     * @param aIdUspdDevPrev the new value for idUspdDevPrev
     */
    public void setIdUspdDevPrev(int aIdUspdDevPrev) {
        idUspdDevPrev = aIdUspdDevPrev;
    }

    /**
     * Access method for nameUspdDevPrev.
     *
     * @return the current value of nameUspdDevPrev
     */
    public String getNameUspdDevPrev() {
        return nameUspdDevPrev;
    }

    /**
     * Setter method for nameUspdDevPrev.
     *
     * @param aNameUspdDevPrev the new value for nameUspdDevPrev
     */
    public void setNameUspdDevPrev(String aNameUspdDevPrev) {
        nameUspdDevPrev = aNameUspdDevPrev;
    }

    /**
     * Access method for numUspdDevPrev.
     *
     * @return the current value of numUspdDevPrev
     */
    public String getNumUspdDevPrev() {
        return numUspdDevPrev;
    }

    /**
     * Setter method for numUspdDevPrev.
     *
     * @param aNumUspdDevPrev the new value for numUspdDevPrev
     */
    public void setNumUspdDevPrev(String aNumUspdDevPrev) {
        numUspdDevPrev = aNumUspdDevPrev;
    }

    /**
     * Compares the key for this instance with another UspdDevPrev.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class UspdDevPrev and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof UspdDevPrev)) {
            return false;
        }
        UspdDevPrev that = (UspdDevPrev) other;
        if (this.getIdUspdDevPrev() != that.getIdUspdDevPrev()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another UspdDevPrev.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UspdDevPrev)) return false;
        return this.equalKeys(other) && ((UspdDevPrev)other).equalKeys(this);
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
        i = getIdUspdDevPrev();
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
        StringBuffer sb = new StringBuffer("[UspdDevPrev |");
        sb.append(" idUspdDevPrev=").append(getIdUspdDevPrev());
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
        ret.put("idUspdDevPrev", Integer.valueOf(getIdUspdDevPrev()));
        return ret;
    }

}
