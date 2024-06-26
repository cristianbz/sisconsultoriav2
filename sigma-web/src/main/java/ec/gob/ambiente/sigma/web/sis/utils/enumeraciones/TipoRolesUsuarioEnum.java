/**
@autor proamazonia [Christian Báez]  29 jul. 2021

**/
package ec.gob.ambiente.sigma.web.sis.utils.enumeraciones;

import lombok.Getter;

public enum TipoRolesUsuarioEnum {
	SIS_Administrador(1,"SIS_Administrador"),
	SIS_socio_implementador(2,"REDD+_SOCIO_IMPLEMENTADOR"),
	SIS_socio_estrategico(3,"SIS_socio_estrategico"),
	SIS_tecnico(4,"SIS_tecnico");
	@Getter
	private final int codigo;

	@Getter
	private final String etiqueta;
	
	private TipoRolesUsuarioEnum(int codigo, String etiqueta){
		this.codigo = codigo;
		this.etiqueta = etiqueta;
	}
}

