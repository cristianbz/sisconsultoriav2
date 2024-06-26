package ec.gob.ambiente.sigma.ejb.sis.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: CatalogsType
 *
 */
@Entity
@Table(name = "catalogs_types", schema = "sis")
//@NamedQueries({
//	@NamedQuery(name = "pro_",query = "SELECT CT FROM CatalogTypes CT WHERE CT.catyStatus")
//		
//})
public class CatalogTypes {
	
	@Getter
	@Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "caty_id")
    private Integer catyId;
	
	@Getter
	@Setter
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "caty_mnemonic")
    private String catyMnemonic;
    
	@Getter
	@Setter
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "caty_description")
    private String catyDescription;
    
	@Getter
	@Setter
	@Column(name = "caty_status")
    private Boolean catyStatus;
	
    public CatalogTypes() {
    }
    public CatalogTypes(Integer catyId) {
        this.catyId = catyId;
    }
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "catalogTypes")
    private List<Catalogs> catalogsList;
}
