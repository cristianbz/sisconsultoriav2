package ec.gob.ambiente.sigma.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.gob.ambiente.sigma.ejb.entidades.Component;

@ManagedBean(name="componenteConverter")
@SessionScoped
public class ComponenteConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Component pso=new Component();
        try{
            if(!s.equals("null")){
            	Component o=new Component(Integer.valueOf(s));
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
            if (o instanceof Component) {
            	Component com= (Component)o;
                r = String.valueOf(com.getCompId());
            }else if (o instanceof String) {
               r = (String) o;
            }
        } catch (Exception e) {
           
        }
        return r;
        }
}
