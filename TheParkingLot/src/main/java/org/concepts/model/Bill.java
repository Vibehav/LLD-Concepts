package org.concepts.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill extends BaseEntity {

    private int amount;
    private LocalDateTime exitTime;
    private Token token;        // Used to determine entry time and entry gate
    private Operator operator;
    private List<Payment> payments;
    private Gate gate;

    private Bill() {
    }

    private Bill(Builder builder) {
        this.amount = builder.amount;
        this.exitTime = builder.exitTime;
        this.token = builder.token;
        this.operator = builder.operator;
        this.payments = builder.payments;
        this.gate = builder.gate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int amount;
        private LocalDateTime exitTime;
        private Token token;
        private Operator operator;
        private List<Payment> payments = new ArrayList<>();
        private Gate gate;

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder exitTime(LocalDateTime exitTime) {
            this.exitTime = exitTime;
            return this;
        }

        public Builder token(Token token) {
            this.token = token;
            return this;
        }

        public Builder operator(Operator operator) {
            this.operator = operator;
            return this;
        }

        public Builder gate(Gate gate) {
            this.gate = gate;
            return this;
        }

        public Builder payments(List<Payment> payments) {
            this.payments = payments;
            return this;
        }

        public Builder addPayment(Payment payment) {
            this.payments.add(payment);
            return this;
        }

        public Bill build() {

            if (token == null) {
                throw new IllegalStateException("Token is mandatory.");
            }

            if (operator == null) {
                throw new IllegalStateException("Operator is mandatory.");
            }

            if (gate == null) {
                throw new IllegalStateException("Exit gate is mandatory.");
            }

            if (exitTime == null) {
                exitTime = LocalDateTime.now();
            }

            return new Bill(this);
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }
}