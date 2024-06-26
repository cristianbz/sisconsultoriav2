/**
@autor proamazonia [Christian Báez]  20 jun. 2022

**/
package ec.gob.ambiente.sigma.ejb.sis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoResumenAbordajeSalvaguarda {
	private String logoEscudo;
	private String logoMae;
	private String logoPieIzquierda;
	private String logoPieDerecha;
	private String fecha;
	private String anio;
	private String salvaguarda;
	private String socioImplementador;
	private String resumenSocioImplementador;
	private String proyecto;
	private String proyectoNombreCorto;
	private String tablaResumenSociosEstrategicos;
	private String tablaResumenSalvaguardas;
}

