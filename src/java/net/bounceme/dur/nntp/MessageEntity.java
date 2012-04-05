package net.bounceme.dur.nntp;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Header;
import javax.persistence.*;

@Entity
public class MessageEntity implements Serializable {

    private static long serialVersionUID = 1L;
    private String MessageId = "0";
    private List<Header> headers = new ArrayList<Header>();
    private String content = "content";
    private String subject = "subj";
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date sentDate = new Date();
    private List<Address> addresses = new ArrayList<Address>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private URL url = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageEntity)) {
            return false;
        }
        MessageEntity other = (MessageEntity) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getMessageId() {
        return MessageId;
    }

    public void setMessageId(String MessageId) {
        this.MessageId = MessageId;
    }

    public List<String> getStringHeaders() {
        List<Header> headerListOfHeaders = getHeaders();
        List<String> stringListOfHeaders = new ArrayList<String>();
        for (Header h : headerListOfHeaders) {
            stringListOfHeaders.add(h.getName() + " " + h.getValue() + "\n");
        }
        return stringListOfHeaders;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public URL getUrl() throws MalformedURLException {
        url = new URL("http://www.google.com/");
        for (Header h : headers) {
            if ("Archived-at".equals(h.getName())) {
                String s = h.getValue();
                s = s.substring(1, s.length()-1);
                url = new URL(s);
            }
        }
        return url;
    }

    public void setUrl(URL link) {
        this.url = link;
    }

    @Override
    public String toString() {

        String s = "no URL";
        try {
            s = "net.bounceme.dur.nntp.MessageEntity[ id=" + getId() + " ]"
             + getUrl().toExternalForm();
        } catch (MalformedURLException ex) {
            Logger.getLogger(MessageEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
