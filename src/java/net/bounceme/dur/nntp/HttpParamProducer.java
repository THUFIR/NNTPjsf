package net.bounceme.dur.nntp;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class HttpParamProducer {

    @Inject
    private FacesContext facesContext;

    @Produces
    @HttpParam
    String getHttpParameter(InjectionPoint ip) {
        String name = ip.getAnnotated().getAnnotation(HttpParam.class).value();
        if ("".equals(name)) name = ip.getMember().getName();
        return getFacesContext().getExternalContext().getRequestParameterMap().get(name);
    }
}
