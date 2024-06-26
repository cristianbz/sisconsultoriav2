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
@Table(name = "sigma.agreements")
@NamedQueries({
    @NamedQuery(name = "Agreement.findAll", query = "SELECT a FROM Agreement a")})
public class Agreement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "agre_id")
    private Integer agreId;
    
    
    @Size(max = 6)
    @Column(name = "agre_register_code")
    private String agreRegisterCode;
    @Size(max = 5)
    @Column(name = "agre_invoice_code")
    private String agreInvoiceCode;
    
    @Column(name = "agre_register_date")
    @Temporal(TemporalType.DATE)
    private Date agreRegisterDate;
    
    @Size(max = 200)
    @Column(name = "agre_title")
    private String agreTitle;
    
    @Size(max = 100)
    @Column(name = "agre_validity")
    private String agreValidity;
    @Column(name = "agre_subscription_date")
    @Temporal(TemporalType.DATE)
    private Date agreSubscriptionDate;
    @Column(name = "agre_to_date")
    @Temporal(TemporalType.DATE)
    private Date agreToDate;
    @Size(max = 350)
    @Column(name = "agre_scope")
    private String agreScope;
    @Size(max = 500)
    @Column(name = "agre_financing")
    private String agreFinancing;
    @Column(name = "agre_status")
    private Boolean agreStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "agre_creator_user")
    private String agreCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agre_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date agreCreationDate;
    @Size(max = 255)
    @Column(name = "agre_user_update")
    private String agreUserUpdate;
    @Column(name = "agre_date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date agreDateUpdate;
    @Size(max = 1024)
    @Column(name = "agre_dba_observation")
    private String agreDbaObservation;
    @JoinColumn(name = "docu_id", referencedColumnName = "docu_id")
    @ManyToOne
    private Document docuId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agreId")
    private List<ProjectsAgreement> projectsAgreementList;

    public Agreement() {
    	agreStatus=true;
    }

    public Agreement(Integer agreId) {
        this.agreId = agreId;
    }

    public Agreement(Integer agreId, int userId, String agreRegisterCode, Date agreRegisterDate, String agreTitle, String agreValidity, String agreCreatorUser, Date agreCreationDate) {
        this.agreId = agreId;
        this.agreRegisterCode = agreRegisterCode;
        this.agreRegisterDate = agreRegisterDate;
        this.agreTitle = agreTitle;
        this.agreValidity = agreValidity;
        this.agreCreatorUser = agreCreatorUser;
        this.agreCreationDate = agreCreationDate;
    }

    public Integer getAgreId() {
        return agreId;
    }

    public void setAgreId(Integer agreId) {
        this.agreId = agreId;
    }

    

    public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getAgreRegisterCode() {
        return agreRegisterCode;
    }

    public void setAgreRegisterCode(String agreRegisterCode) {
        this.agreRegisterCode = agreRegisterCode;
    }

    public String getAgreInvoiceCode() {
        return agreInvoiceCode;
    }

    public void setAgreInvoiceCode(String agreInvoiceCode) {
        this.agreInvoiceCode = agreInvoiceCode;
    }

    public Date getAgreRegisterDate() {
        return agreRegisterDate;
    }

    public void setAgreRegisterDate(Date agreRegisterDate) {
        this.agreRegisterDate = agreRegisterDate;
    }

    public String getAgreTitle() {
        return agreTitle;
    }

    public void setAgreTitle(String agreTitle) {
        this.agreTitle = agreTitle;
    }

    public String getAgreValidity() {
        return agreValidity;
    }

    public void setAgreValidity(String agreValidity) {
        this.agreValidity = agreValidity;
    }

    public Date getAgreSubscriptionDate() {
        return agreSubscriptionDate;
    }

    public void setAgreSubscriptionDate(Date agreSubscriptionDate) {
        this.agreSubscriptionDate = agreSubscriptionDate;
    }

    public Date getAgreToDate() {
        return agreToDate;
    }

    public void setAgreToDate(Date agreToDate) {
        this.agreToDate = agreToDate;
    }

    public String getAgreScope() {
        return agreScope;
    }

    public void setAgreScope(String agreScope) {
        this.agreScope = agreScope;
    }

    public String getAgreFinancing() {
        return agreFinancing;
    }

    public void setAgreFinancing(String agreFinancing) {
        this.agreFinancing = agreFinancing;
    }

    public Boolean getAgreStatus() {
        return agreStatus;
    }

    public void setAgreStatus(Boolean agreStatus) {
        this.agreStatus = agreStatus;
    }

    public String getAgreCreatorUser() {
        return agreCreatorUser;
    }

    public void setAgreCreatorUser(String agreCreatorUser) {
        this.agreCreatorUser = agreCreatorUser;
    }

    public Date getAgreCreationDate() {
        return agreCreationDate;
    }

    public void setAgreCreationDate(Date agreCreationDate) {
        this.agreCreationDate = agreCreationDate;
    }

    public String getAgreUserUpdate() {
        return agreUserUpdate;
    }

    public void setAgreUserUpdate(String agreUserUpdate) {
        this.agreUserUpdate = agreUserUpdate;
    }

    public Date getAgreDateUpdate() {
        return agreDateUpdate;
    }

    public void setAgreDateUpdate(Date agreDateUpdate) {
        this.agreDateUpdate = agreDateUpdate;
    }

    public String getAgreDbaObservation() {
        return agreDbaObservation;
    }

    public void setAgreDbaObservation(String agreDbaObservation) {
        this.agreDbaObservation = agreDbaObservation;
    }

    public Document getDocuId() {
        return docuId;
    }

    public void setDocuId(Document docuId) {
        this.docuId = docuId;
    }

    public List<ProjectsAgreement> getProjectsAgreementList() {
        return projectsAgreementList;
    }

    public void setProjectsAgreementList(List<ProjectsAgreement> projectsAgreementList) {
        this.projectsAgreementList = projectsAgreementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agreId != null ? agreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agreement)) {
            return false;
        }
        Agreement other = (Agreement) object;
        if ((this.agreId == null && other.agreId != null) || (this.agreId != null && !this.agreId.equals(other.agreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Agreement[ agreId=" + agreId + " ]";
    }
    
}
