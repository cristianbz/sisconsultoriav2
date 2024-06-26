/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
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
@Table(name = "sigma.projects")
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proj_id")
    private Integer projId;
    @Column(name = "proj_register_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date projRegisterDate;
    @Size(max = 3)
    @Column(name = "proj_type")
    private String projType;
    @Size(max = 20)
    @Column(name = "proj_code")
    private String projCode;
    @Column(name = "proj_registration_date")
    @Temporal(TemporalType.DATE)
    private Date projRegistrationDate;
    @Column(name = "proj_start_date")
    @Temporal(TemporalType.DATE)
    private Date projStartDate;
    @Size(max = 500)
    @Column(name = "proj_title")
    private String projTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "proj_term_from")
    private String projTermFrom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "proj_term_to")
    private String projTermTo;
    @Column(name = "proj_direct_beneficiaries_number")
    private Integer projDirectBeneficiariesNumber;
    @Size(max = 1000)
    @Column(name = "proj_direct_beneficiaries")
    private String projDirectBeneficiaries;
    @Column(name = "proj_indirect_beneficiaries_number")
    private Integer projIndirectBeneficiariesNumber;
    @Size(max = 1000)
    @Column(name = "proj_indirect_beneficiaries")
    private String projIndirectBeneficiaries;
    @Size( max = 1000)
    @Column(name = "proj_general_purpose")
    private String projGeneralPurpose;
    @Column(name = "proj_is_financier")
    private Boolean projIsFinancier;
    @Column(name = "proj_has_relationship_other")
    private Boolean projHasRelationshipOther;
    @Column(name = "proj_last_report")
    private Integer projLastReport;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1,max = 1)
    @Column(name = "proj_register_status")
    private String projRegisterStatus;
    @Size(max = 1)
    @Column(name = "proj_financing_status")
    private String projFinancingStatus;
    @Column(name = "proj_status")
    private Boolean projStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "proj_creator_user")
    private String projCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proj_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date projCreationDate;
    @Size(max = 255)
    @Column(name = "proj_user_update")
    private String projUserUpdate;
    @Column(name = "proj_date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date projDateUpdate;
    @Size(max = 1024)
    @Column(name = "proj_dba_observation")
    private String projDbaObservation;
    @Column(name = "proj_temporary_partner")
    private Boolean projTemporaryPartner;
    @Size(max = 200)
    @Column(name = "proj_implementer_project")
    private String projImplementerProject;
    @Size(max = 20)
    @Column(name = "proj_short_name")
    private String projShortName;
    @Size(max = 7)
    @Column(name = "proj_style_color")
    private String projStyleColor;
    @JoinColumn(name = "acpl_id", referencedColumnName = "acpl_id")
    @ManyToOne(optional = false)
    private ActionPlan acplId;
    @JoinColumn(name = "part_id", referencedColumnName = "part_id")
    @ManyToOne(optional = false)
    private Partner partId;
    @JoinColumn(name = "proj_parent_id", referencedColumnName = "proj_id")
    @ManyToOne
    private Project projParentId;
    

    public Project() {
    	projStatus=true;
    }

    public Project(Integer projId) {
        this.projId = projId;
    }

    public Project(Integer projId, Date projRegisterDate, String projType, String projTitle, String projTermFrom, String projTermTo, String projGeneralPurpose, String projCreatorUser, Date projCreationDate) {
        this.projId = projId;
        this.projRegisterDate = projRegisterDate;
        this.projType = projType;
        this.projTitle = projTitle;
        this.projTermFrom = projTermFrom;
        this.projTermTo = projTermTo;
        this.projGeneralPurpose = projGeneralPurpose;
        this.projCreatorUser = projCreatorUser;
        this.projCreationDate = projCreationDate;
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public Date getProjRegisterDate() {
        return projRegisterDate;
    }

    public void setProjRegisterDate(Date projRegisterDate) {
        this.projRegisterDate = projRegisterDate;
    }

    public String getProjType() {
        return projType;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    public String getProjCode() {
        return projCode;
    }

    public void setProjCode(String projCode) {
        this.projCode = projCode;
    }

    public Date getProjRegistrationDate() {
        return projRegistrationDate;
    }

    public void setProjRegistrationDate(Date projRegistrationDate) {
        this.projRegistrationDate = projRegistrationDate;
    }

    public Date getProjStartDate() {
        return projStartDate;
    }

    public void setProjStartDate(Date projStartDate) {
        this.projStartDate = projStartDate;
    }

    public String getProjTitle() {
        return projTitle;
    }

    public void setProjTitle(String projTitle) {
        this.projTitle = projTitle;
    }

    public String getProjTermFrom() {
        return projTermFrom;
    }

    public void setProjTermFrom(String projTermFrom) {
        this.projTermFrom = projTermFrom;
    }

    public String getProjTermTo() {
        return projTermTo;
    }

    public void setProjTermTo(String projTermTo) {
        this.projTermTo = projTermTo;
    }

    

    public String getProjDirectBeneficiaries() {
        return projDirectBeneficiaries;
    }

    public void setProjDirectBeneficiaries(String projDirectBeneficiaries) {
        this.projDirectBeneficiaries = projDirectBeneficiaries;
    }

    public String getProjIndirectBeneficiaries() {
        return projIndirectBeneficiaries;
    }

    public void setProjIndirectBeneficiaries(String projIndirectBeneficiaries) {
        this.projIndirectBeneficiaries = projIndirectBeneficiaries;
    }

   

    public String getProjGeneralPurpose() {
        return projGeneralPurpose;
    }

    public void setProjGeneralPurpose(String projGeneralPurpose) {
        this.projGeneralPurpose = projGeneralPurpose;
    }

    public Boolean getProjIsFinancier() {
        return projIsFinancier;
    }

    public void setProjIsFinancier(Boolean projIsFinancier) {
        this.projIsFinancier = projIsFinancier;
    }

    public Boolean getProjHasRelationshipOther() {
        return projHasRelationshipOther;
    }

    public void setProjHasRelationshipOther(Boolean projHasRelationshipOther) {
        this.projHasRelationshipOther = projHasRelationshipOther;
    }
    
    
    
    public Integer getProjLastReport() {
		return projLastReport;
	}

	public void setProjLastReport(Integer projLastReport) {
		this.projLastReport = projLastReport;
	}

	public String getProjRegisterStatus() {
		return projRegisterStatus;
	}

	public void setProjRegisterStatus(String projRegisterStatus) {
		this.projRegisterStatus = projRegisterStatus;
	}

	public String getProjFinancingStatus() {
		return projFinancingStatus;
	}

	public void setProjFinancingStatus(String projFinancingStatus) {
		this.projFinancingStatus = projFinancingStatus;
	}

	public Boolean getProjStatus() {
        return projStatus;
    }

    public void setProjStatus(Boolean projStatus) {
        this.projStatus = projStatus;
    }

    public String getProjCreatorUser() {
        return projCreatorUser;
    }

    public void setProjCreatorUser(String projCreatorUser) {
        this.projCreatorUser = projCreatorUser;
    }

    public Date getProjCreationDate() {
        return projCreationDate;
    }

    public void setProjCreationDate(Date projCreationDate) {
        this.projCreationDate = projCreationDate;
    }

    public String getProjUserUpdate() {
        return projUserUpdate;
    }

    public void setProjUserUpdate(String projUserUpdate) {
        this.projUserUpdate = projUserUpdate;
    }

    public Date getProjDateUpdate() {
        return projDateUpdate;
    }

    public void setProjDateUpdate(Date projDateUpdate) {
        this.projDateUpdate = projDateUpdate;
    }

    public String getProjDbaObservation() {
        return projDbaObservation;
    }

    public void setProjDbaObservation(String projDbaObservation) {
        this.projDbaObservation = projDbaObservation;
    }

    public ActionPlan getAcplId() {
        return acplId;
    }

    public void setAcplId(ActionPlan acplId) {
        this.acplId = acplId;
    }

    public Partner getPartId() {
        return partId;
    }

    public void setPartId(Partner partId) {
        this.partId = partId;
    }

   

    public Project getProjParentId() {
        return projParentId;
    }

    public void setProjParentId(Project projParentId) {
        this.projParentId = projParentId;
    }
    
    

    public Integer getProjDirectBeneficiariesNumber() {
		return projDirectBeneficiariesNumber;
	}

	public void setProjDirectBeneficiariesNumber(Integer projDirectBeneficiariesNumber) {
		this.projDirectBeneficiariesNumber = projDirectBeneficiariesNumber;
	}

	public Integer getProjIndirectBeneficiariesNumber() {
		return projIndirectBeneficiariesNumber;
	}

	public void setProjIndirectBeneficiariesNumber(Integer projIndirectBeneficiariesNumber) {
		this.projIndirectBeneficiariesNumber = projIndirectBeneficiariesNumber;
	}

	

    public Boolean getProjTemporaryPartner() {
		return projTemporaryPartner;
	}

	public void setProjTemporaryPartner(Boolean projTemporaryPartner) {
		this.projTemporaryPartner = projTemporaryPartner;
	}
	
	

	public String getProjImplementerProject() {
		return projImplementerProject;
	}

	public void setProjImplementerProject(String projImplementerProject) {
		this.projImplementerProject = projImplementerProject;
	}
	
	


	public String getProjShortName() {
		return projShortName;
	}

	public void setProjShortName(String projShortName) {
		this.projShortName = projShortName;
	}

	public String getProjStyleColor() {
		return projStyleColor;
	}

	public void setProjStyleColor(String projStyleColor) {
		this.projStyleColor = projStyleColor;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (projId != null ? projId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projId == null && other.projId != null) || (this.projId != null && !this.projId.equals(other.projId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Project[ projId=" + projId + " ]";
    }
    
}
