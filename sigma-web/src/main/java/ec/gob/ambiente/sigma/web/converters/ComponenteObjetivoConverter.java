package ec.gob.ambiente.sigma.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.gob.ambiente.sigma.ejb.entidades.ProjectsSpecificObjective;

@ManagedBean(name="componenteObjetivoConverter")
@SessionScoped
public class ComponenteObjetivoConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        ProjectsSpecificObjective pso=new ProjectsSpecificObjective();
        try{
            if(!s.equals("null")){
            ProjectsSpecificObjective o=new ProjectsSpecificObjective(Integer.valueOf(s));
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
            if (o instanceof ProjectsSpecificObjective) {
            	ProjectsSpecificObjective pso= (ProjectsSpecificObjective)o;
                r = String.valueOf(pso.getPsobId());
            }else if (o instanceof String) {
               r = (String) o;
            }
        } catch (Exception e) {
           
        }
        return r;
        }
}
