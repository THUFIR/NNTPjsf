package net.bounceme.dur.nntp;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.Message;



public enum SingletonNNTP {

    INSTANCE;
    private final Logger logger = Logger.getLogger(SingletonNNTP.class.getName());
    private final Level level = Level.INFO;
    private Properties props = new Properties();
    private List<Message> messages = new ArrayList<Message>();
    private boolean loaded = false;

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
        javax.mail.Message[] msgs = folder.getMessages();
        messages = Arrays.asList(msgs);
        //folder.close(false);
        //store.close();
        return true;
    }

    public List<Message> getMessages() {
        logger.logp(level, "SingletonNNTP", "getMessages", "returning messages");
        return Collections.unmodifiableList(messages);
    }



}
