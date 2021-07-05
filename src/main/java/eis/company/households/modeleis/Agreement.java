// Generated with g9.

package eis.company.households.modeleis;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity(name="agreement")
public class Agreement implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "agrId";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="agr_id", unique=true, nullable=false, precision=10)
    private int agrId;
    @Column(name="cl_id", nullable=false, precision=10)
    private int clId;
    @Column(name="agr_num", nullable=false, length=10)
    private String agrNum;
    @Column(name="agr_date", nullable=false)
    private LocalDate agrDate;
    @Column(name="agr_fd", nullable=false)
    private LocalDate agrFd;
    @Column(name="agr_ld", nullable=false)
    private LocalDate agrLd;
    @Column(name="agr_address", nullable=false, length=250)
    private String agrAddress;
    @Column(name="agr_curr", nullable=false, precision=10)
    private int agrCurr;

    /** Default constructor. */
    public Agreement() {
        super();
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
     * Access method for clId.
     *
     * @return the current value of clId
     */
    public int getClId() {
        return clId;
    }

    /**
     * Setter method for clId.
     *
     * @param aClId the new value for clId
     */
    public void setClId(int aClId) {
        clId = aClId;
    }

    /**
     * Access method for agrNum.
     *
     * @return the current value of agrNum
     */
    public String getAgrNum() {
        return agrNum;
    }

    /**
     * Setter method for agrNum.
     *
     * @param aAgrNum the new value for agrNum
     */
    public void setAgrNum(String aAgrNum) {
        agrNum = aAgrNum;
    }

    /**
     * Access method for agrDate.
     *
     * @return the current value of agrDate
     */
    public LocalDate getAgrDate() {
        return agrDate;
    }

    /**
     * Setter method for agrDate.
     *
     * @param aAgrDate the new value for agrDate
     */
    public void setAgrDate(LocalDate aAgrDate) {
        agrDate = aAgrDate;
    }

    /**
     * Access method for agrFd.
     *
     * @return the current value of agrFd
     */
    public LocalDate getAgrFd() {
        return agrFd;
    }

    /**
     * Setter method for agrFd.
     *
     * @param aAgrFd the new value for agrFd
     */
    public void setAgrFd(LocalDate aAgrFd) {
        agrFd = aAgrFd;
    }

    /**
     * Access method for agrLd.
     *
     * @return the current value of agrLd
     */
    public LocalDate getAgrLd() {
        return agrLd;
    }

    /**
     * Setter method for agrLd.
     *
     * @param aAgrLd the new value for agrLd
     */
    public void setAgrLd(LocalDate aAgrLd) {
        agrLd = aAgrLd;
    }

    /**
     * Access method for agrAddress.
     *
     * @return the current value of agrAddress
     */
    public String getAgrAddress() {
        return agrAddress;
    }

    /**
     * Setter method for agrAddress.
     *
     * @param aAgrAddress the new value for agrAddress
     */
    public void setAgrAddress(String aAgrAddress) {
        agrAddress = aAgrAddress;
    }

    /**
     * Access method for agrCurr.
     *
     * @return the current value of agrCurr
     */
    public int getAgrCurr() {
        return agrCurr;
    }

    /**
     * Setter method for agrCurr.
     *
     * @param aAgrCurr the new value for agrCurr
     */
    public void setAgrCurr(int aAgrCurr) {
        agrCurr = aAgrCurr;
    }

    /**
     * Compares the key for this instance with another Agreement.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Agreement and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Agreement)) {
            return false;
        }
        Agreement that = (Agreement) other;
        if (this.getAgrId() != that.getAgrId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Agreement.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Agreement)) return false;
        return this.equalKeys(other) && ((Agreement)other).equalKeys(this);
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
        i = getAgrId();
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
        StringBuffer sb = new StringBuffer("[Agreement |");
        sb.append(" agrId=").append(getAgrId());
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
        ret.put("agrId", Integer.valueOf(getAgrId()));
        return ret;
    }

}
