/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bounceme.dur.nntp;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.mail.Message;

/**
 *
 * @author thufir
 *
 *
 *  @ManagedProperty(value="#{param.id}") private Integer id;
 *
 *
 */
@Named
@Dependent
@ManagedProperty(value="#{param.id}")
public class Detail {

    private Message message = null;
    private Integer id = 0;

    public Detail() {
    }

    public void forward() {
    }

    public void back() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
