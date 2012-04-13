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
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;
    private int forward = 0;  //id + 1
    private int back = 0;     //id - 1

    public Detail() {
        LOG.log(level, "Detail..");
    }

    //@PostConstruct
    private void onLoad() throws Exception {
        LOG.log(level, "Detail.onLoad..");
        message = nntp.getMessage(Integer.parseInt(getId()));
        setBack(message.getMessageNumber() - 1);
        setForward(message.getMessageNumber() + 1);
    }

    public Message getMessage() throws Exception {
        LOG.log(level, "Detail.getMessage..{0}", getId());
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

    public void setId(String id) throws Exception {
        LOG.log(level, "Detail.setId..{0}", getId());
        this.id = id;
        //onLoad();
        LOG.log(level, "..Detail.setId {0}", getId());
    }

    public int getForward() throws Exception {
        LOG.log(level, "Detail.forward..{0}", forward);
        return forward;
    }

    public void setForward(int forward) {
        LOG.log(level, "Detail.forward..{0}", forward);
        this.forward = forward;
    }

    public int getBack() throws Exception {
        LOG.log(level, "Detail.forward..{0}", forward);
        return back;
    }

    public void setBack(int back) {
        LOG.log(level, "Detail.forward..{0}", forward);
        this.back = back;
    }
}
