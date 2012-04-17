package net.bounceme.dur.listeners;

import java.util.logging.Logger;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class MessageListener implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MessageListener.class.getName());

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        LOG.info("MessageListener.processAction..");
        LOG.info("..MessageListener.processAction");
    }
}