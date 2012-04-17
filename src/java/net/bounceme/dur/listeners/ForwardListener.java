package net.bounceme.dur.listeners;

import java.util.logging.Logger;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import net.bounceme.dur.nntp.SingletonNNTP;

public class ForwardListener implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ForwardListener.class.getName());

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        try {
            LOG.info("ForwardListener.processAction..." + event.toString());
            SingletonNNTP nntp = SingletonNNTP.INSTANCE;
            nntp.page(false);
        } catch (Exception ex) {
            LOG.severe("..ForwardListener.processAction would not execute " + ex.getMessage());
        }
        LOG.info("..ForwardListener.processAction");
    }
}