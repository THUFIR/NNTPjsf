package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;

@Named
@RequestScoped
public class Detail {

    private static final Logger logger = Logger.getLogger(Detail.class.getName());
    private static final Level level = Level.INFO;
    @Inject
    @HttpParam
    private String id;
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    public Detail() {
        message = nntp.getMessage(Integer.parseInt(id));
    }

    public String forward() {
        logger.log(level, "Detail.forward.." + id);
        id = String.valueOf(Integer.parseInt(id) + 1);
        logger.log(level, "..Detail.forward " + id);
        return id;
    }

    public String back() {
        logger.log(level, "Detail.back.." + id);
        id = String.valueOf(Integer.parseInt(id) - 1);
        logger.log(level, "..Detail.back " + id);
        return id;
    }

    public Message getMessage() {
        return message;
    }

    public String getContent() throws Exception {
        return message.getContent().toString();
    }
}
