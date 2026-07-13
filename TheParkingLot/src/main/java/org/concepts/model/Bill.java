package org.concepts.model;

import java.time.LocalDateTime;
import java.util.List;

public class Bill extends BaseEntity {


    private int amount;
    private LocalDateTime exitTime;
    private Token token; // DAP : I want to know the entry gate, I want to know the time slot
    private Operator operator;
    private List<Payment> payments ;
    private Gate gate;

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
/**
 * Schema Design
 * gate
 * id - primitve
 * bill_number - primitve
 * amount - primitve
 * exit_time - TimeStamp
 *
 */
