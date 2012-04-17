package net.bounceme.dur.beans;

import java.io.Serializable;
import java.net.URL;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.mail.Message;
import net.bounceme.dur.nntp.SingletonNNTP;

@Named
@ConversationScoped
public class MessageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MessageBean.class.getName());
    private Message message;
    private URL url = null;
    private int id = 0;
    private int previous = 0;
    private int next = 0;

    public MessageBean() {
        LOG.info("MessageBean..");
    }

    @PostConstruct
    public void postConstruct() throws Exception {
        LOG.info("MessageBean.postConstruct..");
        SingletonNNTP nntp = SingletonNNTP.INSTANCE;
        nntp.setIndex(id);
        setMessage(nntp.getMessage());
        setId(message.getMessageNumber());
        setPrevious(getId() - 1);
        setNext(getId() + 1);
        setUrl(MessageUtils.getUrl(message));
    }

    public Message getMessage() {
        LOG.info("MessageBean.getMessage..");
        return message;
    }

    public void setMessage(Message message) {
        LOG.info("MessageBean.setMessage..");
        this.message = message;
    }

    public URL getUrl() throws Exception {
        LOG.info("MessageBean.getUrl..");
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}