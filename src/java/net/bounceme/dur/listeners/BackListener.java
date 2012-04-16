package net.bounceme.dur.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import net.bounceme.dur.beans.Messages;
import net.bounceme.dur.nntp.SingletonNNTP;

public class BackListener implements ActionListener {

    /*
     * Only create and register the ActionListener instance the first time the
     * component for this tag is created The "target" and "value" tag attributes
     * are ValueExpression instances and are stored unevaluated as instance
     * variables of the listener. When the listener executes, call getValue() on
     * the "value" ValueExpression. Pass the result to a call to setValue() on
     * the "target" ValueExpression
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(BackListener.class.getName());

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        LOG.info("BackListener.processAction..");
        SingletonNNTP nntp = SingletonNNTP.INSTANCE;
        LOG.info("..BackListener.processAction");
    }
}