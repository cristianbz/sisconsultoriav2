package ec.gob.ambiente.sigma.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


import ec.gob.ambiente.sigma.ejb.entidades.Catalog;




@ManagedBean(name="catalogoConverter")
@SessionScoped
public class CatalogoConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Catalog pso=new Catalog();
        try{
            if(!s.equals("null")||s.equals(null)){
            	Catalog o=new Catalog(Integer.valueOf(s));
            pso=o;
            }
          } catch (Exception e) {
            
          }
          return pso;
      }


    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String r = "";
        try {
            if (o instanceof Catalog) {
            	Catalog a = (Catalog)o;
                r = String.valueOf(a.getCataId());
            }else if (o instanceof String) {
               r = (String) o;
            }else{
            	r=null;
            }
        } catch (Exception e) {
        	
        }
        return r;
        }
}
