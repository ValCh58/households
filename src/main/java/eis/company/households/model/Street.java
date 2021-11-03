// Generated with g9.

package eis.company.households.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="street")
public class Street implements Serializable {

    private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idStreet";

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_street", unique=true, nullable=false)
    private int idStreet;
    
    @Column(name="name_street", nullable=false, length=145)
    private String nameStreet;
    
    @Column(length=145)
    private String district;
    
    @Column(name="id_link_object")
    private int idLinkObject;
    
    @OneToMany(mappedBy="street")
    @JsonIgnore
    private Set<House> house;
    
    @ManyToOne
    @JoinColumn(name="id_manag_company")
    private ManagCompany managCompany;
    
    @ManyToOne
    @JoinColumn(name="id_type_object")
    private TypeObject typeObject;
    
    /*** Helper methods **************************/
    public void addHouse(House house) {
        this.house.add(house);
        house.setStreet(this);
    }

	
	public void removeHouse(House house) { 
	     house.setStreet(null);
	     this.house.remove(house); 
	}
    /*******************************************/
    

    /** Default constructor. */
    public Street() {
        super();
    }
        
    public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}


	public int getIdLinkObject() {
		return idLinkObject;
	}


	public void setIdLinkObject(int idLinkObject) {
		this.idLinkObject = idLinkObject;
	}

	public ManagCompany getManagCompany() {
		return managCompany;
	}

	public void setManagCompany(ManagCompany managCompany) {
		this.managCompany = managCompany;
	}

	/**
     * Access method for idStreet.
     *
     * @return the current value of idStreet
     */
    public int getIdStreet() {
        return idStreet;
    }

    /**
     * Setter method for idStreet.
     *
     * @param aIdStreet the new value for idStreet
     */
    public void setIdStreet(int aIdStreet) {
        idStreet = aIdStreet;
    }

    /**
     * Access method for nameStreet.
     *
     * @return the current value of nameStreet
     */
    public String getNameStreet() {
        return nameStreet;
    }

    /**
     * Setter method for nameStreet.
     *
     * @param aNameStreet the new value for nameStreet
     */
    public void setNameStreet(String aNameStreet) {
        nameStreet = aNameStreet;
    }

    /**
     * Access method for district.
     *
     * @return the current value of district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Setter method for district.
     *
     * @param aDistrict the new value for district
     */
    public void setDistrict(String aDistrict) {
        district = aDistrict;
    }

    /**
     * Access method for house.
     *
     * @return the current value of house
     */
    public Set<House> getHouse() {
        return house;
    }

    /**
     * Setter method for house.
     *
     * @param aHouse the new value for house
     */
    public void setHouse(Set<House> aHouse) {
        house = aHouse;
    }

    /**
     * Compares the key for this instance with another Street.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Street and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Street)) {
            return false;
        }
        Street that = (Street) other;
        if (this.getIdStreet() != that.getIdStreet()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Street.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Street)) return false;
        return this.equalKeys(other) && ((Street)other).equalKeys(this);
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
        i = getIdStreet();
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
        StringBuffer sb = new StringBuffer("[Street |");
        sb.append(" idStreet=").append(getIdStreet());
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
        ret.put("idStreet", Integer.valueOf(getIdStreet()));
        return ret;
    }

}
