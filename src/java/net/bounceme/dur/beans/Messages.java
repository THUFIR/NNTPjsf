package net.bounceme.dur.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import net.bounceme.dur.nntp.SingletonNNTP;

@Named
@ConversationScoped
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Messages.class.getName());
    private DataModel messagesDataModel = null;
    private List<Message> messages = null;
    @Inject
    private MessageBean messageBean;

    @PostConstruct
    public void postConstruct() throws Exception {
        LOG.info("Messages.postConstruct..");
        SingletonNNTP nntp = SingletonNNTP.INSTANCE;
        boolean debugNNTP = false;
        messages = nntp.getMessages(debugNNTP);
        messagesDataModel = new ListDataModel(messages);
    }

    public Messages() {
        LOG.info("Messages..");
    }

    public DataModel getModel() throws Exception {
        LOG.info("Messages.getModel..");
        return messagesDataModel;
    }

    public void forward() throws Exception {
        LOG.info("Messages.forward..");

    }

    public void back() throws Exception {
        LOG.info("Messages.back..");
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public void insert(Message message) {
        LOG.info("Messages.insert..");
    }
}