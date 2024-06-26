/**
@autor proamazonia [Christian Báez]  16 jun. 2022

**/
package ec.gob.ambiente.sigma.web.sis.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ec.gob.ambiente.sigma.ejb.entidades.Project;
import ec.gob.ambiente.sigma.ejb.entidades.Safeguard;
import ec.gob.ambiente.sigma.ejb.sis.entidades.AdvanceSummary;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class ReportesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String anioReporte;	
	private Integer anio;
	private Integer tipoCargaInformacion;
	private List<Safeguard> listaSalvaguardas;
	private List<AdvanceSummary> listaResumenAvance;
	private Safeguard salvaguardaSeleccionada;
	private List<Project> listaProyectos;
	private Project proyectoSeleccionado;
}

