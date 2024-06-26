/**
@autor proamazonia [Christian Báez]  1 dic. 2022

**/
package ec.gob.ambiente.sigma.web.sis.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.sis.entidades.Documentslog;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class BitacoradocumentosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int tipoBusqueda;
	private int tipoOficio;
	private boolean habilitaPorFecha;
	private boolean habilitaPorProyecto;
	private boolean filtroPorProyecto;
	private boolean filtroPorAsunto;
	private boolean filtroPorOtro;
	private boolean filtroNroOficio;
	private boolean filtroRemitente;
//	private boolean filtroAsunto;
	private boolean filtroInstitucion;
	private Date fechaHasta;
	private Date fechaDesde;
	private boolean habilitaPorDestinatario;
	private List<Project> listaProyectos;
	private List<Documentslog> listaBitacoras;
	private Project proyectoSeleccionado;
	private Documentslog bitacoraDocumentos;
	private String valorFiltroOtro;
	private String valorFiltroNroOficio;
	private String valorFiltroRemitente;
	private String valorFiltroAsunto;
	private String valorFiltroInstitucion;
	private List<String> listadoPDIProgramas;
	private List<String> listadoPDIProgramasSeleccionados;
	private List<String> listadoPDIProgramasSeleccionadosAux;
	private String pdiSeleccionado;
}

