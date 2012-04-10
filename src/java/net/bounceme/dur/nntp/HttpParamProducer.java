package net.bounceme.dur.nntp;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class HttpParamProducer {

    @Inject
    FacesContext facesContext;

    @Produces
    @HttpParam
    String getHttpParameter(InjectionPoint ip) {
        String name = ip.getAnnotated().getAnnotation(HttpParam.class).value();
        if ("".equals(name)) {
            name = ip.getMember().getName();
        }
        FacesContextProducer fcp = new FacesContextProducer();
        facesContext = fcp.getFacesContext();
        return facesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }
}
