package ec.gob.ambiente.sigma.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.gob.ambiente.sigma.ejb.entidades.Action;




@ManagedBean(name="accionConverter")
@SessionScoped
public class AccionConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Action pso=new Action();
        try{
            if(!s.equals("null")){
            	Action o=new Action(Integer.valueOf(s));
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
            if (o instanceof Action) {
            	Action a = (Action)o;
                r = String.valueOf(a.getActiId());
            }else if (o instanceof String) {
               r = (String) o;
            }
        } catch (Exception e) {
           
        }
        return r;
        }
}
