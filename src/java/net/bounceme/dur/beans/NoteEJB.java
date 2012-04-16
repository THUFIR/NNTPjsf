package net.bounceme.dur.beans;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.bounceme.dur.nntp.Note;

class NoteEJB {

    //@PersistenceContext(unitName = "chapter10PU")
    //private EntityManager em;

    public static Note createNote(Note note) {

        return new Note();
    }
}
