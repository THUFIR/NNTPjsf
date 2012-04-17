package net.bounceme.dur.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.mail.Message;
import net.bounceme.dur.nntp.SingletonNNTP;

@FacesConverter("detailConverter")
public class DetailConverter implements Converter {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(DetailConverter.class.getName());
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Message message = null;
        try {
            message = nntp.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(DetailConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Message message = null;
        try {
            message = nntp.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(DetailConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(message.getMessageNumber());
    }
}