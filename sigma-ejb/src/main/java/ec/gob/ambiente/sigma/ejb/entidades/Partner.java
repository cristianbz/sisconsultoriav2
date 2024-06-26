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
@Table(name = "sigma.partners")
@NamedQueries({
    @NamedQuery(name = "Partner.findAll", query = "SELECT p FROM Partner p")})
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "part_id")
    private Integer partId;
    @Column(name = "orga_id")
    private Integer orgaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "part_type")
    private String partType;
    @Size(max = 1)
    @Column(name = "part_type_id")
    private String partTypeId;
    @Size(max = 13)
    @Column(name = "part_id_number")
    private String partIdNumber;
    @Size(max = 200)
    @Column(name = "part_name")
    private String partName;
    @Size(max = 100)
    @Column(name = "part_website")
    private String partWebsite;
    @Size(max = 50)
    @Column(name = "part_contact_person")
    private String partContactPerson;
    @Size(max = 100)
    @Column(name = "part_contact_person_position")
    private String partContactPersonPosition;
    @Size(max = 100)
    @Column(name = "part_contact_person_email")
    private String partContactPersonEmail;
    @Size(max = 20)
    @Column(name = "part_phones")
    private String partPhones;
    @Size(max = 30)
    @Column(name = "part_acronym")
    private String partAcronym;
    @Column(name = "part_status")
    private Boolean partStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "part_creator_user")
    private String partCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "part_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date partCreationDate;
    @Size(max = 255)
    @Column(name = "part_user_update")
    private String partUserUpdate;
    @Column(name = "part_date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date partDateUpdate;
    @Size(max = 1024)
    @Column(name = "part_dba_observation")
    private String partDbaObservation;
    
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataId;

    public Partner() {
    	partStatus=true;
    }

    public Partner(Integer partId) {
        this.partId = partId;
    }

    public Partner(Integer partId, String partType, String partName, String partContactPerson, String partPhones, String partCreatorUser, Date partCreationDate, Date partDateUpdate) {
        this.partId = partId;
        this.partType = partType;
        this.partName = partName;
        this.partContactPerson = partContactPerson;
        this.partPhones = partPhones;
        this.partCreatorUser = partCreatorUser;
        this.partCreationDate = partCreationDate;
        this.partDateUpdate = partDateUpdate;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getOrgaId() {
        return orgaId;
    }

    public void setOrgaId(Integer orgaId) {
        this.orgaId = orgaId;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getPartTypeId() {
        return partTypeId;
    }

    public void setPartTypeId(String partTypeId) {
        this.partTypeId = partTypeId;
    }

    public String getPartIdNumber() {
        return partIdNumber;
    }

    public void setPartIdNumber(String partIdNumber) {
        this.partIdNumber = partIdNumber;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartWebsite() {
        return partWebsite;
    }

    public void setPartWebsite(String partWebsite) {
        this.partWebsite = partWebsite;
    }

    public String getPartContactPerson() {
        return partContactPerson;
    }

    public void setPartContactPerson(String partContactPerson) {
        this.partContactPerson = partContactPerson;
    }

    public String getPartContactPersonPosition() {
        return partContactPersonPosition;
    }

    public void setPartContactPersonPosition(String partContactPersonPosition) {
        this.partContactPersonPosition = partContactPersonPosition;
    }

    public String getPartContactPersonEmail() {
        return partContactPersonEmail;
    }

    public void setPartContactPersonEmail(String partContactPersonEmail) {
        this.partContactPersonEmail = partContactPersonEmail;
    }

    public String getPartPhones() {
        return partPhones;
    }

    public void setPartPhones(String partPhones) {
        this.partPhones = partPhones;
    }

    public Boolean getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(Boolean partStatus) {
        this.partStatus = partStatus;
    }

    public String getPartCreatorUser() {
        return partCreatorUser;
    }

    public void setPartCreatorUser(String partCreatorUser) {
        this.partCreatorUser = partCreatorUser;
    }

    public Date getPartCreationDate() {
        return partCreationDate;
    }

    public void setPartCreationDate(Date partCreationDate) {
        this.partCreationDate = partCreationDate;
    }

    public String getPartUserUpdate() {
        return partUserUpdate;
    }

    public void setPartUserUpdate(String partUserUpdate) {
        this.partUserUpdate = partUserUpdate;
    }

    public Date getPartDateUpdate() {
        return partDateUpdate;
    }

    public void setPartDateUpdate(Date partDateUpdate) {
        this.partDateUpdate = partDateUpdate;
    }

    public String getPartDbaObservation() {
        return partDbaObservation;
    }

    public void setPartDbaObservation(String partDbaObservation) {
        this.partDbaObservation = partDbaObservation;
    }

    

    public String getPartAcronym() {
		return partAcronym;
	}

	public void setPartAcronym(String partAcronym) {
		this.partAcronym = partAcronym;
	}

	public Catalog getCataId() {
        return cataId;
    }

    public void setCataId(Catalog cataId) {
        this.cataId = cataId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partId != null ? partId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partner)) {
            return false;
        }
        Partner other = (Partner) object;
        if ((this.partId == null && other.partId != null) || (this.partId != null && !this.partId.equals(other.partId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Partner[ partId=" + partId + " ]";
    }
    
}
