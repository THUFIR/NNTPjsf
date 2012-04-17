package net.bounceme.dur.beans;

import java.io.Serializable;
import java.net.URL;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.mail.Message;

@Named
@ConversationScoped
public class MessageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MessageBean.class.getName());
    @Named
    @Produces
    private Message message;
    private URL url = null;

    public MessageBean() {
        LOG.info("Message..");
    }
    @PostConstruct
    public void postConstruct() throws Exception {
        LOG.info("Message.postConstruct..");
        MessageUtils.getUrl(message);
    }

    public Message getMessage() {
        LOG.info("Message.getMessage..");
        return message;
    }

    public void setMessage(Message message) {
        LOG.info("Message.setMessage..");
        this.message = message;
    }

    public URL getUrl() throws Exception {
        LOG.info("Messages.getUrl..");
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}