package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/*
 * @Named
public class Notes {

    @Inject
    private Detail detail;

}

 */

@Named
@Dependent
public class Notes {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Notes.class.getName());
    private static final Level level = Level.INFO;
    private Note note = new Note();

    public Notes() {
        LOG.log(level, "Notes..");
    }

    public Note getNote() {
        return note;
    }

    private void setNote(Note note) {
        this.note = note;
    }

    public void commentAction() {
        LOG.log(level, "Notes.newNote..");        note.setId("messageIdGoesHere");
        note.setComment("hmmm");
    }
}
