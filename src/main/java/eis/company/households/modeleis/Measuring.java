// Generated with g9.

package eis.company.households.modeleis;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity(name="measuring")
public class Measuring implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idMeasuring";

    
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_MEASURING", unique=true, nullable=false, length=10)
    private int idMeasuring;
    
    @Column(name="PROG_VERSION_EN", length=32)
    private String progVersionEn;
    
    @Column(name="FACTORY_NUMBER_EN", length=32)
    private String factoryNumberEn;
   
    @Column(name="DATETIME_EN", length=32)
    private String datetimeEn;
   
    @Column(name="DAILY_TARIFF_EN", precision=14, scale=7)
    private BigDecimal dailyTariffEn;
   
    @Column(name="NIGHT_TARIFF_EN", precision=14, scale=7)
    private BigDecimal nightTariffEn;
    
    @Column(name="FACTORY_NUMBER_USPD", length=32)
    private String factoryNumberUspd;
    
    @Column(name="COUNT_1_W", precision=13, scale=3)
    private BigDecimal count1W;
    
    @Column(name="COUNT_2_W", precision=13, scale=3)
    private BigDecimal count2W;
   
    @Column(name="COUNT_3_W", precision=13, scale=3)
    private BigDecimal count3W;
    
    @Column(name="COUNT_4_W", precision=13, scale=3)
    private BigDecimal count4W;
    
    @Column(name="COUNT_5_W", precision=13, scale=3)
    private BigDecimal count5W;
    
    @Column(name="COUNT_6_W", precision=13, scale=3)
    private BigDecimal count6W;
    
    @Column(name="COUNT_7_W", precision=13, scale=3)
    private BigDecimal count7W;
    
    @Column(name="COUNT_8_W", precision=13, scale=3)
    private BigDecimal count8W;
    
    @Column(name="INPUT_STATUS_USPD", length=10)
    private String inputStatusUspd;
    
    @Column(name="OUTPUT_STATUS_USPD", length=10)
    private String outputStatusUspd;
    
    @Column(name="POWER_BATTERY_USPD", precision=4, scale=2)
    private BigDecimal powerBatteryUspd;
    
    @Column(name="LOW_POWER_BATTERY_USPD", length=3)
    private String lowPowerBatteryUspd;
    
    @Column(name="THRESHOLD_COUNTER_USPD", length=10)
    private String thresholdCounterUspd;
    
    @Column(name="TIME_STAMP")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeStamp;
    
    @Column(name="ERROR_EN", length=45)
    private String errorEn;
    
    @Column(name="ALARM_STATUS_ANALOG_SENSOR", length=45)
    private String alarmStatusAnalogSensor;
    
    @OneToMany(mappedBy="measuring", orphanRemoval = true)
    //@OneToMany
    @JsonIgnore
    private List<CountElEn> countElEn = new ArrayList<CountElEn>();
    
    
    @OneToMany(mappedBy="measuring", orphanRemoval = true)
    //@OneToMany
    @JsonIgnore
    private List<CountWater> countWater = new ArrayList<CountWater>();
    
    /** Default constructor. ***********************************************************************/
    public Measuring() {
        super();
    }

    /**
     * Access method for idMeasuring.
     *
     * @return the current value of idMeasuring
     */
    
    
    
    public int getIdMeasuring() {
        return idMeasuring;
    }

    public String getAlarmStatusAnalogSensor() {
		return alarmStatusAnalogSensor;
	}

	public void setAlarmStatusAnalogSensor(String alarmStatusAnalogSensor) {
		this.alarmStatusAnalogSensor = alarmStatusAnalogSensor;
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
     * Access method for progVersionEn.
     *
     * @return the current value of progVersionEn
     */
    public String getProgVersionEn() {
        return progVersionEn;
    }

    /**
     * Setter method for progVersionEn.
     *
     * @param aProgVersionEn the new value for progVersionEn
     */
    public void setProgVersionEn(String aProgVersionEn) {
        progVersionEn = aProgVersionEn;
    }

    /**
     * Access method for factoryNumberEn.
     *
     * @return the current value of factoryNumberEn
     */
    public String getFactoryNumberEn() {
        return factoryNumberEn;
    }

    /**
     * Setter method for factoryNumberEn.
     *
     * @param aFactoryNumberEn the new value for factoryNumberEn
     */
    public void setFactoryNumberEn(String aFactoryNumberEn) {
        factoryNumberEn = aFactoryNumberEn;
    }

    /**
     * Access method for datetimeEn.
     *
     * @return the current value of datetimeEn
     */
    public String getDatetimeEn() {
        return datetimeEn;
    }

    /**
     * Setter method for datetimeEn.
     *
     * @param aDatetimeEn the new value for datetimeEn
     */
    public void setDatetimeEn(String aDatetimeEn) {
        datetimeEn = aDatetimeEn;
    }

    /**
     * Access method for dailyTariffEn.
     *
     * @return the current value of dailyTariffEn
     */
    public BigDecimal getDailyTariffEn() {
        return dailyTariffEn;
    }

    /**
     * Setter method for dailyTariffEn.
     *
     * @param aDailyTariffEn the new value for dailyTariffEn
     */
    public void setDailyTariffEn(BigDecimal aDailyTariffEn) {
        dailyTariffEn = aDailyTariffEn;
    }

    /**
     * Access method for nightTariffEn.
     *
     * @return the current value of nightTariffEn
     */
    public BigDecimal getNightTariffEn() {
        return nightTariffEn;
    }

    /**
     * Setter method for nightTariffEn.
     *
     * @param aNightTariffEn the new value for nightTariffEn
     */
    public void setNightTariffEn(BigDecimal aNightTariffEn) {
        nightTariffEn = aNightTariffEn;
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
     * Access method for count1W.
     *
     * @return the current value of count1W
     */
    public BigDecimal getCount1W() {
        return count1W;
    }

    /**
     * Setter method for count1W.
     *
     * @param aCount1W the new value for count1W
     */
    public void setCount1W(BigDecimal aCount1W) {
        count1W = aCount1W;
    }

    /**
     * Access method for count2W.
     *
     * @return the current value of count2W
     */
    public BigDecimal getCount2W() {
        return count2W;
    }

    /**
     * Setter method for count2W.
     *
     * @param aCount2W the new value for count2W
     */
    public void setCount2W(BigDecimal aCount2W) {
        count2W = aCount2W;
    }

    /**
     * Access method for count3W.
     *
     * @return the current value of count3W
     */
    public BigDecimal getCount3W() {
        return count3W;
    }

    /**
     * Setter method for count3W.
     *
     * @param aCount3W the new value for count3W
     */
    public void setCount3W(BigDecimal aCount3W) {
        count3W = aCount3W;
    }

    /**
     * Access method for count4W.
     *
     * @return the current value of count4W
     */
    public BigDecimal getCount4W() {
        return count4W;
    }

    /**
     * Setter method for count4W.
     *
     * @param aCount4W the new value for count4W
     */
    public void setCount4W(BigDecimal aCount4W) {
        count4W = aCount4W;
    }

    /**
     * Access method for count5W.
     *
     * @return the current value of count5W
     */
    public BigDecimal getCount5W() {
        return count5W;
    }

    /**
     * Setter method for count5W.
     *
     * @param aCount5W the new value for count5W
     */
    public void setCount5W(BigDecimal aCount5W) {
        count5W = aCount5W;
    }

    /**
     * Access method for count6W.
     *
     * @return the current value of count6W
     */
    public BigDecimal getCount6W() {
        return count6W;
    }

    /**
     * Setter method for count6W.
     *
     * @param aCount6W the new value for count6W
     */
    public void setCount6W(BigDecimal aCount6W) {
        count6W = aCount6W;
    }

    /**
     * Access method for count7W.
     *
     * @return the current value of count7W
     */
    public BigDecimal getCount7W() {
        return count7W;
    }

    /**
     * Setter method for count7W.
     *
     * @param aCount7W the new value for count7W
     */
    public void setCount7W(BigDecimal aCount7W) {
        count7W = aCount7W;
    }

    /**
     * Access method for count8W.
     *
     * @return the current value of count8W
     */
    public BigDecimal getCount8W() {
        return count8W;
    }

    /**
     * Setter method for count8W.
     *
     * @param aCount8W the new value for count8W
     */
    public void setCount8W(BigDecimal aCount8W) {
        count8W = aCount8W;
    }

    /**
     * Access method for inputStatusUspd.
     *
     * @return the current value of inputStatusUspd
     */
    public String getInputStatusUspd() {
        return inputStatusUspd;
    }

    /**
     * Setter method for inputStatusUspd.
     *
     * @param aInputStatusUspd the new value for inputStatusUspd
     */
    public void setInputStatusUspd(String aInputStatusUspd) {
        inputStatusUspd = aInputStatusUspd;
    }

    /**
     * Access method for outputStatusUspd.
     *
     * @return the current value of outputStatusUspd
     */
    public String getOutputStatusUspd() {
        return outputStatusUspd;
    }

    /**
     * Setter method for outputStatusUspd.
     *
     * @param aOutputStatusUspd the new value for outputStatusUspd
     */
    public void setOutputStatusUspd(String aOutputStatusUspd) {
        outputStatusUspd = aOutputStatusUspd;
    }

    /**
     * Access method for powerBatteryUspd.
     *
     * @return the current value of powerBatteryUspd
     */
    public BigDecimal getPowerBatteryUspd() {
        return powerBatteryUspd;
    }

    /**
     * Setter method for powerBatteryUspd.
     *
     * @param aPowerBatteryUspd the new value for powerBatteryUspd
     */
    public void setPowerBatteryUspd(BigDecimal aPowerBatteryUspd) {
        powerBatteryUspd = aPowerBatteryUspd;
    }

    /**
     * Access method for lowPowerBatteryUspd.
     *
     * @return the current value of lowPowerBatteryUspd
     */
    public String getLowPowerBatteryUspd() {
        return lowPowerBatteryUspd;
    }

    /**
     * Setter method for lowPowerBatteryUspd.
     *
     * @param aLowPowerBatteryUspd the new value for lowPowerBatteryUspd
     */
    public void setLowPowerBatteryUspd(String aLowPowerBatteryUspd) {
        lowPowerBatteryUspd = aLowPowerBatteryUspd;
    }

    /**
     * Access method for thresholdCounterUspd.
     *
     * @return the current value of thresholdCounterUspd
     */
    public String getThresholdCounterUspd() {
        return thresholdCounterUspd;
    }

    /**
     * Setter method for thresholdCounterUspd.
     *
     * @param aThresholdCounterUspd the new value for thresholdCounterUspd
     */
    public void setThresholdCounterUspd(String aThresholdCounterUspd) {
        thresholdCounterUspd = aThresholdCounterUspd;
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
     * Access method for errorEn.
     *
     * @return the current value of errorEn
     */
    public String getErrorEn() {
        return errorEn;
    }

    /**
     * Setter method for errorEn.
     *
     * @param aErrorEn the new value for errorEn
     */
    public void setErrorEn(String aErrorEn) {
        errorEn = aErrorEn;
    }

    /**
     * Access method for countElEn.
     *
     * @return the current value of countElEn
     */
    public List<CountElEn> getCountElEn() {
        return countElEn;
    }

    /**
     * Setter method for countElEn.
     *
     * @param aCountElEn the new value for countElEn
     */
    public void setCountElEn(List<CountElEn> aCountElEn) {
        countElEn = aCountElEn;
    }

    /**
     * Access method for countWater.
     *
     * @return the current value of countWater
     */
    public List<CountWater> getCountWater() {
        return countWater;
    }

    /**
     * Setter method for countWater.
     *
     * @param aCountWater the new value for countWater
     */
    public void setCountWater(List<CountWater> aCountWater) {
        countWater = aCountWater;
    }

    /**
     * Compares the key for this instance with another Measuring.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Measuring and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Measuring)) {
            return false;
        }
        Measuring that = (Measuring) other;
        if (this.getIdMeasuring() != that.getIdMeasuring()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Measuring.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Measuring)) return false;
        return this.equalKeys(other) && ((Measuring)other).equalKeys(this);
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
        i = getIdMeasuring();
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
        StringBuffer sb = new StringBuffer("[Measuring |");
        sb.append(" idMeasuring=").append(getIdMeasuring());
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
        ret.put("idMeasuring", Integer.valueOf(getIdMeasuring()));
        return ret;
    }

}
