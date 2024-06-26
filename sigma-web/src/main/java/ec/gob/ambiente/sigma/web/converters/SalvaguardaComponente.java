package ec.gob.ambiente.sigma.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.gob.ambiente.sigma.ejb.entidades.ProjectsSpecificObjective;
import ec.gob.ambiente.sigma.ejb.entidades.Safeguard;

@ManagedBean(name="salvaguardaConverter")
@SessionScoped
public class SalvaguardaComponente implements Converter {

	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Safeguard pso=new Safeguard();
        try{
            if(!s.equals("null")){
            	Safeguard o=new Safeguard(Integer.valueOf(s));
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
            if (o instanceof Safeguard) {
            	Safeguard pso= (Safeguard)o;
                r = String.valueOf(pso.getSafeId());
            }else if (o instanceof String) {
               r = (String) o;
            }
        } catch (Exception e) {
           
        }
        return r;
        }
}
