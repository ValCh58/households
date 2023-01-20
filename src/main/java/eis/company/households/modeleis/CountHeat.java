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

@Entity(name="count_heat")
public class CountHeat implements Serializable {

    private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idCountHeat";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_count_heat", unique=true, nullable=false)
    private int idCountHeat;
    
    @Column(name="heat_meter_num", length=45)
    private String heatMeterNum;
    
    @Column(name="g_kalor", precision=13, scale=3)
    private BigDecimal gKalor;
    
    @Column(name="heat_time", length=10)
    private String heatTime;
    
    @Column(name="heat_date", length=12)
    private String heatDate;
    
    @Column(name="id_measuring")
    private int idMeasuring;
    
    @Column(name="factory_number_uspd", length=32)
    private String factoryNumberUspd;
    
    @Column(name="time_stamp")
    private LocalDateTime timeStamp;
    
    @Column(name="COUNT_TYPE", precision=4)
    private int countType;

    /** Default constructor. */
    public CountHeat() {
        super();
    }
    
    

    public int getCountType() {
		return countType;
	}



	public void setCountType(int countType) {
		this.countType = countType;
	}



	/**
     * Access method for idCountHeat.
     *
     * @return the current value of idCountHeat
     */
    public int getIdCountHeat() {
        return idCountHeat;
    }

    /**
     * Setter method for idCountHeat.
     *
     * @param aIdCountHeat the new value for idCountHeat
     */
    public void setIdCountHeat(int aIdCountHeat) {
        idCountHeat = aIdCountHeat;
    }

    /**
     * Access method for heatMeterNum.
     *
     * @return the current value of heatMeterNum
     */
    public String getHeatMeterNum() {
        return heatMeterNum;
    }

    /**
     * Setter method for heatMeterNum.
     *
     * @param aHeatMeterNum the new value for heatMeterNum
     */
    public void setHeatMeterNum(String aHeatMeterNum) {
        heatMeterNum = aHeatMeterNum;
    }

    /**
     * Access method for gKalor.
     *
     * @return the current value of gKalor
     */
    public BigDecimal getGKalor() {
        return gKalor;
    }

    /**
     * Setter method for gKalor.
     *
     * @param aGKalor the new value for gKalor
     */
    public void setGKalor(BigDecimal aGKalor) {
        gKalor = aGKalor;
    }

    /**
     * Access method for heatTime.
     *
     * @return the current value of heatTime
     */
    public String getHeatTime() {
        return heatTime;
    }

    /**
     * Setter method for heatTime.
     *
     * @param aHeatTime the new value for heatTime
     */
    public void setHeatTime(String aHeatTime) {
        heatTime = aHeatTime;
    }

    /**
     * Access method for heatDate.
     *
     * @return the current value of heatDate
     */
    public String getHeatDate() {
        return heatDate;
    }

    /**
     * Setter method for heatDate.
     *
     * @param aHeatDate the new value for heatDate
     */
    public void setHeatDate(String aHeatDate) {
        heatDate = aHeatDate;
    }

    /**
     * Access method for idMeasuring.
     *
     * @return the current value of idMeasuring
     */
    public int getIdMeasuring() {
        return idMeasuring;
    }

    /**
     * Setter method for idMeasuring.
     *
     * @param aIdMeasuring the new value for idMeasuring
     */
    public void setIdMeasuring(int aIdMeasuring) {
        idMeasuring = aIdMeasuring;
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
     * Compares the key for this instance with another CountHeat.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class CountHeat and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof CountHeat)) {
            return false;
        }
        CountHeat that = (CountHeat) other;
        if (this.getIdCountHeat() != that.getIdCountHeat()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another CountHeat.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CountHeat)) return false;
        return this.equalKeys(other) && ((CountHeat)other).equalKeys(this);
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
        i = getIdCountHeat();
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
        StringBuffer sb = new StringBuffer("[CountHeat |");
        sb.append(" idCountHeat=").append(getIdCountHeat());
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
        ret.put("idCountHeat", Integer.valueOf(getIdCountHeat()));
        return ret;
    }

}
