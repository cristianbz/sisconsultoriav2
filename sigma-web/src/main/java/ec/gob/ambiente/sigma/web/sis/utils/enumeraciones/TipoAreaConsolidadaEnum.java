/**
@autor proamazonia [Christian Báez]  15 abr. 2021

**/
package ec.gob.ambiente.sigma.web.sis.utils.enumeraciones;

import lombok.Getter;

public enum TipoAreaConsolidadaEnum {
	CONSERVACION(1,"Conservación"),
	RESTAURACION(2,"Restauración"),
	PRODUCCIONSOSTENIBLE(3,"Producción Sostenible"),
	CONECTIVIDADDEAREAS(4,"Conectividad de Areas");

	@Getter
	private final int codigo;

	@Getter
	private final String etiqueta;
	
	private TipoAreaConsolidadaEnum(int codigo,String etiqueta){
		this.codigo=codigo;
		this.etiqueta=etiqueta;
	}

	public static TipoAreaConsolidadaEnum[] listaValores() {
		return values();
	}
}

