package ec.gob.ambiente.sigma.web.sis.utils.enumeraciones;

import lombok.Getter;

public enum TipoRespuestaEnum {
	RADIOBUTTON(1,"Radio Button"),
	CHECKBOX(2,"Check Box"),
	TEXTO(3,"Texto"),
	TABLA(4,"Tabla"),
	LISTA(4,"Tabla"),
	SINRESPUESTA(6,"SinRespuesta");	
	@Getter
	private final int codigo;

	@Getter
	private final String etiqueta;
	
	private TipoRespuestaEnum(int codigo,String etiqueta){
		this.codigo=codigo;
		this.etiqueta=etiqueta;
	}

}
