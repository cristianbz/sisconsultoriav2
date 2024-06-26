package ec.gob.ambiente.sigma.web.utils;



public enum OpcionesRegistro {
	
	SIGUIENTE("SIG"),
	;
	
	private String codigo;

    private OpcionesRegistro(String codigo) {            
       this.codigo=codigo;
    }

	public String getCodigo() {
		return codigo;
	}

    
    
}
