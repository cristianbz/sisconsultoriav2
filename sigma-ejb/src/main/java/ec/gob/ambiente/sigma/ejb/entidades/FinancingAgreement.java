/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.financing_agreements")
@NamedQueries({
    @NamedQuery(name = "FinancingAgreement.findAll", query = "SELECT f FROM FinancingAgreement f")})
public class FinancingAgreement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fiag_id")
    private Integer fiagId;
    @Size(max = 11)
    @Column(name = "fiag_code")
    private String fiagCode;
    @Size(max = 100)
    @Column(name = "fiag_title")
    private String fiagTitle;
    @Column(name = "fiag_from_date")
    @Temporal(TemporalType.DATE)
    private Date fiagFromDate;
    @Column(name = "fiag_to_date")
    @Temporal(TemporalType.DATE)
    private Date fiagToDate;
    @Column(name = "fiag_sign_date")
    @Temporal(TemporalType.DATE)
    private Date fiagSignDate;
    @Column(name = "fiag_fiscal_resources")
    private Boolean fiagFiscalResources;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fiag_amount")
    private BigDecimal fiagAmount;
    @Column(name = "fiag_status")
    private Boolean fiagStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fiag_creator_user")
    private String fiagCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fiag_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiagCreationDate;
    @Size(max = 255)
    @Column(name = "fiag_user_update")
    private String fiagUserUpdate;
    @Column(name = "fiag_date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiagDateUpdate;
    @Size(max = 1024)
    @Column(name = "fiag_dba_observation")
    private String fiagDbaObservation;
    @JoinColumn(name = "cata_admin_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataAdminId;
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id")
    @ManyToOne
    private Document docuId;
    @JoinColumn(name = "fuso_id", referencedColumnName = "fuso_id")
    @ManyToOne(optional = false)
    private FundingSource fusoId;

    public FinancingAgreement() {
    	fiagStatus=true;
    }

    public FinancingAgreement(Integer fiagId) {
        this.fiagId = fiagId;
    }

    public FinancingAgreement(Integer fiagId, String fiagCode, String fiagTitle, Date fiagFromDate, Date fiagToDate, Date fiagSignDate, BigDecimal fiagAmount, String fiagCreatorUser, Date fiagCreationDate) {
        this.fiagId = fiagId;
        this.fiagCode = fiagCode;
        this.fiagTitle = fiagTitle;
        this.fiagFromDate = fiagFromDate;
        this.fiagToDate = fiagToDate;
        this.fiagSignDate = fiagSignDate;
        this.fiagAmount = fiagAmount;
        this.fiagCreatorUser = fiagCreatorUser;
        this.fiagCreationDate = fiagCreationDate;
    }

    public Integer getFiagId() {
        return fiagId;
    }

    public void setFiagId(Integer fiagId) {
        this.fiagId = fiagId;
    }

    public String getFiagCode() {
        return fiagCode;
    }

    public void setFiagCode(String fiagCode) {
        this.fiagCode = fiagCode;
    }

    public String getFiagTitle() {
        return fiagTitle;
    }

    public void setFiagTitle(String fiagTitle) {
        this.fiagTitle = fiagTitle;
    }

    public Date getFiagFromDate() {
        return fiagFromDate;
    }

    public void setFiagFromDate(Date fiagFromDate) {
        this.fiagFromDate = fiagFromDate;
    }

    public Date getFiagToDate() {
        return fiagToDate;
    }

    public void setFiagToDate(Date fiagToDate) {
        this.fiagToDate = fiagToDate;
    }

    public Date getFiagSignDate() {
        return fiagSignDate;
    }

    public void setFiagSignDate(Date fiagSignDate) {
        this.fiagSignDate = fiagSignDate;
    }

    public Boolean getFiagFiscalResources() {
        return fiagFiscalResources;
    }

    public void setFiagFiscalResources(Boolean fiagFiscalResources) {
        this.fiagFiscalResources = fiagFiscalResources;
    }

    public BigDecimal getFiagAmount() {
        return fiagAmount;
    }

    public void setFiagAmount(BigDecimal fiagAmount) {
        this.fiagAmount = fiagAmount;
    }

    public Boolean getFiagStatus() {
        return fiagStatus;
    }

    public void setFiagStatus(Boolean fiagStatus) {
        this.fiagStatus = fiagStatus;
    }

    public String getFiagCreatorUser() {
        return fiagCreatorUser;
    }

    public void setFiagCreatorUser(String fiagCreatorUser) {
        this.fiagCreatorUser = fiagCreatorUser;
    }

    public Date getFiagCreationDate() {
        return fiagCreationDate;
    }

    public void setFiagCreationDate(Date fiagCreationDate) {
        this.fiagCreationDate = fiagCreationDate;
    }

    public String getFiagUserUpdate() {
        return fiagUserUpdate;
    }

    public void setFiagUserUpdate(String fiagUserUpdate) {
        this.fiagUserUpdate = fiagUserUpdate;
    }

    public Date getFiagDateUpdate() {
        return fiagDateUpdate;
    }

    public void setFiagDateUpdate(Date fiagDateUpdate) {
        this.fiagDateUpdate = fiagDateUpdate;
    }

    public String getFiagDbaObservation() {
        return fiagDbaObservation;
    }

    public void setFiagDbaObservation(String fiagDbaObservation) {
        this.fiagDbaObservation = fiagDbaObservation;
    }

    public Catalog getCataAdminId() {
        return cataAdminId;
    }

    public void setCataAdminId(Catalog cataAdminId) {
        this.cataAdminId = cataAdminId;
    }

    public Document getDocuId() {
        return docuId;
    }

    public void setDocuId(Document docuId) {
        this.docuId = docuId;
    }

    public FundingSource getFusoId() {
        return fusoId;
    }

    public void setFusoId(FundingSource fusoId) {
        this.fusoId = fusoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fiagId != null ? fiagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinancingAgreement)) {
            return false;
        }
        FinancingAgreement other = (FinancingAgreement) object;
        if ((this.fiagId == null && other.fiagId != null) || (this.fiagId != null && !this.fiagId.equals(other.fiagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.FinancingAgreement[ fiagId=" + fiagId + " ]";
    }
    
}
