// Generated with g9.

package eis.company.households.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name="uspd_dev")
public class UspdDev implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idUspdDev";

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
    @Column(name="id_uspd_dev", unique=true, nullable=false, precision=11)
    private int idUspdDev;
    
    @Column(name="name_uspd_dev", nullable=false, length=128)
    private String nameUspdDev;
    
    @Column(name="num_uspd_dev", length=28)
    private String numUspdDev;
    
    @Column(name="address_loc", length=255)
    private String addressLoc;
    
    @Column(name="id_counts", precision=11)
    private int idCounts;
    
    @Column(name="id_config_uspd", precision=11)
    private int idConfigUspd;
    
    
    /*** Helper methods **************************/
    public void addCounts(Counts count) {
        this.counts.add(count);
        count.setUspdDev(this);
    }

    public void removeCounts(Counts count) {
        count.setUspdDev(null);
        this.counts.remove(count);
    }
        
    /********* USPD_DEV --------< COUNTS *****************/
    @OneToMany(mappedBy="uspdDev", orphanRemoval = true)
    //@JsonIgnore //Удаление рекурсии!!!
    private List<Counts> counts = new ArrayList<>();
    
    /********* USPD_DEV --------< Room *****************/
    @OneToMany(mappedBy="uspdDev", orphanRemoval = true)
    //@JsonIgnore //Удаление рекурсии!!!
    private List<Room> room = new ArrayList<>();
    
    /********** USPD_DEV >------ COM_SERVER **************/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_com_server")
    private ComServer comServer;
    
    /*** USPD_DEV >-------- TYPE_OBJECT ****************/
    @ManyToOne
    @JoinColumn(name="id_type_object")
    private TypeObject typeObject;
    
    /*** USPD_DEV >-------TYPE_USPD_DEV***************/
    @ManyToOne(optional=false)
    @JoinColumn(name="id_type_uspd_dev", nullable=false)
    private TypeUspd typeUspd;

    /** Default constructor. */
    public UspdDev() {
        super();
    }
    
    /**Constructor with parameters**/
	public UspdDev(int idUspdDev, String nameUspdDev, String numUspdDev, String addressLoc, int idCounts, int idConfigUspd) {
		super();
		this.idUspdDev = idUspdDev;
		this.nameUspdDev = nameUspdDev;
		this.numUspdDev = numUspdDev;
		this.addressLoc = addressLoc;
		this.idCounts = idCounts;
		this.idConfigUspd = idConfigUspd;
	}
	
	/******Helper functions****************/
	public void removeRoom(Room room) { 
	     room.setUspdDev(null);
	     this.room.remove(room); 
	}
	/**************************************/
    
    public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}

	public String getNumUspdDev() {
		return numUspdDev;
	}
    
	public void setNumUspdDev(String numUspdDev) {
		this.numUspdDev = numUspdDev;
	}

	/**
     * Access method for idUspdDev.
     *
     * @return the current value of idUspdDev
     */
    public int getIdUspdDev() {
        return idUspdDev;
    }

    /**
     * Setter method for idUspdDev.
     *
     * @param aIdUspdDev the new value for idUspdDev
     */
    public void setIdUspdDev(int aIdUspdDev) {
        idUspdDev = aIdUspdDev;
    }

    /**
     * Access method for nameUspdDev.
     *
     * @return the current value of nameUspdDev
     */
    public String getNameUspdDev() {
        return nameUspdDev;
    }

    /**
     * Setter method for nameUspdDev.
     *
     * @param aNameUspdDev the new value for nameUspdDev
     */
    public void setNameUspdDev(String aNameUspdDev) {
        nameUspdDev = aNameUspdDev;
    }

    /**
     * Access method for addressLoc.
     *
     * @return the current value of addressLoc
     */
    public String getAddressLoc() {
        return addressLoc;
    }

    /**
     * Setter method for addressLoc.
     *
     * @param aAddressLoc the new value for addressLoc
     */
    public void setAddressLoc(String aAddressLoc) {
        addressLoc = aAddressLoc;
    }

    /**
     * Access method for idCounts.
     *
     * @return the current value of idCounts
     */
    public int getIdCounts() {
        return idCounts;
    }

    /**
     * Setter method for idCounts.
     *
     * @param aIdCounts the new value for idCounts
     */
    public void setIdCounts(int aIdCounts) {
        idCounts = aIdCounts;
    }

    /**
     * Access method for idConfigUspd.
     *
     * @return the current value of idConfigUspd
     */
    public int getIdConfigUspd() {
        return idConfigUspd;
    }

    /**
     * Setter method for idConfigUspd.
     *
     * @param aIdConfigUspd the new value for idConfigUspd
     */
    public void setIdConfigUspd(int aIdConfigUspd) {
        idConfigUspd = aIdConfigUspd;
    }

    /**
     * Access method for counts.
     *
     * @return the current value of counts
     */
    public List<Counts> getCounts() {
        return counts;
    }

    /**
     * Setter method for counts.
     *
     * @param aCounts the new value for counts
     */
    public void setCounts(List<Counts> aCounts) {
        counts = aCounts;
    }

    /**
     * Access method for comServer.
     *
     * @return the current value of comServer
     */
    public ComServer getComServer() {
        return comServer;
    }

    /**
     * Setter method for comServer.
     *
     * @param aComServer the new value for comServer
     */
    public void setComServer(ComServer aComServer) {
        comServer = aComServer;
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
     * Access method for typeUspd.
     *
     * @return the current value of typeUspd
     */
    public TypeUspd getTypeUspd() {
        return typeUspd;
    }

    /**
     * Setter method for typeUspd.
     *
     * @param aTypeUspd the new value for typeUspd
     */
    public void setTypeUspd(TypeUspd aTypeUspd) {
        typeUspd = aTypeUspd;
    }

    /**
     * Compares the key for this instance with another UspdDev.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class UspdDev and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof UspdDev)) {
            return false;
        }
        UspdDev that = (UspdDev) other;
        if (this.getIdUspdDev() != that.getIdUspdDev()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another UspdDev.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UspdDev)) return false;
        return this.equalKeys(other) && ((UspdDev)other).equalKeys(this);
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
        i = getIdUspdDev();
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
        StringBuffer sb = new StringBuffer("[UspdDev |");
        sb.append(" idUspdDev=").append(getIdUspdDev());
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
        ret.put("idUspdDev", Integer.valueOf(getIdUspdDev()));
        return ret;
    }

}
