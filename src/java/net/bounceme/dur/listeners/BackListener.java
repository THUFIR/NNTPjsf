package net.bounceme.dur.listeners;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

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
    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}