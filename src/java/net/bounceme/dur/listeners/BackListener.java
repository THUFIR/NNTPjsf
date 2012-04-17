package net.bounceme.dur.listeners;

import java.util.logging.Level;
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
        LOG.info("BackListener.processAction..");
        SingletonNNTP nntp = SingletonNNTP.INSTANCE;
        try {
            nntp.page(true);
        } catch (Exception ex) {
            Logger.getLogger(BackListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOG.info("..BackListener.processAction");
    }
}