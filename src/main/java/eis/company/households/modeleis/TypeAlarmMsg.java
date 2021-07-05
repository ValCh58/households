// Generated with g9.

package eis.company.households.modeleis;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="type_alarm_msg")
public class TypeAlarmMsg implements Serializable {

    
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idTypeAlarmMsg";

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_type_alarm_msg", unique=true, nullable=false)
    private int idTypeAlarmMsg;
    @Column(name="MSG_TEXT", length=255)
    private String msgText;
    @OneToMany(mappedBy="typeAlarmMsg")
    private Set<Alarm> alarm;

    /** Default constructor. */
    public TypeAlarmMsg() {
        super();
    }

    /**
     * Access method for idTypeAlarmMsg.
     *
     * @return the current value of idTypeAlarmMsg
     */
    public int getIdTypeAlarmMsg() {
        return idTypeAlarmMsg;
    }

    /**
     * Setter method for idTypeAlarmMsg.
     *
     * @param aIdTypeAlarmMsg the new value for idTypeAlarmMsg
     */
    public void setIdTypeAlarmMsg(int aIdTypeAlarmMsg) {
        idTypeAlarmMsg = aIdTypeAlarmMsg;
    }

    /**
     * Access method for msgText.
     *
     * @return the current value of msgText
     */
    public String getMsgText() {
        return msgText;
    }

    /**
     * Setter method for msgText.
     *
     * @param aMsgText the new value for msgText
     */
    public void setMsgText(String aMsgText) {
        msgText = aMsgText;
    }

    /**
     * Access method for alarm.
     *
     * @return the current value of alarm
     */
    public Set<Alarm> getAlarm() {
        return alarm;
    }

    /**
     * Setter method for alarm.
     *
     * @param aAlarm the new value for alarm
     */
    public void setAlarm(Set<Alarm> aAlarm) {
        alarm = aAlarm;
    }

    /**
     * Compares the key for this instance with another TypeAlarmMsg.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TypeAlarmMsg and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TypeAlarmMsg)) {
            return false;
        }
        TypeAlarmMsg that = (TypeAlarmMsg) other;
        if (this.getIdTypeAlarmMsg() != that.getIdTypeAlarmMsg()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TypeAlarmMsg.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TypeAlarmMsg)) return false;
        return this.equalKeys(other) && ((TypeAlarmMsg)other).equalKeys(this);
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
        i = getIdTypeAlarmMsg();
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
        StringBuffer sb = new StringBuffer("[TypeAlarmMsg |");
        sb.append(" idTypeAlarmMsg=").append(getIdTypeAlarmMsg());
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
        ret.put("idTypeAlarmMsg", Integer.valueOf(getIdTypeAlarmMsg()));
        return ret;
    }

}
