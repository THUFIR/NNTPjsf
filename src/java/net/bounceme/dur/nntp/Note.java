package net.bounceme.dur.nntp;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

@Entity
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Note.class.getName());
    private static final Level level = Level.INFO;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column
    private String comment = "comment here";
    @Column
    private String message = "some Message id";

    public Note() {
        logger.log(level, "Note..");
    }

    public String getId() {
        logger.log(level, "Note.getId..{0}", toString());
        return id;
    }

    public void setId(String id) {
        logger.log(level, "Note.setId..{0}", toString());
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\nnet.bounceme.dur.nntp.Note[ id=" + id + " ]"
                + "\nnet.bounceme.dur.nntp.Note[ comment=" + comment + " ]";
    }

    public String getComment() {
        logger.log(level, "Note.getComment..{0}", toString());
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        logger.log(level, "Note.setComment..{0}", toString());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
