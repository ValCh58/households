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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="house", indexes={@Index(name="house_address_IX", columnList="address", unique=true)})
public class House implements Serializable {

    
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idHouse";

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_house", unique=true, nullable=false)
    private int idHouse;
    
    @Column(name="name_house", length=145)
    private String nameHouse;
    
    @Column(unique=true, nullable=false, length=145)
    private String address;
    
    @Column(name="id_link_object")
    private int idLinkObject;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="id_street", nullable=false)
    private Street street;
    
    @OneToMany(mappedBy="house")
    private Set<Room> room;
    
    @ManyToOne
    @JoinColumn(name="id_type_object")
    private TypeObject typeObject;

    /** Default constructor. */
    public House() {
        super();
    }


    /****Helper functions************************/
    public void removeRoom(Room room) { 
	     room.setHouse(null);
	     this.room.remove(room); 
	}
    /********************************************/
    
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

	
	/**
     * Access method for idHouse.
     *
     * @return the current value of idHouse
     */
    public int getIdHouse() {
        return idHouse;
    }

    /**
     * Setter method for idHouse.
     *
     * @param aIdHouse the new value for idHouse
     */
    public void setIdHouse(int aIdHouse) {
        idHouse = aIdHouse;
    }

    /**
     * Access method for nameHouse.
     *
     * @return the current value of nameHouse
     */
    public String getNameHouse() {
        return nameHouse;
    }

    /**
     * Setter method for nameHouse.
     *
     * @param aNameHouse the new value for nameHouse
     */
    public void setNameHouse(String aNameHouse) {
        nameHouse = aNameHouse;
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
        address = aAddress;
    }

    /**
     * Access method for street.
     *
     * @return the current value of street
     */
    public Street getStreet() {
        return street;
    }

    /**
     * Setter method for street.
     *
     * @param aStreet the new value for street
     */
    public void setStreet(Street aStreet) {
        street = aStreet;
    }

    /**
     * Access method for room.
     *
     * @return the current value of room
     */
    public Set<Room> getRoom() {
        return room;
    }

    /**
     * Setter method for room.
     *
     * @param aRoom the new value for room
     */
    public void setRoom(Set<Room> aRoom) {
        room = aRoom;
    }

    /**
     * Compares the key for this instance with another House.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class House and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof House)) {
            return false;
        }
        House that = (House) other;
        if (this.getIdHouse() != that.getIdHouse()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another House.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof House)) return false;
        return this.equalKeys(other) && ((House)other).equalKeys(this);
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
        i = getIdHouse();
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
        StringBuffer sb = new StringBuffer("[House |");
        sb.append(" idHouse=").append(getIdHouse());
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
        ret.put("idHouse", Integer.valueOf(getIdHouse()));
        return ret;
    }

}
