// Generated with g9.

package eis.company.households.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity(name="foto_counts")
public class FotoCounts implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idFotoCounts";

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
    @Column(name="id_foto_counts", unique=true, nullable=false, precision=11)
    private int idFotoCounts;
    @Column(name="name_file", length=128)
    private String nameFile;
    @Column(name="image_count")
    private Blob imageCount;
    @ManyToOne(optional=false)
    @JoinColumn(name="id_counts", nullable=false)
    private Counts counts;

    /** Default constructor. */
    public FotoCounts() {
        super();
    }

    /**
     * Access method for idFotoCounts.
     *
     * @return the current value of idFotoCounts
     */
    public int getIdFotoCounts() {
        return idFotoCounts;
    }

    /**
     * Setter method for idFotoCounts.
     *
     * @param aIdFotoCounts the new value for idFotoCounts
     */
    public void setIdFotoCounts(int aIdFotoCounts) {
        idFotoCounts = aIdFotoCounts;
    }

    /**
     * Access method for nameFile.
     *
     * @return the current value of nameFile
     */
    public String getNameFile() {
        return nameFile;
    }

    /**
     * Setter method for nameFile.
     *
     * @param aNameFile the new value for nameFile
     */
    public void setNameFile(String aNameFile) {
        nameFile = aNameFile;
    }

    /**
     * Access method for imageCount.
     *
     * @return the current value of imageCount
     */
    public Blob getImageCount() {
        return imageCount;
    }

    /**
     * Setter method for imageCount.
     *
     * @param aImageCount the new value for imageCount
     */
    public void setImageCount(Blob aImageCount) {
        imageCount = aImageCount;
    }

    /**
     * Access method for counts.
     *
     * @return the current value of counts
     */
    public Counts getCounts() {
        return counts;
    }

    /**
     * Setter method for counts.
     *
     * @param aCounts the new value for counts
     */
    public void setCounts(Counts aCounts) {
        counts = aCounts;
    }

    /**
     * Compares the key for this instance with another FotoCounts.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class FotoCounts and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof FotoCounts)) {
            return false;
        }
        FotoCounts that = (FotoCounts) other;
        if (this.getIdFotoCounts() != that.getIdFotoCounts()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another FotoCounts.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FotoCounts)) return false;
        return this.equalKeys(other) && ((FotoCounts)other).equalKeys(this);
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
        i = getIdFotoCounts();
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
        StringBuffer sb = new StringBuffer("[FotoCounts |");
        sb.append(" idFotoCounts=").append(getIdFotoCounts());
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
        ret.put("idFotoCounts", Integer.valueOf(getIdFotoCounts()));
        return ret;
    }

}
