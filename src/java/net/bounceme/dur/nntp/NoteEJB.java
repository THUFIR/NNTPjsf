package net.bounceme.dur.nntp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

class NoteEJB {

    //@PersistenceContext(unitName = "chapter10PU")
    //private EntityManager em;

    public static Note createNote(Note note) {

        return new Note();
    }
}
