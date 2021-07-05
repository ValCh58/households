// Generated with g9.

package eis.company.households.modeleis;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="client")
public class Client implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "clId";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cl_id", unique=true, nullable=false, precision=10)
    private int clId;
    @Column(name="cl_login", nullable=false, length=50)
    private String clLogin;
    @Column(name="cl_pass", nullable=false, length=50)
    private String clPass;
    @Column(name="cl_fname", nullable=false, length=50)
    private String clFname;
    @Column(name="cl_name", nullable=false, length=50)
    private String clName;
    @Column(name="cl_sname", nullable=false, length=50)
    private String clSname;
    @Column(name="cl_email", nullable=false, length=100)
    private String clEmail;

    /** Default constructor. */
    public Client() {
        super();
    }

    /**
     * Access method for clId.
     *
     * @return the current value of clId
     */
    public int getClId() {
        return clId;
    }

    /**
     * Setter method for clId.
     *
     * @param aClId the new value for clId
     */
    public void setClId(int aClId) {
        clId = aClId;
    }

    /**
     * Access method for clLogin.
     *
     * @return the current value of clLogin
     */
    public String getClLogin() {
        return clLogin;
    }

    /**
     * Setter method for clLogin.
     *
     * @param aClLogin the new value for clLogin
     */
    public void setClLogin(String aClLogin) {
        clLogin = aClLogin;
    }

    /**
     * Access method for clPass.
     *
     * @return the current value of clPass
     */
    public String getClPass() {
        return clPass;
    }

    /**
     * Setter method for clPass.
     *
     * @param aClPass the new value for clPass
     */
    public void setClPass(String aClPass) {
        clPass = aClPass;
    }

    /**
     * Access method for clFname.
     *
     * @return the current value of clFname
     */
    public String getClFname() {
        return clFname;
    }

    /**
     * Setter method for clFname.
     *
     * @param aClFname the new value for clFname
     */
    public void setClFname(String aClFname) {
        clFname = aClFname;
    }

    /**
     * Access method for clName.
     *
     * @return the current value of clName
     */
    public String getClName() {
        return clName;
    }

    /**
     * Setter method for clName.
     *
     * @param aClName the new value for clName
     */
    public void setClName(String aClName) {
        clName = aClName;
    }

    /**
     * Access method for clSname.
     *
     * @return the current value of clSname
     */
    public String getClSname() {
        return clSname;
    }

    /**
     * Setter method for clSname.
     *
     * @param aClSname the new value for clSname
     */
    public void setClSname(String aClSname) {
        clSname = aClSname;
    }

    /**
     * Access method for clEmail.
     *
     * @return the current value of clEmail
     */
    public String getClEmail() {
        return clEmail;
    }

    /**
     * Setter method for clEmail.
     *
     * @param aClEmail the new value for clEmail
     */
    public void setClEmail(String aClEmail) {
        clEmail = aClEmail;
    }

    /**
     * Compares the key for this instance with another Client.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Client and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Client)) {
            return false;
        }
        Client that = (Client) other;
        if (this.getClId() != that.getClId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Client.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Client)) return false;
        return this.equalKeys(other) && ((Client)other).equalKeys(this);
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
        i = getClId();
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
        StringBuffer sb = new StringBuffer("[Client |");
        sb.append(" clId=").append(getClId());
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
        ret.put("clId", Integer.valueOf(getClId()));
        return ret;
    }

}
