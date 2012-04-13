package net.bounceme.dur.nntp;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.mail.Header;

@Named
@SessionScoped
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Messages.class.getName());
    private static final Level LEVEL = Level.INFO;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    public Messages() {
        LOG.log(LEVEL, "MessageBean..");
    }

    public void action() throws Exception {
        LOG.log(LEVEL, "action..");
    }

    public DataModel getModel() throws Exception {
        LOG.log(LEVEL, "MessageBean.getModel..");
        List<javax.mail.Message> messages = new ArrayList<javax.mail.Message>();
        messages = nntp.getMessages();
        DataModel messagesDataModel = new ListDataModel(messages);
        return messagesDataModel;
    }

    public URL getUrl(javax.mail.Message message) throws Exception {
        List<Header> headers = getHeaders(message);
        URL url = new URL("http://www.google.com/");
        for (Header h : headers) {
            if ("Archived-at".equals(h.getName())) {
                String stringUrl = h.getValue();
                stringUrl = stringUrl.substring(1, stringUrl.length() - 1);
                url = new URL(stringUrl);
            }
        }
        return url;
    }

    private List<Header> getHeaders(javax.mail.Message message) throws Exception {
        Enumeration allHeaders = message.getAllHeaders();
        List<Header> headers = new ArrayList<Header>();
        while (allHeaders.hasMoreElements()) {
            Header hdr = (Header) allHeaders.nextElement();
            headers.add(hdr);
        }
        return headers;
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
}