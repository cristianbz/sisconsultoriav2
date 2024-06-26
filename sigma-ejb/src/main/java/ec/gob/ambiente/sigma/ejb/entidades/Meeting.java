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
@Table(name = "sigma.meetings")
@NamedQueries({
    @NamedQuery(name = "Meeting.findAll", query = "SELECT m FROM Meeting m")})
public class Meeting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "meet_id")
    private Integer meetId;
    @Size(max = 200)
    @Column(name = "meet_name")
    private String meetName;
    @Size(max = 2147483647)
    @Column(name = "meet_agenda")
    private String meetAgenda;
    @Size(max = 200)
    @Column(name = "meet_purpose")
    private String meetPurpose;
    @Size(max = 50)
    @Column(name = "meet_community")
    private String meetCommunity;
    @Size(max = 50)
    @Column(name = "meet_place")
    private String meetPlace;
    @Column(name = "meet_date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date meetDateStart;
    @Column(name = "meet_date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date meetDateEnd;
    @Column(name = "meet_duration")
    private Integer meetDuration;
    @Size(max = 100)
    @Column(name = "meet_responsable")
    private String meetResponsable;
    @Column(name = "meet_days")
    private Integer meetDays;
    @Column(name = "meet_participants_number")
    private Integer meetParticipantsNumber;
    @Size(max = 2147483647)
    @Column(name = "meet_result")
    private String meetResult;
    @Size(max = 2147483647)
    @Column(name = "meet_agreements")
    private String meetAgreements;
    @Size(max = 2147483647)
    @Column(name = "meet_observations")
    private String meetObservations;
    @Size(max = 2147483647)
    @Column(name = "meet_links")
    private String meetLinks;
    @Size(max = 50)
    @Column(name = "meet_other_typeorg")
    private String meetOtherTypeorg;
    @Size(max = 100)
    @Column(name = "meet_donor")
    private String meetDonor;
    @Column(name = "meet_translate")
    private Boolean meetTranslate;
    @Size(max = 50)
    @Column(name = "meet_translate_details")
    private String meetTranslateDetails;
    @Column(name = "meet_participation_cert")
    private Boolean meetParticipationCert;
    @Column(name = "meet_academic_cert")
    private Boolean meetAcademicCert;
    @Column(name = "meet_status")
    private Boolean meetStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "meet_creator_user")
    private String meetCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "meet_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date meetCreationDate;
    @Size(max = 255)
    @Column(name = "meet_user_update")
    private String meetUserUpdate;
    @Column(name = "meet_date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date meetDateUpdate;
    @Size(max = 1024)
    @Column(name = "meet_dba_observation")
    private String meetDbaObservation;
    @JoinColumn(name = "cata_type_meeting_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataTypeMeetingId;
    @JoinColumn(name = "cata_training_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataTrainingId;
    @JoinColumn(name = "cata_language_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataLanguageId;
    @JoinColumn(name = "cata_type_org_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataTypeOrgId;
    @JoinColumn(name = "proj_id", referencedColumnName = "proj_id")
    @ManyToOne
    private Project projId;
    @JoinColumn(name = "gelo_id", referencedColumnName = "gelo_id")
    @ManyToOne
    private GeographicalLocations geloId;
    @Size(max = 1)
    @Column(name = "meet_type_form")
    private String meetTypeForm;
    @Column(name = "meet_total_male")
    private Integer meetTotalMale;
    @Column(name = "meet_total_female")
    private Integer meetTotalFemale;
    @Column(name = "meet_total_age18to25")
    private Integer meetTotalAge18to25;
    @Column(name = "meet_total_age26to35")
    private Integer meetTotalAge26to35;
    @Column(name = "meet_total_age36to45")
    private Integer meetTotalAge36to45;
    @Column(name = "meet_total_age46to55")
    private Integer meetTotalAge46to55;
    @Column(name = "meet_total_age56to64")
    private Integer meetTotalAge56to64;
    @Column(name = "meet_total_age65andmore")
    private Integer meetTotalAge65andmore;
    @Column(name = "meet_total_indigenous")
    private Integer meetTotalIndigenous;

    public Meeting() {
    	meetStatus=true;
    }

    public Meeting(Integer meetId) {
        this.meetId = meetId;
    }

    public Meeting(Integer meetId,  String meetName, String meetCreatorUser, Date meetCreationDate) {
        this.meetId = meetId;
        this.meetName = meetName;
        this.meetCreatorUser = meetCreatorUser;
        this.meetCreationDate = meetCreationDate;
    }

    public Integer getMeetId() {
        return meetId;
    }

    public void setMeetId(Integer meetId) {
        this.meetId = meetId;
    }

   

    public GeographicalLocations getGeloId() {
		return geloId;
	}

	public void setGeloId(GeographicalLocations geloId) {
		this.geloId = geloId;
	}

	public String getMeetName() {
        return meetName;
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }

    public String getMeetAgenda() {
        return meetAgenda;
    }

    public void setMeetAgenda(String meetAgenda) {
        this.meetAgenda = meetAgenda;
    }

    public String getMeetPurpose() {
        return meetPurpose;
    }

    public void setMeetPurpose(String meetPurpose) {
        this.meetPurpose = meetPurpose;
    }

    public String getMeetCommunity() {
        return meetCommunity;
    }

    public void setMeetCommunity(String meetCommunity) {
        this.meetCommunity = meetCommunity;
    }

    public String getMeetPlace() {
        return meetPlace;
    }

    public void setMeetPlace(String meetPlace) {
        this.meetPlace = meetPlace;
    }

    public Date getMeetDateStart() {
        return meetDateStart;
    }

    public void setMeetDateStart(Date meetDateStart) {
        this.meetDateStart = meetDateStart;
    }

    public Date getMeetDateEnd() {
        return meetDateEnd;
    }

    public void setMeetDateEnd(Date meetDateEnd) {
        this.meetDateEnd = meetDateEnd;
    }

    public Integer getMeetDuration() {
        return meetDuration;
    }

    public void setMeetDuration(Integer meetDuration) {
        this.meetDuration = meetDuration;
    }

    public String getMeetResponsable() {
        return meetResponsable;
    }

    public void setMeetResponsable(String meetResponsable) {
        this.meetResponsable = meetResponsable;
    }

    public Integer getMeetDays() {
        return meetDays;
    }

    public void setMeetDays(Integer meetDays) {
        this.meetDays = meetDays;
    }

    public Integer getMeetParticipantsNumber() {
        return meetParticipantsNumber;
    }

    public void setMeetParticipantsNumber(Integer meetParticipantsNumber) {
        this.meetParticipantsNumber = meetParticipantsNumber;
    }

    public String getMeetResult() {
        return meetResult;
    }

    public void setMeetResult(String meetResult) {
        this.meetResult = meetResult;
    }

    public String getMeetAgreements() {
        return meetAgreements;
    }

    public void setMeetAgreements(String meetAgreements) {
        this.meetAgreements = meetAgreements;
    }

    public String getMeetObservations() {
        return meetObservations;
    }

    public void setMeetObservations(String meetObservations) {
        this.meetObservations = meetObservations;
    }

    public String getMeetLinks() {
        return meetLinks;
    }

    public void setMeetLinks(String meetLinks) {
        this.meetLinks = meetLinks;
    }

    public String getMeetOtherTypeorg() {
        return meetOtherTypeorg;
    }

    public void setMeetOtherTypeorg(String meetOtherTypeorg) {
        this.meetOtherTypeorg = meetOtherTypeorg;
    }

    public String getMeetDonor() {
        return meetDonor;
    }

    public void setMeetDonor(String meetDonor) {
        this.meetDonor = meetDonor;
    }

    public Boolean getMeetTranslate() {
        return meetTranslate;
    }

    public void setMeetTranslate(Boolean meetTranslate) {
        this.meetTranslate = meetTranslate;
    }

    public String getMeetTranslateDetails() {
        return meetTranslateDetails;
    }

    public void setMeetTranslateDetails(String meetTranslateDetails) {
        this.meetTranslateDetails = meetTranslateDetails;
    }

    public Boolean getMeetParticipationCert() {
        return meetParticipationCert;
    }

    public void setMeetParticipationCert(Boolean meetParticipationCert) {
        this.meetParticipationCert = meetParticipationCert;
    }

    public Boolean getMeetAcademicCert() {
        return meetAcademicCert;
    }

    public void setMeetAcademicCert(Boolean meetAcademicCert) {
        this.meetAcademicCert = meetAcademicCert;
    }

    public Boolean getMeetStatus() {
        return meetStatus;
    }

    public void setMeetStatus(Boolean meetStatus) {
        this.meetStatus = meetStatus;
    }

    public String getMeetCreatorUser() {
        return meetCreatorUser;
    }

    public void setMeetCreatorUser(String meetCreatorUser) {
        this.meetCreatorUser = meetCreatorUser;
    }

    public Date getMeetCreationDate() {
        return meetCreationDate;
    }

    public void setMeetCreationDate(Date meetCreationDate) {
        this.meetCreationDate = meetCreationDate;
    }

    public String getMeetUserUpdate() {
        return meetUserUpdate;
    }

    public void setMeetUserUpdate(String meetUserUpdate) {
        this.meetUserUpdate = meetUserUpdate;
    }

    public Date getMeetDateUpdate() {
        return meetDateUpdate;
    }

    public void setMeetDateUpdate(Date meetDateUpdate) {
        this.meetDateUpdate = meetDateUpdate;
    }

    public String getMeetDbaObservation() {
        return meetDbaObservation;
    }

    public void setMeetDbaObservation(String meetDbaObservation) {
        this.meetDbaObservation = meetDbaObservation;
    }

    public Catalog getCataTypeMeetingId() {
        return cataTypeMeetingId;
    }

    public void setCataTypeMeetingId(Catalog cataTypeMeetingId) {
        this.cataTypeMeetingId = cataTypeMeetingId;
    }

    public Catalog getCataTrainingId() {
        return cataTrainingId;
    }

    public void setCataTrainingId(Catalog cataTrainingId) {
        this.cataTrainingId = cataTrainingId;
    }

    public Catalog getCataLanguageId() {
        return cataLanguageId;
    }

    public void setCataLanguageId(Catalog cataLanguageId) {
        this.cataLanguageId = cataLanguageId;
    }

    public Catalog getCataTypeOrgId() {
        return cataTypeOrgId;
    }

    public void setCataTypeOrgId(Catalog cataTypeOrgId) {
        this.cataTypeOrgId = cataTypeOrgId;
    }

    
    
    public Project getProjId() {
		return projId;
	}

	public void setProjId(Project projId) {
		this.projId = projId;
	}

	
	

    public String getMeetTypeForm() {
		return meetTypeForm;
	}

	public void setMeetTypeForm(String meetTypeForm) {
		this.meetTypeForm = meetTypeForm;
	}

	public Integer getMeetTotalMale() {
		return meetTotalMale;
	}

	public void setMeetTotalMale(Integer meetTotalMale) {
		this.meetTotalMale = meetTotalMale;
	}

	public Integer getMeetTotalFemale() {
		return meetTotalFemale;
	}

	public void setMeetTotalFemale(Integer meetTotalFemale) {
		this.meetTotalFemale = meetTotalFemale;
	}

	public Integer getMeetTotalAge18to25() {
		return meetTotalAge18to25;
	}

	public void setMeetTotalAge18to25(Integer meetTotalAge18to25) {
		this.meetTotalAge18to25 = meetTotalAge18to25;
	}

	public Integer getMeetTotalAge26to35() {
		return meetTotalAge26to35;
	}

	public void setMeetTotalAge26to35(Integer meetTotalAge26to35) {
		this.meetTotalAge26to35 = meetTotalAge26to35;
	}

	public Integer getMeetTotalAge36to45() {
		return meetTotalAge36to45;
	}

	public void setMeetTotalAge36to45(Integer meetTotalAge36to45) {
		this.meetTotalAge36to45 = meetTotalAge36to45;
	}

	public Integer getMeetTotalAge46to55() {
		return meetTotalAge46to55;
	}

	public void setMeetTotalAge46to55(Integer meetTotalAge46to55) {
		this.meetTotalAge46to55 = meetTotalAge46to55;
	}

	public Integer getMeetTotalAge56to64() {
		return meetTotalAge56to64;
	}

	public void setMeetTotalAge56to64(Integer meetTotalAge56to64) {
		this.meetTotalAge56to64 = meetTotalAge56to64;
	}

	public Integer getMeetTotalAge65andmore() {
		return meetTotalAge65andmore;
	}

	public void setMeetTotalAge65andmore(Integer meetTotalAge65andmore) {
		this.meetTotalAge65andmore = meetTotalAge65andmore;
	}

	public Integer getMeetTotalIndigenous() {
		return meetTotalIndigenous;
	}

	public void setMeetTotalIndigenous(Integer meetTotalIndigenous) {
		this.meetTotalIndigenous = meetTotalIndigenous;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (meetId != null ? meetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meeting)) {
            return false;
        }
        Meeting other = (Meeting) object;
        if ((this.meetId == null && other.meetId != null) || (this.meetId != null && !this.meetId.equals(other.meetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Meeting[ meetId=" + meetId + " ]";
    }
    
}
