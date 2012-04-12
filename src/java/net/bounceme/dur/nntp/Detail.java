package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.Message;

@Named
@RequestScoped
public class Detail {

    private static final Logger logger = Logger.getLogger(Detail.class.getName());
    private static final Level level = Level.INFO;
    private String id;              //seems to be null
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;
    private String forward = null;  //id + 1
    private String back = null;     //id - 1
    private String content = null;  //message.content

    public Detail() {
        logger.log(level, "Detail..");
    }

    @PostConstruct
    private void doesNotWork() {
        logger.log(level, "Detail.doesNotWork..");
    }

    public Message getMessage() {
        logger.log(level, "Detail.getMessage..");
        return message;
    }

    public void setMessage(Message message) {
        logger.log(level, "Detail.setMessage..");
        this.message = message;
    }

    public String getId() {
        logger.log(level, "Detail.getId..{0}", id);
        if (id == null) {
            logger.log(level, "Detail.getId..SETTING DEFAULT ID");
            id = String.valueOf(2000);
        }
        return id;
    }

    public void setId(String id) throws Exception {
        logger.log(level, "Detail.setId..{0}", getId());
        this.id = id;
        logger.log(level, "..Detail.setId {0}", getId());
    }

    public String getForward()  {
        logger.log(level, "Detail.forward..");
        int f = Integer.parseInt(getId());
        f = f + 1;
        logger.log(level, "..Detail.forward {0}", f);
        forward = String.valueOf(f);
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getBack() {
        logger.log(level, "Detail.back..");
        int b = Integer.parseInt(getId());
        b = b - 1;
        logger.log(level, "..Detail.back {0}", b);
        back = String.valueOf(b);
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getContent() throws Exception {
        logger.log(level, "Detail.getContent..{0}", getId());
        message = nntp.getMessage(Integer.parseInt(getId()));
        content = message.getContent().toString();
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
