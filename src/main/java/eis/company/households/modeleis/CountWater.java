// Generated with g9.

package eis.company.households.modeleis;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity(name="count_water")

public class CountWater implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idCountWater";

        
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_count_water", unique=true, nullable=false, precision=11)
    private int idCountWater;
    
    @Column(name="FACTORY_NUMBER_USPD", length=32)
    private String factoryNumberUspd;
    
    @Column(name="ID_MEASURING", insertable=false, updatable=false)
    private int id_Measuring;
    
    @Column(name="count_w", precision=13, scale=3)
    private BigDecimal countW;
    
    @Column(name="TIME_STAMP")
    private LocalDateTime timeStamp;
    
    @Column(name="type_count", precision=4)
    private short typeCount;
    
    @Column(name="num_ch", precision=3)
    private int numCh;
    
    /***************************************************************/
    
   
    @ManyToOne(optional=false)
    @JoinColumn(name="ID_MEASURING", nullable=false)
    private Measuring measuring;
     
    
    /** Default constructor. **************************************/
    public CountWater() {
        super();
    }

    /**
     * Access method for idCountWater.
     *
     * @return the current value of idCountWater
     */
    public int getIdCountWater() {
        return idCountWater;
    }

    /**
     * Setter method for idCountWater.
     *
     * @param aIdCountWater the new value for idCountWater
     */
    public void setIdCountWater(int aIdCountWater) {
        idCountWater = aIdCountWater;
    }
    

    public int getId_Measuring() {
		return id_Measuring;
	}

	public void setId_Measuring(int id_Measuring) {
		this.id_Measuring = id_Measuring;
	}

	/**
     * Access method for factoryNumberUspd.
     *
     * @return the current value of factoryNumberUspd
     */
    public String getFactoryNumberUspd() {
        return factoryNumberUspd;
    }

    /**
     * Setter method for factoryNumberUspd.
     *
     * @param aFactoryNumberUspd the new value for factoryNumberUspd
     */
    public void setFactoryNumberUspd(String aFactoryNumberUspd) {
        factoryNumberUspd = aFactoryNumberUspd;
    }

    /**
     * Access method for countW.
     *
     * @return the current value of countW
     */
    public BigDecimal getCountW() {
        return countW;
    }

    /**
     * Setter method for countW.
     *
     * @param aCountW the new value for countW
     */
    public void setCountW(BigDecimal aCountW) {
        countW = aCountW;
    }

    /**
     * Access method for timeStamp.
     *
     * @return the current value of timeStamp
     */
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    /**
     * Setter method for timeStamp.
     *
     * @param aTimeStamp the new value for timeStamp
     */
    public void setTimeStamp(LocalDateTime aTimeStamp) {
        timeStamp = aTimeStamp;
    }

    /**
     * Access method for typeCount.
     *
     * @return the current value of typeCount
     */
    public short getTypeCount() {
        return typeCount;
    }

    /**
     * Setter method for typeCount.
     *
     * @param aTypeCount the new value for typeCount
     */
    public void setTypeCount(short aTypeCount) {
        typeCount = aTypeCount;
    }

    /**
     * Access method for numCh.
     *
     * @return the current value of numCh
     */
    public int getNumCh() {
        return numCh;
    }

    /**
     * Setter method for numCh.
     *
     * @param aNumCh the new value for numCh
     */
    public void setNumCh(int aNumCh) {
        numCh = aNumCh;
    }

    /**
     * Access method for measuring.
     *
     * @return the current value of measuring
    
    public Measuring getMeasuring() {
        return measuring;
    }
    */

    /**
     * Setter method for measuring.
     *
     * @param aMeasuring the new value for measuring
     
    public void setMeasuring(Measuring aMeasuring) {
        measuring = aMeasuring;
    }*/
  
    /**
     * Compares the key for this instance with another CountWater.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class CountWater and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof CountWater)) {
            return false;
        }
        CountWater that = (CountWater) other;
        if (this.getIdCountWater() != that.getIdCountWater()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another CountWater.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CountWater)) return false;
        return this.equalKeys(other) && ((CountWater)other).equalKeys(this);
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
        i = getIdCountWater();
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
        StringBuffer sb = new StringBuffer("[CountWater |");
        sb.append(" idCountWater=").append(getIdCountWater());
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
        ret.put("idCountWater", Integer.valueOf(getIdCountWater()));
        return ret;
    }

}
