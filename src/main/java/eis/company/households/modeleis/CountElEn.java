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




@Entity(name="count_el_en")

public class CountElEn implements Serializable {

    private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "idCountElEn";

        
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_COUNT_EL_EN", unique=true, nullable=false, precision=11)
    private int idCountElEn;
   
    @Column(name="PROG_VERSION_EN", length=32)
    private String progVersionEn;
    
    @Column(name="FACTORY_NUMBER_EN", length=32)
    private String factoryNumberEn;
    
    @Column(name="ID_MEASURING", insertable=false, updatable=false)
    private int id_Measuring;
   
    @Column(name="DATETIME_EN", length=32)
    private String datetimeEn;
    
    @Column(name="TARIFF_EN", precision=14, scale=7)
    private BigDecimal tariffEn;
    
    @Column(name="FACTORY_NUMBER_USPD", length=32)
    private String factoryNumberUspd;
    
    @Column(name="TIME_STAMP")
    private LocalDateTime timeStamp;
   
    @Column(name="ERROR_EN", length=45)
    private String errorEn;
   
    @Column(name="TYPE_TARIF", precision=4)
    private short typeTarif;
    
    @Column(name="COUNT_TYPE", precision=4)
    private int countType;
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="ID_MEASURING", nullable=false)
    private Measuring measuring;
    
    /** Default constructor. */
    public CountElEn() {
        super();
    }

    /**
     * Access method for idCountElEn.
     *
     * @return the current value of idCountElEn
     */
    public int getIdCountElEn() {
        return idCountElEn;
    }

    /**
     * Setter method for idCountElEn.
     *
     * @param aIdCountElEn the new value for idCountElEn
     */
    public void setIdCountElEn(int aIdCountElEn) {
        idCountElEn = aIdCountElEn;
    }
    
    

    public int getCountType() {
		return countType;
	}

	public void setCountType(int countType) {
		this.countType = countType;
	}

	public Measuring getMeasuring() {
		return measuring;
	}

	public void setMeasuring(Measuring measuring) {
		this.measuring = measuring;
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
    
    

    public int getId_Measuring() {
		return id_Measuring;
	}

	public void setId_Measuring(int id_Measuring) {
		this.id_Measuring = id_Measuring;
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
     * Access method for tariffEn.
     *
     * @return the current value of tariffEn
     */
    public BigDecimal getTariffEn() {
        return tariffEn;
    }

    /**
     * Setter method for tariffEn.
     *
     * @param aTariffEn the new value for tariffEn
     */
    public void setTariffEn(BigDecimal aTariffEn) {
        tariffEn = aTariffEn;
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
     * Access method for typeTarif.
     *
     * @return the current value of typeTarif
     */
    public short getTypeTarif() {
        return typeTarif;
    }

    /**
     * Setter method for typeTarif.
     *
     * @param aTypeTarif the new value for typeTarif
     */
    public void setTypeTarif(short aTypeTarif) {
        typeTarif = aTypeTarif;
    }

    /**
     * Access method for measuring.
     *
     * @return the current value of measuring
     */
    
    /*
    public Measuring getMeasuring() {
        return measuring;
    }
    */

    /**
     * Setter method for measuring.
     *
     * @param aMeasuring the new value for measuring
     */
    
    /*
    public void setMeasuring(Measuring aMeasuring) {
        measuring = aMeasuring;
    }
    */

    /**
     * Compares the key for this instance with another CountElEn.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class CountElEn and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof CountElEn)) {
            return false;
        }
        CountElEn that = (CountElEn) other;
        if (this.getIdCountElEn() != that.getIdCountElEn()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another CountElEn.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CountElEn)) return false;
        return this.equalKeys(other) && ((CountElEn)other).equalKeys(this);
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
        i = getIdCountElEn();
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
        StringBuffer sb = new StringBuffer("[CountElEn |");
        sb.append(" idCountElEn=").append(getIdCountElEn());
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
        ret.put("idCountElEn", Integer.valueOf(getIdCountElEn()));
        return ret;
    }

}
