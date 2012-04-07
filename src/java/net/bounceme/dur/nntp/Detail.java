/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bounceme.dur.nntp;

import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.mail.Message;

@Named
@Dependent
public class Detail {

    @ManagedProperty(value = "#{param.id}")
    private Integer id = 0;
    private Message message = null;

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
