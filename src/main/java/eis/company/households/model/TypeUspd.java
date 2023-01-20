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
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="type_uspd")
public class TypeUspd implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idTypeUspd";

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
    @Column(name="id_type_uspd", unique=true, nullable=false, precision=11)
    private int idTypeUspd;
    @Column(name="name_type", nullable=false, length=128)
    private String nameType;
    
    /*** USPD_DEV >------ TYPE_USPD ***/
    @OneToMany(mappedBy="typeUspd", orphanRemoval = true)
    @JsonIgnore //Удаление рекурсии!!!
    private List<UspdDev> uspdDev = new ArrayList<UspdDev>();
    
    /*** Helper methods *************************/
    public void addUspdDev(UspdDev uspddev) {
        this.uspdDev.add(uspddev);
        uspddev.setTypeUspd(this);
    }

    public void removeUspdDev(UspdDev uspddev) {
        uspddev.setTypeUspd(null);
        this.uspdDev.remove(uspddev);
    }
    /*******************************************/
    /** Default constructor. *******************/
    public TypeUspd() {
        super();
    }

    /**
     * Access method for idTypeUspd.
     *
     * @return the current value of idTypeUspd
     */
    public int getIdTypeUspd() {
        return idTypeUspd;
    }

    /**
     * Setter method for idTypeUspd.
     *
     * @param aIdTypeUspd the new value for idTypeUspd
     */
    public void setIdTypeUspd(int aIdTypeUspd) {
        idTypeUspd = aIdTypeUspd;
    }

   
    /**
     * Access method for nameType.
     *
     * @return the current value of nameType
     */
    public String getNameType() {
        return nameType;
    }

    /**
     * Setter method for nameType.
     *
     * @param aNameType the new value for nameType
     */
    public void setNameType(String aNameType) {
        nameType = aNameType;
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
     * Compares the key for this instance with another TypeUspd.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TypeUspd and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TypeUspd)) {
            return false;
        }
        TypeUspd that = (TypeUspd) other;
        if (this.getIdTypeUspd() != that.getIdTypeUspd()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TypeUspd.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TypeUspd)) return false;
        return this.equalKeys(other) && ((TypeUspd)other).equalKeys(this);
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
        i = getIdTypeUspd();
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
        StringBuffer sb = new StringBuffer("[TypeUspd |");
        sb.append(" idTypeUspd=").append(getIdTypeUspd());
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
        ret.put("idTypeUspd", Integer.valueOf(getIdTypeUspd()));
        return ret;
    }

}
