package ec.gob.ambiente.sigma.web.utils;

public enum ProjectGeoType {
	AREA_INTERVENCION("AI"),
	CONSERVACION("E1"),
	PRODUCCION_SOSTENIBLE("E2"),
	MANEJO_CONTROL_FORESTAL("E3"),
	RESTAURACION("E4"),
	;
	
	private String codigo;

    private ProjectGeoType(String codigo) {            
       this.codigo=codigo;
    }

	public String getCodigo() {
		return codigo;
	}
}
