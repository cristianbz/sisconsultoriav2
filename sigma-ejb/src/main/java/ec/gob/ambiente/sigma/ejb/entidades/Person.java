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
@Table(name = "sigma.persons")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pers_id")
    private Integer persId;
    @Column(name = "peop_id")
    private Integer peopId;
    @Column(name = "gelo_id")
    private Integer geloId;
    @Column(name = "pers_year_birth")
    private Integer persYearBirth;
    @Size(max = 1)
    @Column(name = "pers_typeid")
    private String persTypeid;
    @Size(max = 13)
    @Column(name = "pers_id_number")
    private String persIdNumber;
    @Size(max = 30)
    @Column(name = "pers_name")
    private String persName;
    @Size(max = 30)
    @Column(name = "pers_lastname")
    private String persLastname;
    @Size(max = 50)
    @Column(name = "pers_job_area")
    private String persJobArea;
    @Size(max = 100)
    @Column(name = "pers_position")
    private String persPosition;
    @Size(max = 50)
    @Column(name = "pers_phone1")
    private String persPhone1;
    @Size(max = 50)
    @Column(name = "pers_phone2")
    private String persPhone2;
    @Size(max = 150)
    @Column(name = "pers_email1")
    private String persEmail1;
    @Size(max = 150)
    @Column(name = "pers_email2")
    private String persEmail2;
    @Size(max = 30)
    @Column(name = "pers_community")
    private String persCommunity;
    @Size(max = 20)
    @Column(name = "pers_country")
    private String persCountry;
    @Column(name = "pers_status")
    private Boolean persStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pers_creator_user")
    private String persCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pers_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date persCreationDate;
    @Size(max = 255)
    @Column(name = "pers_user_update")
    private String persUserUpdate;
    @Column(name = "pers_date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date persDateUpdate;
    @Size(max = 1024)
    @Column(name = "pers_dba_observation")
    private String persDbaObservation;
    @JoinColumn(name = "cata_org_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataOrgId;
    @JoinColumn(name = "cata_sex_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataSexId;
    @JoinColumn(name = "cata_origin_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataOriginId;
    @JoinColumn(name = "cata_nationality_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataNationalityId;
    @JoinColumn(name = "cata_type_actor_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataTypeActorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persId")
    private List<Participant> participantList;

    public Person() {
    }

    public Person(Integer persId) {
        this.persId = persId;
    }

    public Person(Integer persId, String persCreatorUser, Date persCreationDate) {
        this.persId = persId;
        this.persCreatorUser = persCreatorUser;
        this.persCreationDate = persCreationDate;
    }

    public Integer getPersId() {
        return persId;
    }

    public void setPersId(Integer persId) {
        this.persId = persId;
    }

    public Integer getPeopId() {
        return peopId;
    }

    public void setPeopId(Integer peopId) {
        this.peopId = peopId;
    }

    public Integer getGeloId() {
        return geloId;
    }

    public void setGeloId(Integer geloId) {
        this.geloId = geloId;
    }

    public Integer getPersYearBirth() {
        return persYearBirth;
    }

    public void setPersYearBirth(Integer persYearBirth) {
        this.persYearBirth = persYearBirth;
    }

    public String getPersTypeid() {
        return persTypeid;
    }

    public void setPersTypeid(String persTypeid) {
        this.persTypeid = persTypeid;
    }

    public String getPersIdNumber() {
        return persIdNumber;
    }

    public void setPersIdNumber(String persIdNumber) {
        this.persIdNumber = persIdNumber;
    }

    public String getPersName() {
        return persName;
    }

    public void setPersName(String persName) {
        this.persName = persName;
    }

    public String getPersLastname() {
        return persLastname;
    }

    public void setPersLastname(String persLastname) {
        this.persLastname = persLastname;
    }

    public String getPersJobArea() {
        return persJobArea;
    }

    public void setPersJobArea(String persJobArea) {
        this.persJobArea = persJobArea;
    }

    public String getPersPosition() {
        return persPosition;
    }

    public void setPersPosition(String persPosition) {
        this.persPosition = persPosition;
    }

    public String getPersPhone1() {
        return persPhone1;
    }

    public void setPersPhone1(String persPhone1) {
        this.persPhone1 = persPhone1;
    }

    public String getPersPhone2() {
        return persPhone2;
    }

    public void setPersPhone2(String persPhone2) {
        this.persPhone2 = persPhone2;
    }

    public String getPersEmail1() {
        return persEmail1;
    }

    public void setPersEmail1(String persEmail1) {
        this.persEmail1 = persEmail1;
    }

    public String getPersEmail2() {
        return persEmail2;
    }

    public void setPersEmail2(String persEmail2) {
        this.persEmail2 = persEmail2;
    }

    public String getPersCommunity() {
        return persCommunity;
    }

    public void setPersCommunity(String persCommunity) {
        this.persCommunity = persCommunity;
    }

    public String getPersCountry() {
        return persCountry;
    }

    public void setPersCountry(String persCountry) {
        this.persCountry = persCountry;
    }

    public Boolean getPersStatus() {
        return persStatus;
    }

    public void setPersStatus(Boolean persStatus) {
        this.persStatus = persStatus;
    }

    public String getPersCreatorUser() {
        return persCreatorUser;
    }

    public void setPersCreatorUser(String persCreatorUser) {
        this.persCreatorUser = persCreatorUser;
    }

    public Date getPersCreationDate() {
        return persCreationDate;
    }

    public void setPersCreationDate(Date persCreationDate) {
        this.persCreationDate = persCreationDate;
    }

    public String getPersUserUpdate() {
        return persUserUpdate;
    }

    public void setPersUserUpdate(String persUserUpdate) {
        this.persUserUpdate = persUserUpdate;
    }

    public Date getPersDateUpdate() {
        return persDateUpdate;
    }

    public void setPersDateUpdate(Date persDateUpdate) {
        this.persDateUpdate = persDateUpdate;
    }

    public String getPersDbaObservation() {
        return persDbaObservation;
    }

    public void setPersDbaObservation(String persDbaObservation) {
        this.persDbaObservation = persDbaObservation;
    }

    public Catalog getCataOrgId() {
        return cataOrgId;
    }

    public void setCataOrgId(Catalog cataOrgId) {
        this.cataOrgId = cataOrgId;
    }

    public Catalog getCataSexId() {
        return cataSexId;
    }

    public void setCataSexId(Catalog cataSexId) {
        this.cataSexId = cataSexId;
    }

    public Catalog getCataOriginId() {
        return cataOriginId;
    }

    public void setCataOriginId(Catalog cataOriginId) {
        this.cataOriginId = cataOriginId;
    }

    public Catalog getCataNationalityId() {
        return cataNationalityId;
    }

    public void setCataNationalityId(Catalog cataNationalityId) {
        this.cataNationalityId = cataNationalityId;
    }

    public Catalog getCataTypeActorId() {
        return cataTypeActorId;
    }

    public void setCataTypeActorId(Catalog cataTypeActorId) {
        this.cataTypeActorId = cataTypeActorId;
    }

    public List<Participant> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<Participant> participantList) {
        this.participantList = participantList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (persId != null ? persId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.persId == null && other.persId != null) || (this.persId != null && !this.persId.equals(other.persId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Person[ persId=" + persId + " ]";
    }
    
}
