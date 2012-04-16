package net.bounceme.dur.listeners;

import java.math.BigDecimal;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class TransferBean {

    private static class TransferRequest {

        public TransferRequest() {
        }
    }

    private TransferRequest transferRequest;

    private BigDecimal transferedValue;


}