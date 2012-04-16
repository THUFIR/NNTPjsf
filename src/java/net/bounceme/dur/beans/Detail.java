package net.bounceme.dur.beans;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.mail.Message;
import net.bounceme.dur.nntp.SingletonNNTP;

@Named
@ConversationScoped
public class Detail implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Detail.class.getName());
    private Integer id = null;
    private Integer forward = null;
    private Integer back = null;
    private Message message = null;

    public Detail() {
        LOG.info("Detail..");
    }

    @PostConstruct
    public void configBean() throws Exception {
        LOG.info("Detail.configBean..");
        back = getId() - 1;
        forward = getId() + 1;
        SingletonNNTP nntp = SingletonNNTP.INSTANCE;
        nntp.setIndex(id);
        setMessage(nntp.getMessage());
    }

    public Integer getId() {
        LOG.info("Detail.getId.." + id);
        if (id == null) { //should get from URL as param
            LOG.info("..setting default id");  //should never get here
            id = new Integer(2000);
        }
        return id;
    }

    public void setId(Integer id) throws Exception {
        LOG.info("Detail.setId.." + id);
        this.id = id;
    }

    public Integer getForward() throws Exception {
        LOG.info("Detail.getForward.." + forward);
        return forward;
    }

    public void setForward(int forward) {
        LOG.info("Detail.setForward.." + forward);
        this.forward = forward;
    }

    public Integer getBack() throws Exception {
        LOG.info("Detail.setBack.." + back);
        return back;
    }

    public void setBack(int back) {
        LOG.info("Detail.setBack.." + back);
        this.back = back;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
