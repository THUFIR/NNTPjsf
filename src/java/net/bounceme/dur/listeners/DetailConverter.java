package net.bounceme.dur.listeners;

import java.util.logging.Logger;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class DetailConverter implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(DetailConverter.class.getName());

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        LOG.info("DetailListener.processAction..");
        LOG.info("..DetailListener.processAction");
    }
}