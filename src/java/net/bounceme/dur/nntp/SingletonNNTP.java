package net.bounceme.dur.nntp;

import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;

public enum SingletonNNTP {

    INSTANCE;
    private final Logger log = Logger.getLogger(SingletonNNTP.class.getName());
    private Properties props = new Properties();
    private List<Message> messages = new ArrayList<Message>();
    private Message message;
    private boolean loaded = false;
    private int index = 0;   //hmmm, offset by two
    private Folder folder = null;
    private Folder root = null;
    private Store store = null;

    private SingletonNNTP() {
        log.info("SingletonNNTP..only once...");
        props = PropertiesReader.getProps();
        if (!loaded) {
            try {
                loaded = connect();
            } catch (Exception ex) {
                Logger.getLogger(SingletonNNTP.class.getName()).log(Level.SEVERE, "FAILED TO LOAD MESSAGES", ex);
            }
        }
    }

    public List<Message> getMessages(boolean debug) throws Exception {
        log.info("SingletonNNTP.getMessages..");
        return Collections.unmodifiableList(messages);
    }

    private boolean connect() throws Exception {
        log.info("SingletonNNTP.connect..");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        store = session.getStore(new URLName(props.getProperty("nntp.host")));
        store.connect();
        root = store.getDefaultFolder();
        folder = root.getFolder(props.getProperty("nntp.group"));
        folder.open(Folder.READ_ONLY);
        setIndex(folder.getMessageCount());
        return true;
    }

    private void setMessages() throws Exception {
        log.info("SingletonNNTP.setMessages..");
        Message[] msgs = folder.getMessages(getIndex() - 10, getIndex());
        messages = Arrays.asList(msgs);
        Collections.reverse(messages);
    }

    public void previousMessages() throws Exception {
        log.info("SingletonNNTP.back..");
        setIndex(getIndex() - 10);
        setMessages();
    }

    public void nextMessages() throws Exception {
        log.info("SingletonNNTP.forward..");
        setIndex(getIndex() + 10);
        setMessages();
    }

    public List<Message> getMessages() {
        log.info("SingletonNNTP.returning messages..");
        return Collections.unmodifiableList(messages);
    }

    public Message getMessage() throws Exception {
        log.info("SingletonNNTP.getMessage..");
        return message;
    }

    public Header getMessageId(int id) throws Exception {
        Enumeration allHeaders = getMessage().getAllHeaders();
        Header messageIdHeader = null;
        while (messageIdHeader == null && allHeaders.hasMoreElements()) {
            Header header = (Header) allHeaders.nextElement();
            log.info(header.getName());
            log.info(header.getValue());
            if ("Message-ID".equals(header.getName())) {
                messageIdHeader = header;
            }
        }
        return messageIdHeader;
    }

    public int getIndex() {
        log.log(Level.INFO, "SingletonNNTP.getIndex..{0}", index);
        return index;
    }

    private void setIndex(int index) throws Exception {
        log.log(Level.INFO, "SingletonNNTP.setIndex..{0}", index);
        this.index = index - 2;
        Message[] msgs = folder.getMessages(index - 10, index);
        message = msgs[9];
    }

    private List<Header> getHeaders() throws Exception {
        log.log(Level.INFO, "SingletonNNTP.getHeaders{0}", message.getSubject());
        Enumeration allHeaders = message.getAllHeaders();
        List<Header> headers = new ArrayList<Header>();
        while (allHeaders.hasMoreElements()) {
            Header hdr = (Header) allHeaders.nextElement();
            headers.add(hdr);
        }
        return headers;
    }

    public URL getUrl(int i) throws Exception {
        setIndex(i);
        List<Header> headers = getHeaders();
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
}
