package ec.gob.ambiente.sigma.ejb.sis.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: Sectors
 *
 */
@Entity
@Table(name = "sectors", schema = "sis")
@NamedQueries({
	@NamedQuery(name = Sectors.CARGA_SECTORES,query = "SELECT S FROM Sectors S WHERE S.sectStatus=true")	
})
public class Sectors implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final String CARGA_SECTORES="cargaSectores";
	

	@Getter
	@Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sect_id")
    private Integer sectId;
    
	@Getter
	@Setter
	@Column(name = "sect_name")
	private String sectName;
	
	@Getter
	@Setter
	@Column(name = "sect_status")
	private boolean sectStatus;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "sectors", fetch = FetchType.LAZY)
	private List<AdvanceSectors> advanceSectorsList;
}
