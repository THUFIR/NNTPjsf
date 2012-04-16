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

    @PostConstruct
    public void foo() {
        LOG.info("MessageBean.foo..");
        nntp = SingletonNNTP.INSTANCE;
    }

    public Messages() {
        LOG.info("MessageBean..");
    }

    public void action() throws Exception {
        LOG.info("action..");
    }

    public DataModel getModel() throws Exception {
        LOG.info("MessageBean.getModel..");
        List<Message> messages = new ArrayList<Message>();
        messages = nntp.getMessages(false);
        DataModel messagesDataModel = new ListDataModel(messages);
        return messagesDataModel;
    }

    public void forward() throws Exception {
        LOG.info("MessageBean.forward..");
        nntp.page(false);
    }

    public void back() throws Exception {
        LOG.info("MessageBean.back..");
        nntp.page(true);
    }

    public String detail() throws Exception {
        LOG.info("MessageBean.detail..");
        return "detail.xhtml";
    }

    public String detail(Message m) throws Exception {
        LOG.info("MessageBean.detail..");
        return "detail.xhtml";
    }

    public URL getUrl(Message m) throws Exception {
        LOG.info("MessageBean.getUrl..");
        url = nntp.getUrl();
        return url;
    }
}