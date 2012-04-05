package net.bounceme.dur.nntp;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;

public enum SingletonNNTP {

    INSTANCE;
    private final Logger logger = Logger.getLogger(SingletonNNTP.class.getName());
    private final Level level = Level.INFO;
    private Properties props = new Properties();
    private List<Message> messages = new ArrayList<Message>();
    private boolean loaded = false;
    private List<MessageEntity> messageEntities = new ArrayList<MessageEntity>();

    private SingletonNNTP() {
        logger.logp(level, "SingletonNNTP", "SingletonNNTP", "only once...");
        props = PropertiesReader.getProps();
        if (!loaded) {
            try {
                loaded = setMessages();
            } catch (Exception ex) {
                Logger.getLogger(SingletonNNTP.class.getName()).log(Level.SEVERE, "FAILED TO LOAD MESSAGES", ex);
            }
        }
    }

    public List<Message> getMessages(boolean debug) throws Exception {
        logger.logp(level, "SingletonNNTP", "getMessages", "returning messages");
        return Collections.unmodifiableList(messages);
    }

    private boolean setMessages() throws Exception {
        logger.logp(level, "SingletonNNTP", "setMessages", "connecting to leafnode");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        Store store = session.getStore(new URLName(props.getProperty("nntp.host")));
        store.connect();
        Folder root = store.getDefaultFolder();
        Folder folder = root.getFolder(props.getProperty("nntp.group"));
        folder.open(Folder.READ_ONLY);
        Message[] msgs = folder.getMessages();
        messages = Arrays.asList(msgs);
        setEntities();
        folder.close(false);
        store.close();
        return true;
    }

    public List<MessageEntity> getEntities() {
        logger.logp(level, "SingletonNNTP", "getEntities", "getting entities");
        for (MessageEntity m : messageEntities) {
            for (Header h : m.getHeaders()) {
                logger.log(level, h.toString());
            }
        }
        return Collections.unmodifiableList(messageEntities);
    }

    private void setEntities() throws Exception {
        logger.logp(level, "SingletonNNTP", "loadEntities", "trying to convert");
        messageEntities = new ArrayList<MessageEntity>();
        for (Message message : messages) {
            MessageEntity entity = new MessageEntity();
            Enumeration allHeaders = message.getAllHeaders();
            List<Header> headers = new ArrayList<Header>();
            while (allHeaders.hasMoreElements()) {
                Header hdr = (Header) allHeaders.nextElement();
                headers.add(hdr);
            }
            entity.setHeaders(headers);
            entity.setSubject(message.getSubject());
            entity.setContent(message.getContent().toString());
            entity.setSentDate(message.getReceivedDate());
            messageEntities.add(entity);
        }
    }
}
