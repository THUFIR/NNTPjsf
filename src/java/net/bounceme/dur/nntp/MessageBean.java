package net.bounceme.dur.nntp;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.mail.Header;
import javax.mail.Message;

@Named
@SessionScoped
public class MessageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(MessageBean.class.getName());
    private static Level level = Level.INFO;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    public MessageBean() {
        logger.log(level, "MessageBean..");
    }

    public void action() throws Exception {
        logger.log(level, "action..");
    }

    public DataModel getModel() throws Exception {
        logger.log(level, "MessageBean.getModel..");
        List<Message> messages = new ArrayList<Message>();
        messages = nntp.getMessages();
        DataModel messagesDataModel = new ListDataModel(messages);
        return messagesDataModel;
    }

    public URL getUrl(Message message) throws Exception {
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

    private List<Header> getHeaders(Message message) throws Exception {
        Enumeration allHeaders = message.getAllHeaders();
        List<Header> headers = new ArrayList<Header>();
        while (allHeaders.hasMoreElements()) {
            Header hdr = (Header) allHeaders.nextElement();
            headers.add(hdr);
        }
        return headers;
    }

    public void forward() throws Exception {
        logger.log(level, "MessageBean.forward..");
        nntp.forward();
    }

    public void back() throws Exception {
        logger.log(level, "MessageBean.back..");
        nntp.back();
    }
}