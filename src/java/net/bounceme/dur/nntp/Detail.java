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
    private String id;       //should never get default value in getter
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;
    private String forward = null;  //id + 1
    private String back = null;     //id - 1
    private String content = null;  //message.content

    public Detail() {
        LOG.log(level, "Detail..");
    }

    @PostConstruct
    private void onLoad() {
        LOG.log(level, "Detail.onLoad..{0}", getId());
    }

    public Message getMessage() throws Exception {
        LOG.log(level, "Detail.getMessage..{0}", getId());
        message = nntp.getMessage(Integer.parseInt(getId()));
        return message;
    }

    public void setMessage(Message message) {
        LOG.log(level, "Detail.setMessage..");
        this.message = message;
    }

    public String getId() {
        LOG.log(level, "Detail.getId..{0}", id);
        if (id == null) {
            LOG.log(level, "Detail.getId..SETTING DEFAULT ID"); //should never be null
            id = String.valueOf(2000);
        }
        return id;
    }

    public void setId(String id) throws Exception {
        LOG.log(level, "Detail.setId..{0}", getId());
        this.id = id;
        LOG.log(level, "..Detail.setId {0}", getId());
    }

    public String getForward() {
        LOG.log(level, "Detail.forward..");
        int f = Integer.parseInt(getId());
        f = f + 1;
        LOG.log(level, "..Detail.forward {0}", f);
        forward = String.valueOf(f);
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getBack() {
        LOG.log(level, "Detail.back..");
        int b = Integer.parseInt(getId());
        b = b - 1;
        LOG.log(level, "..Detail.back {0}", b);
        back = String.valueOf(b);
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getContent() throws Exception {
        LOG.log(level, "Detail.getContent..{0}", getId());
        content = message.getContent().toString();
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
