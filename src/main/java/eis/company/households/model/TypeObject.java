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

@Entity(name="type_object")
public class TypeObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idTypeObject";

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
    @Column(name="id_type_object", unique=true, nullable=false, precision=11)
    private int idTypeObject;
    
    @Column(name="name_type", nullable=false, length=255)
    private String nameType;
    
    
    @OneToMany(mappedBy="typeObject")
    private List<ComServer> comServer = new ArrayList<ComServer>();
    
    @OneToMany(mappedBy="typeObject")
    private List<Counts> counts = new ArrayList<Counts>();
    
    @OneToMany(mappedBy="typeObject")
    private List<LinkObject> linkObject = new ArrayList<LinkObject>();
    
    @OneToMany(mappedBy="typeObject")
    private List<LinkObjectUk> linkObjectUk = new ArrayList<LinkObjectUk>();
    
    @OneToMany(mappedBy="typeObject")
    private List<ManagCompany> managCompany = new ArrayList<ManagCompany>();
    
    @OneToMany(mappedBy="typeObject")
    private List<UspdDev> uspdDev = new ArrayList<UspdDev>();
    
    //@OneToMany(mappedBy="typeObject")//////////Создать класс!!!!!!!
    //private List<UspdDevPrev> uspdDevPrev = new ArrayList<UspdDev>();
    
    @OneToMany(mappedBy="typeObject", orphanRemoval = true)
    @JsonIgnore
    private List<Street> street = new ArrayList<Street>();
    
    @OneToMany(mappedBy="typeObject")
    @JsonIgnore
    private List<House> house = new ArrayList<House>();
    
    @OneToMany(mappedBy="typeObject")
    @JsonIgnore
    private List<Room> room = new ArrayList<Room>();
    
    @OneToMany(mappedBy="typeObject")
    @JsonIgnore
    private List<PersonAcnt> personAcnt = new ArrayList<PersonAcnt>();
    
    /*** Helper methods ***/
    public void addCounts(Counts count) {
        this.counts.add(count);
        count.setTypeObject(this);
    }
    
    public void addStreet(Street street) { 
	    this.street.add(street);
	    street.setTypeObject(this); 
	  }

    
    public void addHouse(House house) {
        this.house.add(house);
        house.setTypeObject(this);
    }
    /********Del**************************/
    public void removeStreet(Street street) { 
	     street.setTypeObject(null);
	     this.street.remove(street); 
	}
    
    public void removeHouse(House house) { 
	     house.setTypeObject(null);
	     this.house.remove(house); 
	}
    
    public void removeRoom(Room room) { 
	     room.setTypeObject(null);
	     this.room.remove(room); 
	}
    
    public void removePersonAcnt(PersonAcnt personAcnt) { 
    	 personAcnt.setTypeObject(null);
	     this.personAcnt.remove(personAcnt); 
	}
    /*************************************/
    
    
    /** Default constructor. */
    public TypeObject() {
        super();
    }
    
    

    public List<PersonAcnt> getPersonAcnt() {
		return personAcnt;
	}

	public void setPersonAcnt(List<PersonAcnt> personAcnt) {
		this.personAcnt = personAcnt;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}

	public List<Street> getStreet() {
		return street;
	}

	public void setStreet(List<Street> street) {
		this.street = street;
	}

	public List<House> getHouse() {
		return house;
	}

	public void setHouse(List<House> house) {
		this.house = house;
	}

	/**
     * Access method for idTypeObject.
     *
     * @return the current value of idTypeObject
     */
    public int getIdTypeObject() {
        return idTypeObject;
    }

    /**
     * Setter method for idTypeObject.
     *
     * @param aIdTypeObject the new value for idTypeObject
     */
    public void setIdTypeObject(int aIdTypeObject) {
        idTypeObject = aIdTypeObject;
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
     * Access method for comServer.
     *
     * @return the current value of comServer
     */
    public List<ComServer> getComServer() {
        return comServer;
    }

    /**
     * Setter method for comServer.
     *
     * @param aComServer the new value for comServer
     */
    public void setComServer(List<ComServer> aComServer) {
        comServer = aComServer;
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
     * Access method for linkObject.
     *
     * @return the current value of linkObject
     */
    public List<LinkObject> getLinkObject() {
        return linkObject;
    }

    /**
     * Setter method for linkObject.
     *
     * @param aLinkObject the new value for linkObject
     */
    public void setLinkObject(List<LinkObject> aLinkObject) {
        linkObject = aLinkObject;
    }

    /**
     * Access method for managCompany.
     *
     * @return the current value of managCompany
     */
    public List<ManagCompany> getManagCompany() {
        return managCompany;
    }

    /**
     * Setter method for managCompany.
     *
     * @param aManagCompany the new value for managCompany
     */
    public void setManagCompany(List<ManagCompany> aManagCompany) {
        managCompany = aManagCompany;
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
     * Compares the key for this instance with another TypeObject.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TypeObject and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TypeObject)) {
            return false;
        }
        TypeObject that = (TypeObject) other;
        if (this.getIdTypeObject() != that.getIdTypeObject()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TypeObject.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TypeObject)) return false;
        return this.equalKeys(other) && ((TypeObject)other).equalKeys(this);
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
        i = getIdTypeObject();
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
        StringBuffer sb = new StringBuffer("[TypeObject |");
        sb.append(" idTypeObject=").append(getIdTypeObject());
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
        ret.put("idTypeObject", Integer.valueOf(getIdTypeObject()));
        return ret;
    }

}
