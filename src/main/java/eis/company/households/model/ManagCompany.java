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
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="manag_company")
public class ManagCompany implements Serializable {

    private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idManagCompany";

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
    @Column(name="id_manag_company", unique=true, nullable=false, precision=11)
    private int idManagCompany;
    
    @Column(name="name_company", nullable=false, length=255)
    private String nameCompany;
    
    @Column(length=45)
    private String phone;
    
    @Column(name="address_1", length=255)
    private String address1;
    
    @Column(name="address_2", length=255)
    private String address2;
    
    @OneToMany(mappedBy="managCompany")
    @JsonIgnore //Удаление рекурсии!!!
    private Set<ComServer> comServer;
    
    @OneToMany(mappedBy="managCompany")
    @JsonIgnore
    private Set<Street> street;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="id_type_object", nullable=false)
    @JsonIgnore
    private TypeObject typeObject;

    /** Default constructor. */
    public ManagCompany() {
        super();
    }

    /** Helper methods **************************/
	/*
	 * public void addStreet(Street street) { 
	 *    this.street.add(street);
	 *    street.setManagCompany(this); 
	 * }
	 */

	
	  public void removeStreet(Street street) { 
	     street.setManagCompany(null);
	     this.street.remove(street); 
	  }
	 
    /*******************************************/
    
    
    public Set<Street> getStreet() {
		return street;
	}

	public void setStreet(Set<Street> street) {
		this.street = street;
	}

	/**
     * Access method for idManagCompany.
     *
     * @return the current value of idManagCompany
     */
    public int getIdManagCompany() {
        return idManagCompany;
    }

    /**
     * Setter method for idManagCompany.
     *
     * @param aIdManagCompany the new value for idManagCompany
     */
    public void setIdManagCompany(int aIdManagCompany) {
        idManagCompany = aIdManagCompany;
    }

    /**
     * Access method for nameCompany.
     *
     * @return the current value of nameCompany
     */
    public String getNameCompany() {
        return nameCompany;
    }

    /**
     * Setter method for nameCompany.
     *
     * @param aNameCompany the new value for nameCompany
     */
    public void setNameCompany(String aNameCompany) {
        nameCompany = aNameCompany;
    }

    /**
     * Access method for phone.
     *
     * @return the current value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter method for phone.
     *
     * @param aPhone the new value for phone
     */
    public void setPhone(String aPhone) {
        phone = aPhone;
    }

    /**
     * Access method for address1.
     *
     * @return the current value of address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Setter method for address1.
     *
     * @param aAddress1 the new value for address1
     */
    public void setAddress1(String aAddress1) {
        address1 = aAddress1;
    }

    /**
     * Access method for address2.
     *
     * @return the current value of address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Setter method for address2.
     *
     * @param aAddress2 the new value for address2
     */
    public void setAddress2(String aAddress2) {
        address2 = aAddress2;
    }

    /**
     * Access method for comServer.
     *
     * @return the current value of comServer
     */
    public Set<ComServer> getComServer() {
        return comServer;
    }

    /**
     * Setter method for comServer.
     *
     * @param aComServer the new value for comServer
     */
    public void setComServer(Set<ComServer> aComServer) {
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
     * Compares the key for this instance with another ManagCompany.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class ManagCompany and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof ManagCompany)) {
            return false;
        }
        ManagCompany that = (ManagCompany) other;
        if (this.getIdManagCompany() != that.getIdManagCompany()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another ManagCompany.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ManagCompany)) return false;
        return this.equalKeys(other) && ((ManagCompany)other).equalKeys(this);
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
        i = getIdManagCompany();
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
        StringBuffer sb = new StringBuffer("[ManagCompany |");
        sb.append(" idManagCompany=").append(getIdManagCompany());
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
        ret.put("idManagCompany", Integer.valueOf(getIdManagCompany()));
        return ret;
    }

}
