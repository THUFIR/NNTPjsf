package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.mail.Message;

@Named
@Dependent
public class Detail {

    private static final Logger logger = Logger.getLogger(Detail.class.getName());
    private static final Level level = Level.INFO;
    private String id;
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;
    private String forward = null;
    private String back = null;
    private String content = null;

    public Detail() {
        logger.log(level, "Detail..");
    }

    @PostConstruct
    private void foo() {
        getId();
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
            id = "2000";
        }
        return id;
    }

    public void setId(String i) throws Exception {
        logger.log(level, "Detail.setId..{0}", i);
        id = i;
        //message = nntp.getMessage(Integer.parseInt(i));
        logger.log(level, "..Detail.setId {0}", getId());
    }

    public String getForward() throws Exception {
        logger.log(level, "Detail.forward..");
        getId();
        int f = Integer.parseInt(getId());
        f = f + 1;
        logger.log(level, "..Detail.forward {0}", f);
        forward = String.valueOf(f);
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getBack() throws Exception {
        logger.log(level, "Detail.back..");
        getId();
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
        logger.log(level, "Detail.getContent..");
        message = nntp.getMessage(Integer.parseInt(id));
        content = message.getContent().toString();
        return content;
    }

    public void setContent(String i) throws Exception {
        logger.log(level, "Detail.getContent..{0}", i);
        setId(i);
        message = nntp.getMessage(Integer.parseInt(getId()));
        content = message.getContent().toString();
    }
}
