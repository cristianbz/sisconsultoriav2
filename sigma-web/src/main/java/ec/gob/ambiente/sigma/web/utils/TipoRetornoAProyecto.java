package ec.gob.ambiente.sigma.web.utils;

public enum TipoRetornoAProyecto {
	DESDE_SOCIO_IMPLEMENTADOR("SOC_IMP"),
	DESDE_FUENTE_FINANCIAMIENTO("FUE_FIN"),
	DESDE_CONVENIO_IMPLEMENTACION("CON_IMP"),
	DESDE_ACUERDO_FINANCIAMIENTO("ACU_FIN"),
	DESDE_GEOVISOR("GEO"),
	;
	
	private String codigo;

    private TipoRetornoAProyecto(String codigo) {            
       this.codigo=codigo;
    }

	public String getCodigo() {
		return codigo;
	}
}
