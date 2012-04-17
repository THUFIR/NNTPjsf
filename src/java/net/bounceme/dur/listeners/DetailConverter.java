package net.bounceme.dur.listeners;

import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import net.bounceme.dur.nntp.SingletonNNTP;

@FacesConverter("detailConverter")
public class DetailConverter implements Converter {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(DetailConverter.class.getName());
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       throw new UnsupportedOperationException("get as object Not supported yet.");

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        throw new UnsupportedOperationException("get as string Not supported yet.");
    }



}