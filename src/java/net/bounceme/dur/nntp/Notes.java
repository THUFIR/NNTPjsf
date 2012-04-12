package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

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

    private void setNote(Note note) {
        this.note = note;
    }

    public void comment() {
        logger.log(level, "Notes.newNote..");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
    
    }
}
