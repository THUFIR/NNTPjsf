/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bounceme.dur.nntp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.mail.Message;

@Named
@Dependent
public class Detail {

    private static final Logger logger = Logger.getLogger(Detail.class.getName());
    private static final Level level = Level.INFO;
    @ManagedProperty(value = "#{param.id}")
    private Integer id = 0;
    private Message message = null;
    private SingletonNNTP nntp = SingletonNNTP.INSTANCE;

    public Detail() {
        message = nntp.getMessage(id);
    }

    public int forward() {
        logger.log(level, "Detail.forward.." + id);
        id = id + 1;
        return id;
    }

    public int back() {
        logger.log(level, "Detail.back.." + id);
        id = id - 1;
        return id;
    }

    public Message getMessage() {
        return message;
    }

    public String getContent() throws Exception {
        return message.getContent().toString();
    }
}
