/**
@autor proamazonia [Christian Báez]  3 jun. 2022

**/
package ec.gob.ambiente.sigma.ejb.sis.entidades;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import ec.gob.ambiente.sigma.ejb.entidades.Safeguard;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "advance_summary", schema = "sis")
@NamedQueries({
//	@NamedQuery(name = "ASU",query = "SELECT ASU FROM AdvanceSummary ASU WHERE ASU.advanceExecutionSafeguards.adexTermFrom =:x AND ASU.safeguards.safeCode=:C ORDER BY ASU.advanceExecutionSafeguards.projects.projShortName"),
//	@NamedQuery(name = "ASU2",query = "SELECT ASU FROM AdvanceSummary ASU WHERE ASU.advanceExecutionSafeguards.projects.projId =:x AND ASU.advanceExecutionSafeguards.adexTermFrom =:desde ORDER BY ASU.safeguards.safeCode")
})
public class AdvanceSummary {

	@Getter
	@Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adsu_id")
    private Integer adsuId;
	
	@Getter
	@Setter
	@Column(name = "adsu_status")
	private boolean adsuStatus;
	
	@Getter
	@Setter
	@Column(name = "adsu_advance")
	private String adsuAdvance;

	@Getter
	@Setter
	@Column(name = "adsu_creator_user")
	private String adsuCreatorUser;
	
	
	@Getter
	@Setter
	@Column(name = "adsu_creation_date")
	private Date adsuCreationDate;
	
	@Getter
	@Setter
	@Column(name = "adsu_update_user")
	private String adsuUpdateUser;
		
	@Getter
	@Setter
	@Column(name = "adsu_update_date")
	private Date adsuUpdateDate;

	@Getter
	@Setter
	@JoinColumn(name = "safe_id")
	@ManyToOne(fetch = FetchType.EAGER)	
	private Safeguard safeguards;

	@Getter
	@Setter
	@JoinColumn(name = "adex_id")
	@ManyToOne(fetch = FetchType.EAGER)	
	private AdvanceExecutionSafeguards advanceExecutionSafeguards;

}

