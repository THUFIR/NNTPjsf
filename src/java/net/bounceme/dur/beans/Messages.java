package net.bounceme.dur.beans;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static final Level LEVEL = Level.INFO;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;
    private URL url = null;

    public Messages() {
        LOG.log(LEVEL, "MessageBean..");
    }

    public void action() throws Exception {
        LOG.log(LEVEL, "action..");
    }

    public DataModel getModel() throws Exception {
        LOG.log(LEVEL, "MessageBean.getModel..");
        List<Message> messages = new ArrayList<Message>();
        messages = nntp.getMessages(false);
        DataModel messagesDataModel = new ListDataModel(messages);
        return messagesDataModel;
    }

    public void forward() throws Exception {
        LOG.log(LEVEL, "MessageBean.forward..");
        nntp.nextMessages();
    }

    public void back() throws Exception {
        LOG.log(LEVEL, "MessageBean.back..");
        nntp.previousMessages();
    }

    public String detail() throws Exception {
        LOG.log(LEVEL, "MessageBean.detail..");
        return "detail.xhtml";
    }

    public String detail(Message m) throws Exception {
        LOG.log(LEVEL, "MessageBean.detail..");
        return "detail.xhtml";
    }

    public URL getUrl(Message m) throws Exception {
        int i = m.getMessageNumber();
        url = nntp.getUrl(i);
        return url;
    }
}