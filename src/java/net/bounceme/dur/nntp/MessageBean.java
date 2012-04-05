package net.bounceme.dur.nntp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

@Named
@SessionScoped
public class MessageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(MessageBean.class.getName());
    private static Level level = Level.INFO;

    public MessageBean() {
        logger.log(level, "MessageBean..");
    }

    public DataModel getModel() throws Exception {
        logger.log(level, "MessageBean.getModel..");
        List<MessageEntity> messageEntities = getNNTP();
        DataModel messagesDataModel = new ListDataModel(messageEntities);
        return messagesDataModel;
    }

    private List<MessageEntity> getNNTP() throws Exception {
        logger.log(level, "MessageBean.getNNTP..");
        List<MessageEntity> messageEntities = new ArrayList<MessageEntity>();
        SingletonNNTP nntp = SingletonNNTP.INSTANCE;
        messageEntities = nntp.getEntities();
        logger.log(level, "MessageBean.getNNTP nntp.size:  {0}", messageEntities.size());
        return messageEntities;
    }
}