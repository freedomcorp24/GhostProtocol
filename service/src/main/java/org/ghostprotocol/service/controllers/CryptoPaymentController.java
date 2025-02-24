package org.ghostprotocol.service.controllers;

import com.codahale.metrics.annotation.Timed;
import org.ghostprotocol.service.auth.AuthenticatedDevice;
import org.ghostprotocol.service.payment.CryptoTransactionManager;
import org.ghostprotocol.service.payment.CryptoWalletService;
import org.ghostprotocol.service.payment.PaymentTransaction;
import org.ghostprotocol.service.storage.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/payments/crypto")
public class CryptoPaymentController {
    private final CryptoTransactionManager transactionManager;
    private final CryptoWalletService walletService;

    public CryptoPaymentController(
            CryptoTransactionManager transactionManager,
            CryptoWalletService walletService) {
        this.transactionManager = transactionManager;
        this.walletService = walletService;
    }

    @Timed
    @POST
    @Path("/address")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPaymentAddress(
            @Auth AuthenticatedDevice auth,
            @QueryParam("currency") String currency) {
        
        PaymentAddress address = walletService.createPaymentAddress(auth.getAccount(), currency);
        return Response.ok(address).build();
    }

    @Timed
    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendPayment(
            @Auth AuthenticatedDevice auth,
            SendPaymentRequest request) {
        
        try {
            PaymentTransaction tx = transactionManager.initiatePayment(
                auth.getAccount(),
                request.recipientAddress,
                request.amount,
                request.currency
            ).join();
            
            return Response.ok(new SendPaymentResponse(tx.getTransactionHash())).build();
        } catch (CryptoTransactionManager.InsufficientBalanceException e) {
            return Response.status(400).entity("Insufficient balance").build();
        }
    }

    @Timed
    @GET
    @Path("/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionHistory(
            @Auth AuthenticatedDevice auth,
            @QueryParam("currency") String currency) {
        
        List<PaymentTransaction> transactions = transactionManager.getTransactionHistory(
            auth.getAccount(),
            currency
        );
        return Response.ok(transactions).build();
    }

    @Timed
    @GET
    @Path("/balance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalance(
            @Auth AuthenticatedDevice auth,
            @QueryParam("currency") String currency) {
        
        double balance = walletService.getBalance(auth.getAccount(), currency);
        return Response.ok(new BalanceResponse(balance)).build();
    }

    public static class SendPaymentRequest {
        public String recipientAddress;
        public double amount;
        public String currency;
    }

    public static class SendPaymentResponse {
        public String transactionHash;

        public SendPaymentResponse(String transactionHash) {
            this.transactionHash = transactionHash;
        }
    }

    public static class BalanceResponse {
        public double balance;

        public BalanceResponse(double balance) {
            this.balance = balance;
        }
    }
}
