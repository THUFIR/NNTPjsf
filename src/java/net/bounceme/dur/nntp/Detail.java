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
    private String id = null;
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;
    private int forward = 0;
    private int back = 0;

    public Detail() {
        LOG.info("Detail..");
    }

    @PostConstruct
    public void configBean() {
        int intId = Integer.parseInt(id);
        try {
            nntp.setIndex(intId);
            message = nntp.getMessage();
        } catch (Exception ex) {
            LOG.info("Detail.configBean..failed to set message");
        }
        setForward(intId + 1);
        setBack(intId - 1);
    }

    public Message getMessage() throws Exception {
        LOG.info("Detail.getMessage.." + getId());
        return message;
    }

    public void setMessage(Message message) {
        LOG.info("Detail.setMessage..");
        this.message = message;
    }

    public String getId() throws Exception {
        LOG.info("Detail.getId.." + id);
        if (id == null) { //should never be null, should get from URL as param
            LOG.info("..setting default id");
            id = String.valueOf(2000);
        }
        return id;
    }

    public void setId(String id) throws Exception {
        LOG.info("Detail.setId.." + id);
        this.id = id;
    }

    public int getForward() throws Exception {
        LOG.info("Detail.getForward.." + forward);
        return forward;
    }

    public void setForward(int forward) {
        LOG.info("Detail.setForward.." + forward);
        this.forward = forward;
    }

    public int getBack() throws Exception {
        LOG.info("Detail.setBack.." + back);
        return back;
    }

    public void setBack(int back) {
        LOG.info("Detail.setBack.." + back);
        this.back = back;
    }
}
