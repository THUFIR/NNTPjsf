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
    private Note note = new Note();
    private String forward = null;
    private String back = null;

    public Detail() {
        logger.log(level, "Detail..");
    }

    @PostConstruct
    private void foo() throws Exception{
        setId("1999");
        setForward("2000");
        setBack("1998");
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
        //setId(i);
        message = nntp.getMessage(Integer.parseInt(id));
        return message.getContent().toString();
    }

    public String getId() {
        logger.log(level, "Detail.getId.." + id);
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

    public void commentAction() {
        logger.log(level, "Detail.commentAction..");
        getNote().setId("messageIdGoesHere");
        getNote().setComment("hmmm");
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String getForward() {
        logger.log(level, "Detail.forward..{0}", getId());
        int f = Integer.parseInt(getId());
        f = f + 1;
        logger.log(level, "..Detail.forward {0}", f);
        return String.valueOf(f);
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getBack() {
        logger.log(level, "Detail.back..{0}", getId());
        int b = Integer.parseInt(getId());
        b = b - 1;
        logger.log(level, "..Detail.back {0}", b);
        return String.valueOf(b);
    }

    public void setBack(String back) {
        this.back = back;
    }
}
