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
    private Payment(){}

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

    public static Builder builder(){
        return new Builder();
    }
    private Payment(Builder b){
        this.amount=b.amount;
        this.paymentMode=b.paymentMode;
        this.paymentStatus=b.paymentStatus;
        this.timeOfPayment=b.timeOfPayment;
        this.bill=b.bill;
    }
    public static class Builder{
        private PaymentMode paymentMode;
        private PaymentStatus paymentStatus;
        private int amount;
        private Date timeOfPayment;
        private Bill bill;

        public Builder setPaymentMode(PaymentMode paymentMode) {
            this.paymentMode = paymentMode;
            return this;
        }
        public Builder setPaymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }
        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;

        }
        public Builder setTimeOfPayment(Date timeOfPayment) {
            this.timeOfPayment = timeOfPayment;
            return this;

        }
        public Builder setBill(Bill bill) {
            this.bill = bill;
            return this;

        }

        public Payment build(){
            //validate payment
            return new Payment(this);
        }
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