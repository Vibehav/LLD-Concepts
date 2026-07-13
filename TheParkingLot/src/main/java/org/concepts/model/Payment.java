package org.concepts.model;

import org.concepts.enums.PaymentMode;
import org.concepts.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class Payment extends BaseEntity {

    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private int amount;
    private Date timeOfPayment;
    private Bill bill;

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTimeOfPayment() {
        return timeOfPayment;
    }

    public void setTimeOfPayment(Date timeOfPayment) {
        this.timeOfPayment = timeOfPayment;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}

/**
 * Schema Design
 * gate
 * id - primitive
 * amount_paid - primitve
 * time - TimeStamp
 *
 */