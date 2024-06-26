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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author proamazonia
 */
@Entity
@Table(name = "sigma.catalogs")
@NamedQueries({
    @NamedQuery(name = "Catalog.findAll", query = "SELECT c FROM Catalog c")})
public class Catalog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cata_id")
    private Integer cataId;
    @Column(name = "cata_order")
    private Integer cataOrder;
    @Size(max = 100)
    @Column(name = "cata_text1")
    private String cataText1;
    @Size(max = 2147483647)
    @Column(name = "cata_text2")
    private String cataText2;
    @Column(name = "cata_number")
    private Integer cataNumber;
    @Column(name = "cata_level")
    private Integer cataLevel;
    @Column(name = "cata_status")
    private Boolean cataStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cata_creator_user")
    private String cataCreatorUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cata_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cataCreationDate;
    @Size(max = 255)
    @Column(name = "cata_user_update")
    private String cataUserUpdate;
    @Column(name = "acpl_date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acplDateUpdate;
    @Size(max = 1024)
    @Column(name = "cata_dba_observation")
    private String cataDbaObservation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cataId")
    private List<FundingSourcesOption> fundingSourcesOptionList;
   
    @JoinColumn(name = "cata_parent_id", referencedColumnName = "cata_id")
    @ManyToOne
    private Catalog cataParentId;
    @JoinColumn(name = "caty_id", referencedColumnName = "caty_id")
    @ManyToOne(optional = false)
    private CatalogsType catyId;
    @Transient
    private String tipoEjeTem;
    

    public Catalog() {
    }

    public Catalog(Integer cataId) {
        this.cataId = cataId;
    }

    public Catalog(Integer cataId, String cataText1, String cataText2, String cataCreatorUser, Date cataCreationDate) {
        this.cataId = cataId;
        this.cataText1 = cataText1;
        this.cataText2 = cataText2;
        this.cataCreatorUser = cataCreatorUser;
        this.cataCreationDate = cataCreationDate;
    }

    public Integer getCataId() {
        return cataId;
    }

    public void setCataId(Integer cataId) {
        this.cataId = cataId;
    }

    public Integer getCataOrder() {
        return cataOrder;
    }

    public void setCataOrder(Integer cataOrder) {
        this.cataOrder = cataOrder;
    }

    public String getCataText1() {
        return cataText1;
    }

    public void setCataText1(String cataText1) {
        this.cataText1 = cataText1;
    }

    public String getCataText2() {
        return cataText2;
    }

    public void setCataText2(String cataText2) {
        this.cataText2 = cataText2;
    }

    public Integer getCataNumber() {
        return cataNumber;
    }

    public void setCataNumber(Integer cataNumber) {
        this.cataNumber = cataNumber;
    }

    public Integer getCataLevel() {
        return cataLevel;
    }

    public void setCataLevel(Integer cataLevel) {
        this.cataLevel = cataLevel;
    }

    public Boolean getCataStatus() {
        return cataStatus;
    }

    public void setCataStatus(Boolean cataStatus) {
        this.cataStatus = cataStatus;
    }

    public String getCataCreatorUser() {
        return cataCreatorUser;
    }

    public void setCataCreatorUser(String cataCreatorUser) {
        this.cataCreatorUser = cataCreatorUser;
    }

    public Date getCataCreationDate() {
        return cataCreationDate;
    }

    public void setCataCreationDate(Date cataCreationDate) {
        this.cataCreationDate = cataCreationDate;
    }

    public String getCataUserUpdate() {
        return cataUserUpdate;
    }

    public void setCataUserUpdate(String cataUserUpdate) {
        this.cataUserUpdate = cataUserUpdate;
    }

    public Date getAcplDateUpdate() {
        return acplDateUpdate;
    }

    public void setAcplDateUpdate(Date acplDateUpdate) {
        this.acplDateUpdate = acplDateUpdate;
    }

    public String getCataDbaObservation() {
        return cataDbaObservation;
    }

    public void setCataDbaObservation(String cataDbaObservation) {
        this.cataDbaObservation = cataDbaObservation;
    }

    public List<FundingSourcesOption> getFundingSourcesOptionList() {
        return fundingSourcesOptionList;
    }

    public void setFundingSourcesOptionList(List<FundingSourcesOption> fundingSourcesOptionList) {
        this.fundingSourcesOptionList = fundingSourcesOptionList;
    }

    

    public String getTipoEjeTem() {
    	if(cataText2!=null){
    		if(cataText2.startsWith("#")){
    			tipoEjeTem="color";
    		}else{
    			tipoEjeTem="imagen";
    		}
    	}
		return tipoEjeTem;
	}

	public void setTipoEjeTem(String tipoEjeTem) {
		this.tipoEjeTem = tipoEjeTem;
	}

	public Catalog getCataParentId() {
        return cataParentId;
    }

    public void setCataParentId(Catalog cataParentId) {
        this.cataParentId = cataParentId;
    }

    public CatalogsType getCatyId() {
        return catyId;
    }

    public void setCatyId(CatalogsType catyId) {
        this.catyId = catyId;
    }

   

   

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cataId != null ? cataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalog)) {
            return false;
        }
        Catalog other = (Catalog) object;
        if ((this.cataId == null && other.cataId != null) || (this.cataId != null && !this.cataId.equals(other.cataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.ambiente.sigma.ejb.entidades.Catalog[ cataId=" + cataId + " ]";
    }
    
}
