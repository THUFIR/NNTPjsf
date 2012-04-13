package net.bounceme.dur.nntp;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.mail.Message;

@Named
@ConversationScoped
public class Detail implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Detail.class.getName());
    private static final Level level = Level.INFO;
    private String id = null;       //@PostConstruct should load id
    private Message message = null;
    private int messageId = 0;      //@PostConstruct should set correctly
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;
    private int forward = 0;  //id + 1
    private int back = 0;     //id - 1

    public Detail() {
        LOG.log(level, "Detail..");
    }

    @PostConstruct
    private void onLoad() {
        LOG.log(level, "Detail.onLoad..");
        setMessageId(Integer.parseInt(getId()));
        forward = getMessageId() + 1;
        back = getMessageId() - 1;
        try {
            message = nntp.getMessage(getMessageId());
        } catch (Exception ex) {
            Logger.getLogger(Detail.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOG.log(level, "..Detail.onLoad {0}", getMessageId());
    }

    public Message getMessage() {
        LOG.log(level, "Detail.getMessage..{0}", getMessageId());
        return message;
    }

    public void setMessage(Message message) {
        LOG.log(level, "Detail.setMessage..");
        this.message = message;
    }

    public String getId() {
        LOG.log(level, "Detail.getId..{0}", id);
        if (id == null) { //should never be null, should get from URL
            LOG.log(level, "Detail.getId..SETTING DEFAULT ID");
            id = String.valueOf(2000);
        }
        return id;
    }

    public void setId(String id) {
        LOG.log(level, "Detail.setId..{0}", getId());
        this.id = id;
        LOG.log(level, "..Detail.setId {0}", getId());
    }

    public int getForward() {
        LOG.log(level, "Detail.forward..{0}", forward);
        return forward;
    }

    public void setForward(int forward) {
        LOG.log(level, "Detail.forward..{0}", forward);
        this.forward = forward;
    }

    public int getBack() {
        LOG.log(level, "Detail.forward..{0}", forward);
        return back;
    }

    public void setBack(int back) {
        LOG.log(level, "Detail.forward..{0}", forward);
        this.back = back;
    }

    public int getMessageId() {
        LOG.log(level, "Detail.forward..{0}", forward);
        return messageId;
    }

    public void setMessageId(int messageId) {
        LOG.log(level, "Detail.forward..{0}", forward);
        this.messageId = messageId;
    }
}
