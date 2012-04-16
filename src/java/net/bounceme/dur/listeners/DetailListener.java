package net.bounceme.dur.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import net.bounceme.dur.beans.Messages;
import net.bounceme.dur.nntp.SingletonNNTP;

public class DetailListener implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(DetailListener.class.getName());

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        LOG.info("BackListener.processAction..");
        LOG.info("..BackListener.processAction");
    }
}