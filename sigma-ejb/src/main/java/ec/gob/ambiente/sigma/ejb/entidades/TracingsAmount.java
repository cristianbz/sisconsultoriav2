/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.tracings_amounts")
@NamedQueries({
    @NamedQuery(name = "TracingsAmount.findAll", query = "SELECT t FROM TracingsAmount t")})
public class TracingsAmount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tram_id")
    private Integer tramId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tram_assigned_amount")
    private BigDecimal tramAssignedAmount;
    @Column(name = "tram_executed_amount")
    private BigDecimal tramExecutedAmount;
    @Column(name = "tram_executed_percent")
    private Integer tramExecutedPercent;
    @Column(name = "tram_administrative")
    private BigDecimal tramAdministrative;
    @Column(name = "tram_operative")
    private BigDecimal tramOperative;
    @Column(name = "tram_year1")
    private BigDecimal tramYear1;
    @Column(name = "tram_year2")
    private BigDecimal tramYear2;
    @Column(name = "tram_year3")
    private BigDecimal tramYear3;
    @Column(name = "tram_year4")
    private BigDecimal tramYear4;
    @Column(name = "tram_year5")
    private BigDecimal tramYear5;
    @Column(name = "tram_year6")
    private BigDecimal tramYear6;
    @Column(name = "tram_year7")
    private BigDecimal tramYear7;
    @Column(name = "tram_year8")
    private BigDecimal tramYear8;
    @Column(name = "tram_year9")
    private BigDecimal tramYear9;
    @Column(name = "tram_year10")
    private BigDecimal tramYear10;
    @Column(name = "tram_total")
    private BigDecimal tramTotal;
    @Column(name = "tram_status")
    private Boolean tramStatus;
    @JoinColumn(name = "trac_id", referencedColumnName = "trac_id")
    @ManyToOne(optional = false)
    private Tracing tracId;
    @JoinColumn(name = "psob_id", referencedColumnName = "psob_id")
    @ManyToOne
    private ProjectsSpecificObjective psobId;
    @JoinColumn(name = "cata_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataId;
    @Transient
    private List<TracingsAmount> listaPorCategorias;
    

    public TracingsAmount() {
    	tramStatus=true;
    }

    public TracingsAmount(Integer tramId) {
        this.tramId = tramId;
    }

    public TracingsAmount(Integer tramId, BigDecimal tramAssignedAmount) {
        this.tramId = tramId;
        this.tramAssignedAmount = tramAssignedAmount;
    }

    public Integer getTramId() {
        return tramId;
    }

    public void setTramId(Integer tramId) {
        this.tramId = tramId;
    }

   

    public BigDecimal getTramAssignedAmount() {
        return tramAssignedAmount;
    }

    public void setTramAssignedAmount(BigDecimal tramAssignedAmount) {
        this.tramAssignedAmount = tramAssignedAmount;
    }

    public BigDecimal getTramExecutedAmount() {
        return tramExecutedAmount;
    }

    public void setTramExecutedAmount(BigDecimal tramExecutedAmount) {
        this.tramExecutedAmount = tramExecutedAmount;
    }

    public Integer getTramExecutedPercent() {
        return tramExecutedPercent;
    }

    public void setTramExecutedPercent(Integer tramExecutedPercent) {
        this.tramExecutedPercent = tramExecutedPercent;
    }

   

    public BigDecimal getTramAdministrative() {
		return tramAdministrative;
	}

	public void setTramAdministrative(BigDecimal tramAdministrative) {
		this.tramAdministrative = tramAdministrative;
	}

	public BigDecimal getTramOperative() {
		return tramOperative;
	}

	public void setTramOperative(BigDecimal tramOperative) {
		this.tramOperative = tramOperative;
	}

	public BigDecimal getTramYear1() {
		return tramYear1;
	}

	public void setTramYear1(BigDecimal tramYear1) {
		this.tramYear1 = tramYear1;
	}

	public BigDecimal getTramYear2() {
		return tramYear2;
	}

	public void setTramYear2(BigDecimal tramYear2) {
		this.tramYear2 = tramYear2;
	}

	public BigDecimal getTramYear3() {
		return tramYear3;
	}

	public void setTramYear3(BigDecimal tramYear3) {
		this.tramYear3 = tramYear3;
	}

	public BigDecimal getTramYear4() {
		return tramYear4;
	}

	public void setTramYear4(BigDecimal tramYear4) {
		this.tramYear4 = tramYear4;
	}

	public BigDecimal getTramYear5() {
		return tramYear5;
	}

	public void setTramYear5(BigDecimal tramYear5) {
		this.tramYear5 = tramYear5;
	}
	
	

	public BigDecimal getTramYear6() {
		return tramYear6;
	}

	public void setTramYear6(BigDecimal tramYear6) {
		this.tramYear6 = tramYear6;
	}

	public BigDecimal getTramYear7() {
		return tramYear7;
	}

	public void setTramYear7(BigDecimal tramYear7) {
		this.tramYear7 = tramYear7;
	}

	public BigDecimal getTramYear8() {
		return tramYear8;
	}

	public void setTramYear8(BigDecimal tramYear8) {
		this.tramYear8 = tramYear8;
	}

	public BigDecimal getTramYear9() {
		return tramYear9;
	}

	public void setTramYear9(BigDecimal tramYear9) {
		this.tramYear9 = tramYear9;
	}

	public BigDecimal getTramYear10() {
		return tramYear10;
	}

	public void setTramYear10(BigDecimal tramYear10) {
		this.tramYear10 = tramYear10;
	}

	public BigDecimal getTramTotal() {
		return tramTotal;
	}

	public void setTramTotal(BigDecimal tramTotal) {
		this.tramTotal = tramTotal;
	}

	public ProjectsSpecificObjective getPsobId() {
		return psobId;
	}

	public void setPsobId(ProjectsSpecificObjective psobId) {
		this.psobId = psobId;
	}

	public Catalog getCataId() {
		return cataId;
	}

	public void setCataId(Catalog cataId) {
		this.cataId = cataId;
	}


	public Boolean getTramStatus() {
        return tramStatus;
    }

    public void setTramStatus(Boolean tramStatus) {
        this.tramStatus = tramStatus;
    }

    

    public Tracing getTracId() {
        return tracId;
    }

    public void setTracId(Tracing tracId) {
        this.tracId = tracId;
    }
    
    

    public List<TracingsAmount> getListaPorCategorias() {
		return listaPorCategorias;
	}

	public void setListaPorCategorias(List<TracingsAmount> listaPorCategorias) {
		this.listaPorCategorias = listaPorCategorias;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (tramId != null ? tramId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TracingsAmount)) {
            return false;
        }
        TracingsAmount other = (TracingsAmount) object;
        if ((this.tramId == null && other.tramId != null) || (this.tramId != null && !this.tramId.equals(other.tramId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.TracingsAmount[ tramId=" + tramId + " ]";
    }
    
}
