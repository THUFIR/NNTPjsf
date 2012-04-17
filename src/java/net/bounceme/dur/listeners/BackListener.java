package net.bounceme.dur.listeners;

import java.util.logging.Logger;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import net.bounceme.dur.nntp.SingletonNNTP;

public class BackListener implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(BackListener.class.getName());

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        try {
            LOG.info("BackListener.processAction..." + event.toString());
            SingletonNNTP nntp = SingletonNNTP.INSTANCE;
            nntp.page(true);
        } catch (Exception ex) {
            LOG.severe("..BackListener.processAction would not execute " + ex.getMessage());
        }
        LOG.info("..BackListener.processAction");
    }
}