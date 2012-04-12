package net.bounceme.dur.nntp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class Notes {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Notes.class.getName());
    private static final Level level = Level.INFO;
    private Note note = new Note();

    public Notes() {
        logger.log(level, "Notes..");
    }

    public Note getNote() {
        return note;
    }

    private void setNote(Note note) {
        this.note = note;
    }


    /*
     *     public String doCreateBook() {
        book = bookEJB.createBook(book);
        bookList = bookEJB.findBooks();
        return "listBooks.xhtml";
    }
     */
    public void commentAction() {
        logger.log(level, "Notes.newNote..");
        //ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        //FacesContext.getCurrentInstance().getExternalContext();
        //HttpServletResponse response = (HttpServletResponse) context.getResponse();
        String id = "messageIdHere";
        note.setComment("hmmm");
        note = NoteEJB.createNote(note);
    }
}
