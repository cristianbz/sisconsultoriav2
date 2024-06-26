/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.tracings_progress")
@NamedQueries({
    @NamedQuery(name = "TracingsProgress.findAll", query = "SELECT t FROM TracingsProgress t")})
public class TracingsProgress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trpr_id")
    private Integer trprId;
    @Column(name = "trpr_reached_value")
    private Double trprReachedValue;
    @Column(name = "trpr_progress_percent")
    private Integer trprProgressPercent;
    @Size(max = 2147483647)
    @Column(name = "trpr_details")
    private String trprDetails;
    @Column(name = "trpr_risk_ispresent")
    private Boolean trprRiskIspresent;
    @Size(max = 1)
    @Column(name = "trpr_risk_status")
    private String trprRiskStatus;
    @Column(name = "trpr_status")
    private Boolean trprStatus;
    @JoinColumn(name = "trac_id", referencedColumnName = "trac_id")
    @ManyToOne(optional = false)
    private Tracing tracId;
    @JoinColumn(name = "prac_id", referencedColumnName = "prac_id")
    @ManyToOne
    private ProjectsAction pracId;
    @JoinColumn(name = "prri_id", referencedColumnName = "prri_id")
    @ManyToOne
    private ProjectsRisk prriId;
    @Transient
    private String trprInfoExtra;

    public TracingsProgress() {
    	trprStatus=true;
    }

    public TracingsProgress(Integer trprId) {
        this.trprId = trprId;
    }

    public Integer getTrprId() {
        return trprId;
    }

    public void setTrprId(Integer trprId) {
        this.trprId = trprId;
    }

    

    public Integer getTrprProgressPercent() {
        return trprProgressPercent;
    }

    public void setTrprProgressPercent(Integer trprProgressPercent) {
        this.trprProgressPercent = trprProgressPercent;
    }

   

    public String getTrprRiskStatus() {
        return trprRiskStatus;
    }

    public void setTrprRiskStatus(String trprRiskStatus) {
        this.trprRiskStatus = trprRiskStatus;
    }

    public Boolean getTrprStatus() {
        return trprStatus;
    }

    public void setTrprStatus(Boolean trprStatus) {
        this.trprStatus = trprStatus;
    }

    public Tracing getTracId() {
        return tracId;
    }

    public void setTracId(Tracing tracId) {
        this.tracId = tracId;
   
    }
    
    
    public Double getTrprReachedValue() {
		return trprReachedValue;
	}

	public void setTrprReachedValue(Double trprReachedValue) {
		this.trprReachedValue = trprReachedValue;
	}

	public String getTrprDetails() {
		return trprDetails;
	}

	public void setTrprDetails(String trprDetails) {
		this.trprDetails = trprDetails;
	}

	public ProjectsAction getPracId() {
		return pracId;
	}

	public void setPracId(ProjectsAction pracId) {
		this.pracId = pracId;
	}

	public ProjectsRisk getPrriId() {
		return prriId;
	}

	public void setPrriId(ProjectsRisk prriId) {
		this.prriId = prriId;
	}
	
	

	public Boolean getTrprRiskIspresent() {
		return trprRiskIspresent;
	}

	public void setTrprRiskIspresent(Boolean trprRiskIspresent) {
		this.trprRiskIspresent = trprRiskIspresent;
	}
	
	

	public String getTrprInfoExtra() {
		return trprInfoExtra;
	}

	public void setTrprInfoExtra(String trprInfoExtra) {
		this.trprInfoExtra = trprInfoExtra;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (trprId != null ? trprId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TracingsProgress)) {
            return false;
        }
        TracingsProgress other = (TracingsProgress) object;
        if ((this.trprId == null && other.trprId != null) || (this.trprId != null && !this.trprId.equals(other.trprId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.TracingsProgress[ trprId=" + trprId + " ]";
    }
    
}
