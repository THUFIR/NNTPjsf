package net.bounceme.dur.nntp;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.mail.Message;

@FacesConverter("messageConverter")
public class MessageConverter implements Converter{

    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return nntp.getMessage(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Message message = (Message) value;
        return String.valueOf(message.getMessageNumber());
    }

}
