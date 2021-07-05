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

@Entity(name="alarm")
public class Alarm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idAlarm";

    

    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_ALARM", unique=true, nullable=false)
    private int idAlarm;
    @Column(name="ID_MEASURING")
    private int idMeasuring;
    @Column(name="FACTORY_NUMBER_USPD")
    private int factoryNumberUspd;
    @Column(name="OUTPUT_STATUS_USPD", length=10)
    private String outputStatusUspd;
    @Column(name="THRESHOLD_COUNTER_USPD", length=10)
    private String thresholdCounterUspd;
    @Column(name="ALARM_STATUS_ANALOG_SENSOR", length=45)
    private String alarmStatusAnalogSensor;
    @Column(name="LOW_POWER_BATTERY_USPD", length=3)
    private String lowPowerBatteryUspd;
    @Column(name="POWER_BATTERY_USPD", precision=4, scale=2)
    private BigDecimal powerBatteryUspd;
    @Column(name="TIME_STAMP")
    private LocalDateTime timeStamp;
    @Column(name="ACTIVE", length=3)
    private String active;
    @ManyToOne
    @JoinColumn(name="ID_TYPE_ALARM_MSG")
    private TypeAlarmMsg typeAlarmMsg;

    /** Default constructor. */
    public Alarm() {
        super();
    }

    /**
     * Access method for idAlarm.
     *
     * @return the current value of idAlarm
     */
    public int getIdAlarm() {
        return idAlarm;
    }

    /**
     * Setter method for idAlarm.
     *
     * @param aIdAlarm the new value for idAlarm
     */
    public void setIdAlarm(int aIdAlarm) {
        idAlarm = aIdAlarm;
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
    public int getFactoryNumberUspd() {
        return factoryNumberUspd;
    }

    /**
     * Setter method for factoryNumberUspd.
     *
     * @param aFactoryNumberUspd the new value for factoryNumberUspd
     */
    public void setFactoryNumberUspd(int aFactoryNumberUspd) {
        factoryNumberUspd = aFactoryNumberUspd;
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
     * Access method for alarmStatusAnalogSensor.
     *
     * @return the current value of alarmStatusAnalogSensor
     */
    public String getAlarmStatusAnalogSensor() {
        return alarmStatusAnalogSensor;
    }

    /**
     * Setter method for alarmStatusAnalogSensor.
     *
     * @param aAlarmStatusAnalogSensor the new value for alarmStatusAnalogSensor
     */
    public void setAlarmStatusAnalogSensor(String aAlarmStatusAnalogSensor) {
        alarmStatusAnalogSensor = aAlarmStatusAnalogSensor;
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
     * Access method for active.
     *
     * @return the current value of active
     */
    public String getActive() {
        return active;
    }

    /**
     * Setter method for active.
     *
     * @param aActive the new value for active
     */
    public void setActive(String aActive) {
        active = aActive;
    }

    /**
     * Access method for typeAlarmMsg.
     *
     * @return the current value of typeAlarmMsg
     */
    public TypeAlarmMsg getTypeAlarmMsg() {
        return typeAlarmMsg;
    }

    /**
     * Setter method for typeAlarmMsg.
     *
     * @param aTypeAlarmMsg the new value for typeAlarmMsg
     */
    public void setTypeAlarmMsg(TypeAlarmMsg aTypeAlarmMsg) {
        typeAlarmMsg = aTypeAlarmMsg;
    }

    /**
     * Compares the key for this instance with another Alarm.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Alarm and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Alarm)) {
            return false;
        }
        Alarm that = (Alarm) other;
        if (this.getIdAlarm() != that.getIdAlarm()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Alarm.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Alarm)) return false;
        return this.equalKeys(other) && ((Alarm)other).equalKeys(this);
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
        i = getIdAlarm();
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
        StringBuffer sb = new StringBuffer("[Alarm |");
        sb.append(" idAlarm=").append(getIdAlarm());
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
        ret.put("idAlarm", Integer.valueOf(getIdAlarm()));
        return ret;
    }

}
