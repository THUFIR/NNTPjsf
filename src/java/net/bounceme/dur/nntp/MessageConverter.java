package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.mail.Message;

@FacesConverter("messageConverter")
public class MessageConverter implements Converter {

    private static final Logger logger = Logger.getLogger(MessageConverter.class.getName());
    private static final Level level = Level.INFO;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        logger.log(level, "MessageConverter.getAsObject..{0}", value);
        Message message = null;
        try {
            message = nntp.getMessage(Integer.parseInt(value));
        } catch (Exception ex) {
            Logger.getLogger(MessageConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Message message = (Message) value;
        return String.valueOf(message.getMessageNumber());
    }
}
