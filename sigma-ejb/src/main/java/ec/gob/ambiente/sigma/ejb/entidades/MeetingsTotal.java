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
@Table(name = "sigma.meetings_totals")
public class MeetingsTotal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "meto_id")
    private Integer metoId;
    @Column(name = "meto_number1")
    private Integer metoNumber1;
    @Column(name = "meto_number2")
    private Integer metoNumber2;
    @Column(name = "meto_number3")
    private Integer metoNumber3;
    @Column(name = "meto_total")
    private Integer metoTotal;
    @Size(max = 200)
    @Column(name = "meto_comments")
    private String metoComments;
    @Column(name = "meto_status")
    private Boolean metoStatus;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataId;
    @JoinColumn(name = "meet_id", referencedColumnName = "meet_id")
    @ManyToOne(optional = false)
    private Meeting meetId;

    public MeetingsTotal() {
    	metoStatus=true;
    }

    public MeetingsTotal(Integer metoId) {
        this.metoId = metoId;
    }


    
    public Integer getMetoId() {
		return metoId;
	}

	public void setMetoId(Integer metoId) {
		this.metoId = metoId;
	}

	public Integer getMetoNumber1() {
		return metoNumber1;
	}

	public void setMetoNumber1(Integer metoNumber1) {
		this.metoNumber1 = metoNumber1;
	}

	public Integer getMetoNumber2() {
		return metoNumber2;
	}

	public void setMetoNumber2(Integer metoNumber2) {
		this.metoNumber2 = metoNumber2;
	}

	public Integer getMetoNumber3() {
		return metoNumber3;
	}

	public void setMetoNumber3(Integer metoNumber3) {
		this.metoNumber3 = metoNumber3;
	}

	public Integer getMetoTotal() {
		return metoTotal;
	}

	public void setMetoTotal(Integer metoTotal) {
		this.metoTotal = metoTotal;
	}

	public String getMetoComments() {
		return metoComments;
	}

	public void setMetoComments(String metoComments) {
		this.metoComments = metoComments;
	}

	public Boolean getMetoStatus() {
		return metoStatus;
	}

	public void setMetoStatus(Boolean metoStatus) {
		this.metoStatus = metoStatus;
	}

	public Catalog getCataId() {
		return cataId;
	}

	public void setCataId(Catalog cataId) {
		this.cataId = cataId;
	}

	public Meeting getMeetId() {
		return meetId;
	}

	public void setMeetId(Meeting meetId) {
		this.meetId = meetId;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (metoId != null ? metoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeetingsTotal)) {
            return false;
        }
        MeetingsTotal other = (MeetingsTotal) object;
        if ((this.metoId == null && other.metoId != null) || (this.metoId != null && !this.metoId.equals(other.metoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.MeetingsTotal[ cataId=" + metoId + " ]";
    }
    
}
