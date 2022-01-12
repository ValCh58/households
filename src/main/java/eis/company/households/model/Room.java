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

@Entity(name="room")
public class Room implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idRoom";
    
    private transient int id_uspd;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_room", unique=true, nullable=false)
    private int idRoom;
    
    @Column(name="number_room", nullable=false, length=45)
    private String numberRoom;
    
    @Column(name="name_room", length=145)
    private String nameRoom;
    
    @Column(name="number_uspd", length=45)
    //@NotEmpty(message = "*Пожалуйста, выберите УСПД")
    private String numberUspd;
    
    @Column(name="id_link_object")
    private int idLinkObject;
    
    @OneToMany(mappedBy="room")
    @JsonIgnore
    private Set<PersonAcnt> personAcnt;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="id_house", nullable=false)
    @JsonIgnore
    private House house;
    
    @ManyToOne
    @JoinColumn(name="id_type_object")
    @JsonIgnore
    private TypeObject typeObject;
    
    @ManyToOne
    @JoinColumn(name="id_uspd_dev")
    @JsonIgnore
    private UspdDev uspdDev;

    /** Default constructor. */
    public Room() {
        super();
    }
    
    /************Helper functions**************/
    public void removePersonAcnt(PersonAcnt personAcnt) {                       
    	personAcnt.setRoom(null);
	     this.personAcnt.remove(personAcnt); 
	}
    /******************************************/
        
    public UspdDev getUspdDev() {
		return uspdDev;
	}

	public String getNumberUspd() {
		return numberUspd;
	}

	public void setNumberUspd(String numberUspd) {
		this.numberUspd = numberUspd;
	}

	public void setUspdDev(UspdDev uspdDev) {
		this.uspdDev = uspdDev;
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
    

    /**
     * Access method for idRoom.
     *
     * @return the current value of idRoom
     */
    public int getIdRoom() {
        return idRoom;
    }

    /**
     * Setter method for idRoom.
     *
     * @param aIdRoom the new value for idRoom
     */
    public void setIdRoom(int aIdRoom) {
        idRoom = aIdRoom;
    }

    /**
     * Access method for numberRoom.
     *
     * @return the current value of numberRoom
     */
    public String getNumberRoom() {
        return numberRoom;
    }

    /**
     * Setter method for numberRoom.
     *
     * @param aNumberRoom the new value for numberRoom
     */
    public void setNumberRoom(String aNumberRoom) {
        numberRoom = aNumberRoom;
    }

    /**
     * Access method for nameRoom.
     *
     * @return the current value of nameRoom
     */
    public String getNameRoom() {
        return nameRoom;
    }

    /**
     * Setter method for nameRoom.
     *
     * @param aNameRoom the new value for nameRoom
     */
    public void setNameRoom(String aNameRoom) {
        nameRoom = aNameRoom;
    }

    /**
     * Access method for personAcnt.
     *
     * @return the current value of personAcnt
     */
    public Set<PersonAcnt> getPersonAcnt() {
        return personAcnt;
    }

    /**
     * Setter method for personAcnt.
     *
     * @param aPersonAcnt the new value for personAcnt
     */
    public void setPersonAcnt(Set<PersonAcnt> aPersonAcnt) {
        personAcnt = aPersonAcnt;
    }

    /**
     * Access method for house.
     *
     * @return the current value of house
     */
    public House getHouse() {
        return house;
    }

    /**
     * Setter method for house.
     *
     * @param aHouse the new value for house
     */
    public void setHouse(House aHouse) {
        house = aHouse;
    }

    /**
     * Compares the key for this instance with another Room.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Room and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Room)) {
            return false;
        }
        Room that = (Room) other;
        if (this.getIdRoom() != that.getIdRoom()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Room.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Room)) return false;
        return this.equalKeys(other) && ((Room)other).equalKeys(this);
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
        i = getIdRoom();
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
        StringBuffer sb = new StringBuffer("[Room |");
        sb.append(" idRoom=").append(getIdRoom());
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
        ret.put("idRoom", Integer.valueOf(getIdRoom()));
        return ret;
    }

	public int getId_uspd() {
		return id_uspd;
	}

	public void setId_uspd(int id_uspd) {
		this.id_uspd = id_uspd;
	}

}
