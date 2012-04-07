package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.mail.Message;

@ManagedBean(value = "detail")
@RequestScoped
public class Detail {

    private static final Logger logger = Logger.getLogger(Detail.class.getName());
    private static final Level level = Level.INFO;
    @ManagedProperty(value = "#{param.id}")
    private Integer id = 0;
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    public Detail() {
        message = nntp.getMessage(id);
    }

    public int forward() {
        logger.log(level, "Detail.forward.." + id);
        id = id + 1;
        logger.log(level, "..Detail.forward " + id);
        return id;
    }

    public int back() {
        logger.log(level, "Detail.back.." + id);
        id = id - 1;
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
