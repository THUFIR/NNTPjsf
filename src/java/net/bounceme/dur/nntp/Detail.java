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
    private String id = "1671";
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    public Detail() {
        logger.log(level, "Detail..");
    }

    public String forward() {
        logger.log(level, "Detail.forward..{0}", getId());
        int i = Integer.parseInt(getId());
        i = i + 1;
        logger.log(level, "..Detail.forward {0}", getId());
        return String.valueOf(i);
    }

    public String back() {
        logger.log(level, "Detail.back..{0}", getId());
        int i = Integer.parseInt(getId());
        i = i - 1;
        logger.log(level, "..Detail.back {0}", getId());
        return String.valueOf(i);
    }

    public Message getMessage() {
        logger.log(level, "Detail.getMessage..");
        return message;
    }

    public void setMessage(Message message) {
        logger.log(level, "Detail.setMessage..");
        this.message = message;
    }

    public String getContent() throws Exception {
        logger.log(level, "Detail.getContent..");
        setId(getId());
        return message.getContent().toString();
    }

    public String getId() {
        logger.log(level, "Detail.getId..");
        return id;
    }

    public void setId(String id) {
        logger.log(level, "Detail.setId..{0}", getId());
        this.id = id;
        message = nntp.getMessage(Integer.parseInt(id));
        logger.log(level, "*Detail.setId..{0}", getId());
    }
}
