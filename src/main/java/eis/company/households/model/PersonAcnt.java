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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="person_acnt")
public class PersonAcnt implements Serializable {

	/** Для записи ID счетчиков в форме $("#modalFormEditAct #person_counts").val(list)	 */
	private transient List<String> personcounts;
    
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idPersonAcnt";

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_person_acnt", unique=true, nullable=false)
    private int idPersonAcnt;
    
    @Column(name="num_acnt", nullable=false, length=145)
    private String numAcnt;
    
    @Column(name="id_link_object")
    private int idLinkObject;
    
    @OneToMany(mappedBy="personAcnt")
    private List<Counts> counts = new ArrayList<>();
    
    @ManyToOne(optional=false)
    @JoinColumn(name="id_room", nullable=false)
    @JsonIgnore
    private Room room;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="id_type_object", nullable=false)
    @JsonIgnore
    private TypeObject typeObject;

    /** Default constructor. */
    public PersonAcnt() {
        super();
    }
    
    /************Helper functions**************/
    public void addCounts(Counts count) {
    	this.counts.add(count);
    	count.setPersonAcnt(this);
    }
    
    public void removeCounts(Counts count) { 
    	count.setPersonAcnt(null);
   	    this.counts.remove(count); 
   	}
    /******************************************/
        
    public int getIdLinkObject() {
		return idLinkObject;
	}

	public void setIdLinkObject(int idLinkObject) {
		this.idLinkObject = idLinkObject;
	}

	public TypeObject getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}

	/**
     * Access method for idPersonAcnt.
     *
     * @return the current value of idPersonAcnt
     */
    public int getIdPersonAcnt() {
        return idPersonAcnt;
    }

    /**
     * Setter method for idPersonAcnt.
     *
     * @param aIdPersonAcnt the new value for idPersonAcnt
     */
    public void setIdPersonAcnt(int aIdPersonAcnt) {
        idPersonAcnt = aIdPersonAcnt;
    }

    /**
     * Access method for numAcnt.
     *
     * @return the current value of numAcnt
     */
    public String getNumAcnt() {
        return numAcnt;
    }

    /**
     * Setter method for numAcnt.
     *
     * @param aNumAcnt the new value for numAcnt
     */
    public void setNumAcnt(String aNumAcnt) {
        numAcnt = aNumAcnt;
    }
    
    

    public List<Counts> getCounts() {
		return counts;
	}

	public void setCounts(List<Counts> counts) {
		this.counts = counts;
	}

	/**
     * Access method for room.
     *
     * @return the current value of room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Setter method for room.
     *
     * @param aRoom the new value for room
     */
    public void setRoom(Room aRoom) {
        room = aRoom;
    }

    /**
     * Compares the key for this instance with another PersonAcnt.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class PersonAcnt and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof PersonAcnt)) {
            return false;
        }
        PersonAcnt that = (PersonAcnt) other;
        if (this.getIdPersonAcnt() != that.getIdPersonAcnt()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another PersonAcnt.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PersonAcnt)) return false;
        return this.equalKeys(other) && ((PersonAcnt)other).equalKeys(this);
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
        i = getIdPersonAcnt();
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
        StringBuffer sb = new StringBuffer("[PersonAcnt |");
        sb.append(" idPersonAcnt=").append(getIdPersonAcnt());
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
        ret.put("idPersonAcnt", Integer.valueOf(getIdPersonAcnt()));
        return ret;
    }

	public List<String> getPersoncounts() {
		return personcounts;
	}

	public void setPersoncounts(List<String> personcounts) {
		this.personcounts = personcounts;
	}

		

	

}
