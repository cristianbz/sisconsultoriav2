package ec.gob.ambiente.sigma.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.gob.ambiente.sigma.ejb.entidades.Measure;


@ManagedBean(name="medidaConverter")
@SessionScoped
public class MedidaConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Measure pso=new Measure();
        try{
            if(!s.equals("null")){
            	Measure o=new Measure(Integer.valueOf(s));
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
            if (o instanceof Measure) {
            	Measure m= (Measure)o;
                r = String.valueOf(m.getMeasId());
            }else if (o instanceof String) {
               r = (String) o;
            }
        } catch (Exception e) {
           
        }
        return r;
        }
}
