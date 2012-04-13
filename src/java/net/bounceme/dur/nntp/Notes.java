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
    private final Level level = Level.INFO;
    private List<Note> notes = new ArrayList<Note>();
    private String comment = "some comment";
    private Message message;

    public Notes() {
        LOG.log(level, "Notes..");
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

    public void setComment(String comment) throws Exception {
        this.comment = comment;
        Enumeration allHeaders = getMessage().getAllHeaders();
        Header id = null;
        while (id == null && allHeaders.hasMoreElements()) {
            Header hdr = (Header) allHeaders.nextElement();
            if ("Message-ID".equals(hdr.getName())) {
                id = hdr;
            }
        }

    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
