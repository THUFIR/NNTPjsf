package net.bounceme.dur.nntp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.mail.Header;
import javax.mail.Message;

@Named
@Dependent
public class Notes implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Notes.class.getName());
    private final Level LEVEL = Level.INFO;
    private List<Note> notes = new ArrayList<Note>();
    private String comment = "some comment";
    private Message message;

    public Notes() {
        LOG.log(LEVEL, "Notes..");
    }

    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String action() throws Exception {
        LOG.log(LEVEL, getComment());
        SingletonNNTP nntp = SingletonNNTP.INSTANCE;
        Header id = nntp.getMessageId(message.getMessageNumber());
        Note note = new Note();
        note.setMessageId(id.getValue());
        note.setComment(comment);
        notes.add(note);
        return "wtf";
    }
}
