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
        LOG.info("MessageBean.getMessage.." + message.getMessageNumber());
        return message;
    }

    public void setMessage(Message message) {
        LOG.info("MessageBean.setMessage.." + message.getMessageNumber());
        this.message = message;
    }

    public URL getUrl() throws Exception {
        LOG.info("MessageBean.getUrl..");
        return url;
    }

    public void setUrl(URL url) {
        LOG.info("MessageBean.setUrl..");
        this.url = url;
    }

    public int getId() {
        LOG.info("MessageBean.getId.." + id);
        return id;
    }

    public void setId(int id) {
        LOG.info("MessageBean.setId.." + id);
        this.id = id;
    }

    public int getPrevious() {
        LOG.info("MessageBean.getPrevious.." + previous);
        return previous;
    }

    public void setPrevious(int previous) {
        LOG.info("MessageBean.setPrevious.." + previous);
        this.previous = previous;
    }

    public int getNext() {
        LOG.info("MessageBean.getNext.." + next);
        return next;
    }

    /*
     *          <h:link value="back" outcome="detail" includeViewParams="true">
                        <f:param name="id" value="#{detail.back}"/>
                    </h:link>

     */
    public void setNext(int next) {
        LOG.info("MessageBean.setNext.." + next);
        this.next = next;
    }
}