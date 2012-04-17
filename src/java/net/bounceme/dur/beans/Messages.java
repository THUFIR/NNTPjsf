package net.bounceme.dur.beans;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.mail.Message;
import net.bounceme.dur.nntp.SingletonNNTP;

@Named
@ConversationScoped
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Messages.class.getName());
    private SingletonNNTP nntp;
    private URL url = null;
    private DataModel messagesDataModel = null;
    private List<Message> messages = null;

    @PostConstruct
    public void foo() throws Exception {
        LOG.info("Messages.foo..");
        nntp = SingletonNNTP.INSTANCE;
        messages = nntp.getMessages(false);
        messagesDataModel = new ListDataModel(messages);
    }

    public Messages() {
        LOG.info("Messages..");
    }

    public void action() throws Exception {
        LOG.info("action..");
    }

    public DataModel getModel() throws Exception {
        LOG.info("Messages.getModel..");
        return messagesDataModel;
    }

    public void forward() throws Exception {
        LOG.info("Messages.forward..");
        nntp.page(false);
    }

    public void back() throws Exception {
        LOG.info("Messages.back..");
        nntp.page(true);
    }

    public String detail(Message m) throws Exception {
        LOG.info("Messages.detail..");
        return "detail.xhtml";
    }

    public URL getUrl(Message m) throws Exception {
        LOG.info("Messages.getUrl..");
        url = MessageUtils.getUrl(m);
        return url;
    }
}