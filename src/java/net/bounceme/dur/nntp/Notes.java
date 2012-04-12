package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "notes")
@Dependent
public class Notes {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Notes.class.getName());
    private static final Level level = Level.INFO;
    private String foo = "bar";
    private Note note = new Note();

    public Notes() {
        logger.log(level, "Notes..");
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
