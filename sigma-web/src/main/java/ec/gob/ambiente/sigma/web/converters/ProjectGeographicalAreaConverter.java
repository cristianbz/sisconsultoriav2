package ec.gob.ambiente.sigma.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.gob.ambiente.sigma.ejb.entidades.ProjectsGeographicalArea;
import ec.gob.ambiente.sigma.ejb.entidades.ProjectsSpecificObjective;

@ManagedBean(name="projectGeographicalAreaConverter")
@SessionScoped
public class ProjectGeographicalAreaConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        ProjectsGeographicalArea pso=new ProjectsGeographicalArea();
        try{
            if(!s.equals("null")){
            	ProjectsGeographicalArea o=new ProjectsGeographicalArea(Integer.valueOf(s));
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
            if (o instanceof ProjectsGeographicalArea) {
            	ProjectsGeographicalArea pso= (ProjectsGeographicalArea)o;
                r = String.valueOf(pso.getPgarId());
            }else if (o instanceof String) {
               r = (String) o;
            }
        } catch (Exception e) {
           
        }
        return r;
        }
}
