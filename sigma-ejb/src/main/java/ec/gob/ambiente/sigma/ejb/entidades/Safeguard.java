/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.sigma.ejb.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.safeguards")
@NamedQueries({
    @NamedQuery(name = "Safeguard.findAll", query = "SELECT s FROM Safeguard s")})
public class Safeguard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "safe_id")
    private Integer safeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "safe_description")
    private String safeDescription;
    @Column(name = "safe_order")
    private Integer safeOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "safe_level")
    private int safeLevel;
    @Size(max = 5)
    @Column(name = "safe_code")
    private String safeCode;
    @Size(max = 100)
    @Column(name = "safe_title")
    private String safeTitle;
    @Column(name = "safe_status")
    private Boolean safeStatus;
   
    @JoinColumn(name = "acpl_id", referencedColumnName = "acpl_id")
    @ManyToOne(optional = false)
    private ActionPlan acplId;
    
    @JoinColumn(name = "safe_parent_id", referencedColumnName = "safe_id")
    @ManyToOne
    private Safeguard safeParentId;
    

    public Safeguard() {
    }

    public Safeguard(Integer safeId) {
        this.safeId = safeId;
    }

    public Safeguard(Integer safeId, String safeDescription, int safeLevel) {
        this.safeId = safeId;
        this.safeDescription = safeDescription;
        this.safeLevel = safeLevel;
    }

    public Integer getSafeId() {
        return safeId;
    }

    public void setSafeId(Integer safeId) {
        this.safeId = safeId;
    }

    public String getSafeDescription() {
        return safeDescription;
    }

    public void setSafeDescription(String safeDescription) {
        this.safeDescription = safeDescription;
    }

    public Integer getSafeOrder() {
        return safeOrder;
    }

    public void setSafeOrder(Integer safeOrder) {
        this.safeOrder = safeOrder;
    }

    public int getSafeLevel() {
        return safeLevel;
    }

    public void setSafeLevel(int safeLevel) {
        this.safeLevel = safeLevel;
    }

    public Boolean getSafeStatus() {
        return safeStatus;
    }

    public void setSafeStatus(Boolean safeStatus) {
        this.safeStatus = safeStatus;
    }

    

    public ActionPlan getAcplId() {
        return acplId;
    }

    public void setAcplId(ActionPlan acplId) {
        this.acplId = acplId;
    }

   

    public Safeguard getSafeParentId() {
        return safeParentId;
    }

    public void setSafeParentId(Safeguard safeParentId) {
        this.safeParentId = safeParentId;
    }

    public String getSafeCodeLowerCase(){
    	return safeCode.toLowerCase();
    }

    
    
    public String getSafeCode() {
		return safeCode;
	}

	public void setSafeCode(String safeCode) {
		this.safeCode = safeCode;
	}
	
	

	public String getSafeTitle() {
		return safeTitle;
	}

	public void setSafeTitle(String safeTitle) {
		this.safeTitle = safeTitle;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (safeId != null ? safeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Safeguard)) {
            return false;
        }
        Safeguard other = (Safeguard) object;
        if ((this.safeId == null && other.safeId != null) || (this.safeId != null && !this.safeId.equals(other.safeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Safeguard[ safeId=" + safeId + " ]";
    }
    
}
