package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;

@Named
public class Detail {

    private static final Logger logger = Logger.getLogger(Detail.class.getName());
    private static final Level level = Level.INFO;
    String id = "1529";
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    public Detail() {
        /*
         * MessageConverter mc = new MessageConverter(); FacesContext fc =
         * FacesContext.getCurrentInstance(); String value = String.valueOf(id);
         * UIComponent ui = null; Object obj = mc.getAsObject(fc, ui, value);
         * message = (Message) obj;
         */
    }

    public String forward() {
        logger.log(level, "Detail.forward..{0}", id);
        int i = Integer.parseInt(id);
        i = i + 1;
        id = String.valueOf(i);
        logger.log(level, "..Detail.forward {0}", id);
        return id;
    }

    public String back() {
        logger.log(level, "Detail.back..{0}", id);
        int i = Integer.parseInt(id);
        i = i - 1;
        id = String.valueOf(i);
        logger.log(level, "..Detail.back {0}", id);
        return id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getContent() throws Exception {
        return message.getContent().toString();
    }
}
