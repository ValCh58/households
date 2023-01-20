// Generated with g9.

package eis.company.households.model;

import java.io.Serializable;
import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="counts")
public class Counts implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idCounts";

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
    @Column(name="id_counts", unique=true, nullable=false, precision=11)
    private int idCounts;
    
    //1-СХВ; 2-СГВ; 3-СЭЭ; 4-СТЭ///////////
    @Column(name="type_count", precision=2)
    private int typeCount;
    //////////////////////////////////////
    
    @Column(name="serial_num", nullable=false, length=128)
    private String serialNum;
    
    @Column(name="date_plug")
    private LocalDate datePlug;
    
    @Column(name="date_expire")
    private LocalDate dateExpire;
    
    @Column(name="name_count", nullable=false, length=128)
    private String nameCount;
    
    @Column(nullable=false, length=255)
    private String address;
    //Номер канала
    @Column(name="num_ch")
    private int numCh;
    //Делитель импулсов
    @Column(name="num_rat")
    private int numRat;
    
    /*** Counts >-------> Person_acnt ***/
    @ManyToOne
    @JoinColumn(name="id_person_acnt")
    @JsonIgnore
    private PersonAcnt personAcnt;
   
    /*** Counts >--------- TypeObject ***/
    @ManyToOne
    @JoinColumn(name="id_type_object")
    @JsonIgnore
    private TypeObject typeObject;
    
    /*** Counts >--------- UspdDev ***/
    @ManyToOne(optional=false)
    @JoinColumn(name="id_uspd_dev", nullable=false)
    @JsonIgnore
    private UspdDev uspdDev;
    
    /*** Counts ---------< FotoCount ***/
    @OneToMany(mappedBy="counts")
    private List<FotoCounts> fotoCounts = new ArrayList<>();

    /** Default constructor. */
    public Counts() {
        super();
    }

    /**
     * Access method for numCh.
     *
     * @return the current value of numCh
     */
    
    
    
    public int getNumCh() {
		return numCh;
	}

	public int getNumRat() {
		return numRat;
	}

	public void setNumRat(int numRat) {
		this.numRat = numRat;
	}

	public void setNumCh(int numCh) {
		this.numCh = numCh;
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
    	this.idCounts = aIdCounts;
    }

    /**
     * Access method for elEnergy.
     *
     * @return the current value of elEnergy
     */
    public int getTypeCount() {
        return typeCount;
    }

    /**
     * Setter method for elEnergy.
     *
     * @param aElEnergy the new value for elEnergy
     */
    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    /**
     * Access method for serialNum.
     *
     * @return the current value of serialNum
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * Setter method for serialNum.
     *
     * @param aSerialNum the new value for serialNum
     */
    public void setSerialNum(String aSerialNum) {
    	this.serialNum = aSerialNum;
    }

    /**
     * Access method for datePlug.
     *
     * @return the current value of datePlug
     */
    public LocalDate getDatePlug() {
        return datePlug;
    }

    /**
     * Setter method for datePlug.
     *
     * @param aDatePlug the new value for datePlug
     */
    public void setDatePlug(LocalDate aDatePlug) {
    	this.datePlug = aDatePlug;
    }

    /**
     * Access method for dateExpire.
     *
     * @return the current value of dateExpire
     */
    public LocalDate getDateExpire() {
        return dateExpire;
    }

    /**
     * Setter method for dateExpire.
     *
     * @param aDateExpire the new value for dateExpire
     */
    public void setDateExpire(LocalDate aDateExpire) {
    	this.dateExpire = aDateExpire;
    }

    /**
     * Access method for nameCount.
     *
     * @return the current value of nameCount
     */
    public String getNameCount() {
        return nameCount;
    }

    /**
     * Setter method for nameCount.
     *
     * @param aNameCount the new value for nameCount
     */
    public void setNameCount(String aNameCount) {
    	this.nameCount = aNameCount;
    }

    /**
     * Access method for address.
     *
     * @return the current value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for address.
     *
     * @param aAddress the new value for address
     */
    public void setAddress(String aAddress) {
    	this.address = aAddress;
    }
    
    

    public PersonAcnt getPersonAcnt() {
		return personAcnt;
	}

	public void setPersonAcnt(PersonAcnt personAcnt) {
		this.personAcnt = personAcnt;
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
    	this.typeObject = aTypeObject;
    }

    /**
     * Access method for uspdDev.
     *
     * @return the current value of uspdDev
     */
    public UspdDev getUspdDev() {
        return uspdDev;
    }

    /**
     * Setter method for uspdDev.
     *
     * @param aUspdDev the new value for uspdDev
     */
    public void setUspdDev(UspdDev aUspdDev) {
    	this.uspdDev = aUspdDev;
    }

    /**
     * Access method for fotoCounts.
     *
     * @return the current value of fotoCounts
     */
    public List<FotoCounts> getFotoCounts() {
        return fotoCounts;
    }

    /**
     * Setter method for fotoCounts.
     *
     * @param aFotoCounts the new value for fotoCounts
     */
    public void setFotoCounts(List<FotoCounts> aFotoCounts) {
    	this.fotoCounts = aFotoCounts;
    }

    /**
     * Compares the key for this instance with another Counts.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Counts and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Counts)) {
            return false;
        }
        Counts that = (Counts) other;
        if (this.getIdCounts() != that.getIdCounts()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Counts.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Counts)) return false;
        return this.equalKeys(other) && ((Counts)other).equalKeys(this);
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
        i = getIdCounts();
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
        StringBuffer sb = new StringBuffer("[Counts |");
        sb.append(" idCounts=").append(getIdCounts());
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
        ret.put("idCounts", Integer.valueOf(getIdCounts()));
        return ret;
    }

}
