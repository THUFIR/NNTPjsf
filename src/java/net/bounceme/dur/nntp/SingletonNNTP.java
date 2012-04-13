package net.bounceme.dur.nntp;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;

public enum SingletonNNTP {

    INSTANCE;
    private  final Logger log = Logger.getLogger(SingletonNNTP.class.getName());
    private  final Level level = Level.INFO;
    private Properties props = new Properties();
    private List<javax.mail.Message> messages = new ArrayList<javax.mail.Message>();
    private boolean loaded = false;
    private int index = 0;
    private Folder folder = null;
    private Folder root = null;
    private Store store = null;

    private SingletonNNTP() {
        log.logp(level, "SingletonNNTP", "SingletonNNTP", "only once...");
        props = PropertiesReader.getProps();
        if (!loaded) {
            try {
                loaded = connect();
            } catch (Exception ex) {
                Logger.getLogger(SingletonNNTP.class.getName()).log(Level.SEVERE, "FAILED TO LOAD MESSAGES", ex);
            }
        }
    }

    public List<javax.mail.Message> getMessages(boolean debug) throws Exception {
        log.logp(level, "SingletonNNTP", "getMessages", "returning messages");
        return Collections.unmodifiableList(messages);
    }

    private boolean connect() throws Exception {
        log.logp(level, "SingletonNNTP", "setMessages", "connecting to leafnode");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        store = session.getStore(new URLName(props.getProperty("nntp.host")));
        store.connect();
        root = store.getDefaultFolder();
        folder = root.getFolder(props.getProperty("nntp.group"));
        folder.open(Folder.READ_ONLY);
        setIndex(folder.getMessageCount());
        //folder.close(false);
        //store.close();
        setMessages();
        return true;
    }

    private void setMessages() throws Exception {
        javax.mail.Message[] msgs;
        msgs = folder.getMessages(getIndex() - 10, getIndex());
        messages = Arrays.asList(msgs);
        Collections.reverse(messages);
    }

    public void previousMessages() throws Exception {
        log.log(level, "SingletonNNTP.back..");
        setIndex(getIndex() - 10);
        setMessages();
    }

    public void nextMessages() throws Exception {
        log.log(level, "SingletonNNTP.forward..");
        setIndex(getIndex() + 10);
        setMessages();
    }

    public List<javax.mail.Message> getMessages() {
        log.log(level, "SingletonNNTP.returning messages..");
        return Collections.unmodifiableList(messages);
    }

    public Message getMessage(int id) throws Exception {
        log.log(level, "SingletonNNTP.getMessage..{0}", id);
        setIndex(id);
        return folder.getMessage(id);
    }

    public Header getMessageId(int id) throws Exception {
        Enumeration allHeaders = getMessage(id).getAllHeaders();
        Header messageIdHeader = null;
        while (messageIdHeader == null && allHeaders.hasMoreElements()) {
            Header header = (Header) allHeaders.nextElement();
            log.log(level, "{0}{1}", new Object[]{header.getName(), header.getValue()});
            if ("Message-ID".equals(header.getName())) {
                messageIdHeader = header;
            }
        }
        return messageIdHeader;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
