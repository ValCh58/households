// Generated with g9.

package eis.company.households.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="link_object_uk")
public class LinkObjectUk implements Serializable {

   	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idLinkObject";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_link_object", unique=true, nullable=false, precision=10)
    private int idLinkObject;
    
    @Column(name="id_parent", nullable=false, precision=10)
    private int idParent;
    
    @Column(name="id_object", precision=10)
    private int idObject;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="id_type_object", nullable=false)
    @JsonIgnore //Удаление рекурсии!!!
    private TypeObject typeObject;

    /** Default constructor. */
    public LinkObjectUk() {
        super();
    }
    
    
    public TypeObject getTypeObject() {
		return typeObject;
	}


	public void setTypeObject(TypeObject typeObject) {
		this.typeObject = typeObject;
	}


	/**
     * Access method for idLinkObject.
     *
     * @return the current value of idLinkObject
     */
    public int getIdLinkObject() {
        return idLinkObject;
    }

    /**
     * Setter method for idLinkObject.
     *
     * @param aIdLinkObject the new value for idLinkObject
     */
    public void setIdLinkObject(int aIdLinkObject) {
        idLinkObject = aIdLinkObject;
    }

    /**
     * Access method for idParent.
     *
     * @return the current value of idParent
     */
    public int getIdParent() {
        return idParent;
    }

    /**
     * Setter method for idParent.
     *
     * @param aIdParent the new value for idParent
     */
    public void setIdParent(int aIdParent) {
        idParent = aIdParent;
    }

    /**
     * Access method for idObject.
     *
     * @return the current value of idObject
     */
    public int getIdObject() {
        return idObject;
    }

    /**
     * Setter method for idObject.
     *
     * @param aIdObject the new value for idObject
     */
    public void setIdObject(int aIdObject) {
        idObject = aIdObject;
    }

    /**
     * Compares the key for this instance with another LinkObjectUk.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class LinkObjectUk and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof LinkObjectUk)) {
            return false;
        }
        LinkObjectUk that = (LinkObjectUk) other;
        if (this.getIdLinkObject() != that.getIdLinkObject()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another LinkObjectUk.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LinkObjectUk)) return false;
        return this.equalKeys(other) && ((LinkObjectUk)other).equalKeys(this);
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
        i = getIdLinkObject();
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
        StringBuffer sb = new StringBuffer("[LinkObjectUk |");
        sb.append(" idLinkObject=").append(getIdLinkObject());
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
        ret.put("idLinkObject", Integer.valueOf(getIdLinkObject()));
        return ret;
    }

}
