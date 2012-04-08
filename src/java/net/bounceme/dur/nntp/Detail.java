package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.Message;

@Named
public class Detail {

    private static final Logger logger = Logger.getLogger(Detail.class.getName());
    private static final Level level = Level.INFO;
    private int id = 0;
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    public Detail() {
        MessageConverter mc = new MessageConverter();
        FacesContext fc = FacesContext.getCurrentInstance();
        String value = String.valueOf(id);
        UIComponent ui = null;
        Object obj = mc.getAsObject(fc, ui, value);
        message = (Message) obj;
    }

    public int forward() {
        logger.log(level, "Detail.forward..{0}", id);
        id = id + 1;
        logger.log(level, "..Detail.forward {0}", id);
        return id;
    }

    public int back() {
        logger.log(level, "Detail.back..{0}", id);
        id = id - 1;
        logger.log(level, "..Detail.back {0}", id);
        return id;
    }

    public Message getMessage() {
        return message;
    }

    public String getContent() throws Exception {
        return message.getContent().toString();
    }
}
