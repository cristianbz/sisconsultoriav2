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
@Table(name = "sigma.documents")
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d")})
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "docu_id")
    private Integer docuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "docu_name")
    private String docuName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "docu_mime")
    private String docuMime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "docu_date_upload")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docuDateUpload;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "docu_code_public")
    private String docuCodePublic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "docu_address_alfresco")
    private String docuAddressAlfresco;
    @Size(max = 255)
    @Column(name = "docu_extension")
    private String docuExtension;
    @Basic(optional = false)
    @NotNull
    @Column(name = "docu_date_generation_document")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docuDateGenerationDocument;
    @Size(max = 255)
    @Column(name = "docu_alfresco_id")
    private String docuAlfrescoId;
    @Column(name = "docu_task_bpm_id")
    private Integer docuTaskBpmId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "docu_description")
    private String docuDescription;
    @Column(name = "docu_status")
    private Boolean docuStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "docu_creator_user")
    private String docuCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "docu_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docuCreationDate;
    @Size(max = 255)
    @Column(name = "docu_user_update")
    private String docuUserUpdate;
    @Column(name = "docu_date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docuDateUpdate;
    @Size(max = 1024)
    @Column(name = "docu_dba_observation")
    private String docuDbaObservation;
    

    public Document() {
    	docuStatus=true;
    }

    public Document(Integer docuId) {
        this.docuId = docuId;
    }

    public Document(Integer docuId, String docuName, String docuMime, Date docuDateUpload, String docuCodePublic, String docuAddressAlfresco, Date docuDateGenerationDocument, String docuDescription, String docuCreatorUser, Date docuCreationDate) {
        this.docuId = docuId;
        this.docuName = docuName;
        this.docuMime = docuMime;
        this.docuDateUpload = docuDateUpload;
        this.docuCodePublic = docuCodePublic;
        this.docuAddressAlfresco = docuAddressAlfresco;
        this.docuDateGenerationDocument = docuDateGenerationDocument;
        this.docuDescription = docuDescription;
        this.docuCreatorUser = docuCreatorUser;
        this.docuCreationDate = docuCreationDate;
    }

    public Integer getDocuId() {
        return docuId;
    }

    public void setDocuId(Integer docuId) {
        this.docuId = docuId;
    }

    public String getDocuName() {
        return docuName;
    }

    public void setDocuName(String docuName) {
        this.docuName = docuName;
    }

    public String getDocuMime() {
        return docuMime;
    }

    public void setDocuMime(String docuMime) {
        this.docuMime = docuMime;
    }

    public Date getDocuDateUpload() {
        return docuDateUpload;
    }

    public void setDocuDateUpload(Date docuDateUpload) {
        this.docuDateUpload = docuDateUpload;
    }

    public String getDocuCodePublic() {
        return docuCodePublic;
    }

    public void setDocuCodePublic(String docuCodePublic) {
        this.docuCodePublic = docuCodePublic;
    }

    public String getDocuAddressAlfresco() {
        return docuAddressAlfresco;
    }

    public void setDocuAddressAlfresco(String docuAddressAlfresco) {
        this.docuAddressAlfresco = docuAddressAlfresco;
    }

    public String getDocuExtension() {
        return docuExtension;
    }

    public void setDocuExtension(String docuExtension) {
        this.docuExtension = docuExtension;
    }

    public Date getDocuDateGenerationDocument() {
        return docuDateGenerationDocument;
    }

    public void setDocuDateGenerationDocument(Date docuDateGenerationDocument) {
        this.docuDateGenerationDocument = docuDateGenerationDocument;
    }

    public String getDocuAlfrescoId() {
        return docuAlfrescoId;
    }

    public void setDocuAlfrescoId(String docuAlfrescoId) {
        this.docuAlfrescoId = docuAlfrescoId;
    }

    public Integer getDocuTaskBpmId() {
        return docuTaskBpmId;
    }

    public void setDocuTaskBpmId(Integer docuTaskBpmId) {
        this.docuTaskBpmId = docuTaskBpmId;
    }

    public String getDocuDescription() {
        return docuDescription;
    }

    public void setDocuDescription(String docuDescription) {
        this.docuDescription = docuDescription;
    }

    public Boolean getDocuStatus() {
        return docuStatus;
    }

    public void setDocuStatus(Boolean docuStatus) {
        this.docuStatus = docuStatus;
    }

    public String getDocuCreatorUser() {
        return docuCreatorUser;
    }

    public void setDocuCreatorUser(String docuCreatorUser) {
        this.docuCreatorUser = docuCreatorUser;
    }

    public Date getDocuCreationDate() {
        return docuCreationDate;
    }

    public void setDocuCreationDate(Date docuCreationDate) {
        this.docuCreationDate = docuCreationDate;
    }

    public String getDocuUserUpdate() {
        return docuUserUpdate;
    }

    public void setDocuUserUpdate(String docuUserUpdate) {
        this.docuUserUpdate = docuUserUpdate;
    }

    public Date getDocuDateUpdate() {
        return docuDateUpdate;
    }

    public void setDocuDateUpdate(Date docuDateUpdate) {
        this.docuDateUpdate = docuDateUpdate;
    }

    public String getDocuDbaObservation() {
        return docuDbaObservation;
    }

    public void setDocuDbaObservation(String docuDbaObservation) {
        this.docuDbaObservation = docuDbaObservation;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docuId != null ? docuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.docuId == null && other.docuId != null) || (this.docuId != null && !this.docuId.equals(other.docuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Document[ docuId=" + docuId + " ]";
    }
    
}
