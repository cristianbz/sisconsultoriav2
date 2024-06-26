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
@Table(name = "sigma.funding_sources")
@NamedQueries({
    @NamedQuery(name = "FundingSource.findAll", query = "SELECT f FROM FundingSource f")})
public class FundingSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fuso_id")
    private Integer fusoId;
    @Size(max = 10)
    @Column(name = "fuso_code")
    private String fusoCode;
    @Size(max = 100)
    @Column(name = "fuso_name")
    private String fusoName;
    @Size(max = 100)
    @Column(name = "fuso_website")
    private String fusoWebsite;
    @Size(max = 100)
    @Column(name = "fuso_contact_person")
    private String fusoContactPerson;
    @Size(max = 100)
    @Column(name = "fuso_contact_person_position")
    private String fusoContactPersonPosition;
    @Size(max = 100)
    @Column(name = "fuso_contact_person_email")
    private String fusoContactPersonEmail;
    @Size(max = 20)
    @Column(name = "fuso_phones")
    private String fusoPhones;
    @Size(max = 20)
    @Column(name = "fuso_other_fund")
    private String fusoOtherFund;
    @Size(max = 20)
    @Column(name = "fuso_other_sector")
    private String fusoOtherSector;
    @Column(name = "fuso_status")
    private Boolean fusoStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fuso_creator_user")
    private String fusoCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fuso_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fusoCreationDate;
    @Size(max = 255)
    @Column(name = "fuso_user_update")
    private String fusoUserUpdate;
    @Column(name = "fuso_date_upate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fusoDateUpate;
    @Size(max = 1024)
    @Column(name = "fuso_dba_observation")
    private String fusoDbaObservation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fusoId")
    private List<FundingSourcesOption> fundingSourcesOptionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fusoId")
    private List<FundingSourcesNote> fundingSourcesNoteList;
    @OneToMany(mappedBy = "fusoId")
    private List<ProjectsFinancing> projectsFinancingList;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne(optional = false)
    private Catalog cataId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fusoId")
    private List<FinancingAgreement> financingAgreementList;

    public FundingSource() {
    	fusoStatus=true;
    }

    public FundingSource(Integer fusoId) {
        this.fusoId = fusoId;
    }

    public FundingSource(Integer fusoId, String fusoCode, String fusoName, String fusoContactPerson, String fusoContactPersonPosition, String fusoContactPersonEmail, String fusoPhones, String fusoCreatorUser, Date fusoCreationDate) {
        this.fusoId = fusoId;
        this.fusoCode = fusoCode;
        this.fusoName = fusoName;
        this.fusoContactPerson = fusoContactPerson;
        this.fusoContactPersonPosition = fusoContactPersonPosition;
        this.fusoContactPersonEmail = fusoContactPersonEmail;
        this.fusoPhones = fusoPhones;
        this.fusoCreatorUser = fusoCreatorUser;
        this.fusoCreationDate = fusoCreationDate;
    }

    public Integer getFusoId() {
        return fusoId;
    }

    public void setFusoId(Integer fusoId) {
        this.fusoId = fusoId;
    }

    public String getFusoCode() {
        return fusoCode;
    }

    public void setFusoCode(String fusoCode) {
        this.fusoCode = fusoCode;
    }

    public String getFusoName() {
        return fusoName;
    }

    public void setFusoName(String fusoName) {
        this.fusoName = fusoName;
    }

    public String getFusoWebsite() {
        return fusoWebsite;
    }

    public void setFusoWebsite(String fusoWebsite) {
        this.fusoWebsite = fusoWebsite;
    }

    public String getFusoContactPerson() {
        return fusoContactPerson;
    }

    public void setFusoContactPerson(String fusoContactPerson) {
        this.fusoContactPerson = fusoContactPerson;
    }

    public String getFusoContactPersonPosition() {
        return fusoContactPersonPosition;
    }

    public void setFusoContactPersonPosition(String fusoContactPersonPosition) {
        this.fusoContactPersonPosition = fusoContactPersonPosition;
    }

    public String getFusoContactPersonEmail() {
        return fusoContactPersonEmail;
    }

    public void setFusoContactPersonEmail(String fusoContactPersonEmail) {
        this.fusoContactPersonEmail = fusoContactPersonEmail;
    }

    public String getFusoPhones() {
        return fusoPhones;
    }

    public void setFusoPhones(String fusoPhones) {
        this.fusoPhones = fusoPhones;
    }

    public String getFusoOtherFund() {
        return fusoOtherFund;
    }

    public void setFusoOtherFund(String fusoOtherFund) {
        this.fusoOtherFund = fusoOtherFund;
    }

    public String getFusoOtherSector() {
        return fusoOtherSector;
    }

    public void setFusoOtherSector(String fusoOtherSector) {
        this.fusoOtherSector = fusoOtherSector;
    }

    public Boolean getFusoStatus() {
        return fusoStatus;
    }

    public void setFusoStatus(Boolean fusoStatus) {
        this.fusoStatus = fusoStatus;
    }

    public String getFusoCreatorUser() {
        return fusoCreatorUser;
    }

    public void setFusoCreatorUser(String fusoCreatorUser) {
        this.fusoCreatorUser = fusoCreatorUser;
    }

    public Date getFusoCreationDate() {
        return fusoCreationDate;
    }

    public void setFusoCreationDate(Date fusoCreationDate) {
        this.fusoCreationDate = fusoCreationDate;
    }

    public String getFusoUserUpdate() {
        return fusoUserUpdate;
    }

    public void setFusoUserUpdate(String fusoUserUpdate) {
        this.fusoUserUpdate = fusoUserUpdate;
    }

    public Date getFusoDateUpate() {
        return fusoDateUpate;
    }

    public void setFusoDateUpate(Date fusoDateUpate) {
        this.fusoDateUpate = fusoDateUpate;
    }

    public String getFusoDbaObservation() {
        return fusoDbaObservation;
    }

    public void setFusoDbaObservation(String fusoDbaObservation) {
        this.fusoDbaObservation = fusoDbaObservation;
    }

    public List<FundingSourcesOption> getFundingSourcesOptionList() {
        return fundingSourcesOptionList;
    }

    public void setFundingSourcesOptionList(List<FundingSourcesOption> fundingSourcesOptionList) {
        this.fundingSourcesOptionList = fundingSourcesOptionList;
    }

    public List<FundingSourcesNote> getFundingSourcesNoteList() {
        return fundingSourcesNoteList;
    }

    public void setFundingSourcesNoteList(List<FundingSourcesNote> fundingSourcesNoteList) {
        this.fundingSourcesNoteList = fundingSourcesNoteList;
    }

    public List<ProjectsFinancing> getProjectsFinancingList() {
        return projectsFinancingList;
    }

    public void setProjectsFinancingList(List<ProjectsFinancing> projectsFinancingList) {
        this.projectsFinancingList = projectsFinancingList;
    }

    public Catalog getCataId() {
        return cataId;
    }

    public void setCataId(Catalog cataId) {
        this.cataId = cataId;
    }

    public List<FinancingAgreement> getFinancingAgreementList() {
        return financingAgreementList;
    }

    public void setFinancingAgreementList(List<FinancingAgreement> financingAgreementList) {
        this.financingAgreementList = financingAgreementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fusoId != null ? fusoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FundingSource)) {
            return false;
        }
        FundingSource other = (FundingSource) object;
        if ((this.fusoId == null && other.fusoId != null) || (this.fusoId != null && !this.fusoId.equals(other.fusoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.FundingSource[ fusoId=" + fusoId + " ]";
    }
    
}
