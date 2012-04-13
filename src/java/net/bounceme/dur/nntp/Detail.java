package net.bounceme.dur.nntp;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;

@Named
@ConversationScoped
public class Detail implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Detail.class.getName());
    private static final Level level = Level.INFO;
    private String id = null; //does not match Message.getMessageNumber()
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;
    private int forward = 0;
    private int back = 0;
    @Inject
    private Notes notes;

    public Detail() {
        LOG.log(level, "Detail..");
    }

    public Message getMessage() throws Exception {
        LOG.log(level, "Detail.getMessage..{0}", getId());
        return message;
    }

    public void setMessage(Message message) {
        LOG.log(level, "Detail.setMessage..");
        this.message = message;
    }

    public String getId() throws Exception {
        LOG.log(level, "Detail.getId..{0}", id);
        if (id == null) { //should never be null, should get from URL as param
            LOG.log(level, "Detail.getId..SETTING DEFAULT ID");
            id = String.valueOf(2000);
        }
        return id;
    }

    public void setId(String id) throws Exception {
        LOG.log(level, "Detail.setId..{0}", getId());
        this.id = id;
        int intId = Integer.parseInt(id);
        message = nntp.getMessage();
        setForward(intId + 1);
        setBack(intId - 1);
        notes.setMessage(message);
        LOG.log(level, "..Detail.setId {0}", getId());
    }

    public int getForward() throws Exception {
        LOG.log(level, "Detail.getForward..{0}", forward);
        return forward;
    }

    public void setForward(int forward) {
        LOG.log(level, "Detail.setForward..{0}", forward);
        this.forward = forward;
    }

    public int getBack() throws Exception {
        LOG.log(level, "Detail.setBack..{0}", back);
        return back;
    }

    public void setBack(int back) {
        LOG.log(level, "Detail.setBack..{0}", back);
        this.back = back;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }
}
