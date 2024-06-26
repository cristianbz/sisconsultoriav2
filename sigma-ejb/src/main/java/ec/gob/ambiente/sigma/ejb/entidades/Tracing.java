/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.tracings")
@NamedQueries({
    @NamedQuery(name = "Tracing.findAll", query = "SELECT t FROM Tracing t")})
public class Tracing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trac_id")
    private Integer tracId;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "trac_year")
    private Integer tracYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trac_executed_budget")
    private BigDecimal tracExecutedBudget;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trac_budget_progress")
    private int tracBudgetProgress;
    @Column(name = "trac_actions_progress")
    private Integer tracActionsProgress;
    @Column(name = "trac_status")
    private Boolean tracStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "trac_creator_user")
    private String tracCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trac_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tracCreationDate;
    @Size(max = 255)
    @Column(name = "trac_user_update")
    private String tracUserUpdate;
    @Column(name = "trac_date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tracDateUpdate;
    @Size(max = 1024)
    @Column(name = "trac_dba_observation")
    private String tracDbaObservation;
    @Size(max = 1)
    @Column(name = "trac_register_status")
    private String tracRegisterStatus;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataId;
    @JoinColumn(name = "proj_id", referencedColumnName = "proj_id")
    @ManyToOne(optional = false)
    private Project projId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;

    public Tracing() {
    	tracStatus=true;
    }

    public Tracing(Integer tracId) {
        this.tracId = tracId;
    }

    public Tracing(Integer tracId, int userId, BigDecimal tracExecutedBudget, int tracBudgetProgress, String tracCreatorUser, Date tracCreationDate) {
        this.tracId = tracId;
        //this.userId = userId;
        this.tracExecutedBudget = tracExecutedBudget;
        this.tracBudgetProgress = tracBudgetProgress;
        this.tracCreatorUser = tracCreatorUser;
        this.tracCreationDate = tracCreationDate;
    }

    public Integer getTracId() {
        return tracId;
    }

    public void setTracId(Integer tracId) {
        this.tracId = tracId;
    }

   

    public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public BigDecimal getTracExecutedBudget() {
        return tracExecutedBudget;
    }

    public void setTracExecutedBudget(BigDecimal tracExecutedBudget) {
        this.tracExecutedBudget = tracExecutedBudget;
    }

    public int getTracBudgetProgress() {
        return tracBudgetProgress;
    }

    public void setTracBudgetProgress(int tracBudgetProgress) {
        this.tracBudgetProgress = tracBudgetProgress;
    }

   

    public Integer getTracYear() {
		return tracYear;
	}

	public void setTracYear(Integer tracYear) {
		this.tracYear = tracYear;
	}

	public Integer getTracActionsProgress() {
		return tracActionsProgress;
	}

	public void setTracActionsProgress(Integer tracActionsProgress) {
		this.tracActionsProgress = tracActionsProgress;
	}

	public Boolean getTracStatus() {
        return tracStatus;
    }

    public void setTracStatus(Boolean tracStatus) {
        this.tracStatus = tracStatus;
    }

    public String getTracCreatorUser() {
        return tracCreatorUser;
    }

    public void setTracCreatorUser(String tracCreatorUser) {
        this.tracCreatorUser = tracCreatorUser;
    }

    public Date getTracCreationDate() {
        return tracCreationDate;
    }

    public void setTracCreationDate(Date tracCreationDate) {
        this.tracCreationDate = tracCreationDate;
    }

    public String getTracUserUpdate() {
        return tracUserUpdate;
    }

    public void setTracUserUpdate(String tracUserUpdate) {
        this.tracUserUpdate = tracUserUpdate;
    }

    public Date getTracDateUpdate() {
        return tracDateUpdate;
    }

    public void setTracDateUpdate(Date tracDateUpdate) {
        this.tracDateUpdate = tracDateUpdate;
    }

    public String getTracDbaObservation() {
        return tracDbaObservation;
    }

    public void setTracDbaObservation(String tracDbaObservation) {
        this.tracDbaObservation = tracDbaObservation;
    }

    

    

    

    public Catalog getCataId() {
        return cataId;
    }

    public void setCataId(Catalog cataId) {
        this.cataId = cataId;
    }

    public Project getProjId() {
        return projId;
    }

    public void setProjId(Project projId) {
        this.projId = projId;
    }
    
    

    public String getTracRegisterStatus() {
		return tracRegisterStatus;
	}

	public void setTracRegisterStatus(String tracRegisterStatus) {
		this.tracRegisterStatus = tracRegisterStatus;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (tracId != null ? tracId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tracing)) {
            return false;
        }
        Tracing other = (Tracing) object;
        if ((this.tracId == null && other.tracId != null) || (this.tracId != null && !this.tracId.equals(other.tracId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Tracing[ tracId=" + tracId + " ]";
    }
    
}
