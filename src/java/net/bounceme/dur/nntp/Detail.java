/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bounceme.dur.nntp;

import java.io.IOException;
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
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;
    private String content = "content goes here";

    public Detail() {
        message = nntp.getMessage(id);
    }

    public void forward() {
        id++;
    }

    public void back() {
        id--;
    }

    public Message getMessage() {
        return message;
    }

    public String getContent() throws Exception {
        return message.getContent().toString();
    }
}
