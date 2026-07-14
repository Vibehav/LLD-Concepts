package org.concepts.model;

import org.concepts.enums.GateStatus;
import org.concepts.enums.GateType;

public class Gate extends BaseEntity {

    private String gateNo;
    private GateType gateType;
    private Operator operator;
    private GateStatus gateStatus;

    public String getGateNo() {
        return gateNo;
    }
    public void setGateNo(String gateNo) {
        this.gateNo = gateNo;
    }
    public GateType getGateType() {
        return gateType;
    }
    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }
    public Operator getOperator() {
        return operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    public GateStatus getGateStatus() {
        return gateStatus;
    }
    public void setGateStatus(GateStatus gateStatus) {
        this.gateStatus = gateStatus;
    }

    private Gate(Builder b){
        this.gateNo=b.gateNo;
        this.gateStatus=b.gateStatus;
        this.gateType=b.gateType;
        this.operator=b.operator;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private String gateNo;
        private GateType gateType;
        private Operator operator;
        private GateStatus gateStatus;

        public Builder setGateNo(String gateNo) {
            this.gateNo = gateNo;
            return this;
        }
        public Builder setGateType(GateType gateType){
            this.gateType=gateType;
            return this;
        }
        public Builder setOperator(Operator operator) {
            this.operator = operator;
            return this;
        }
        public Builder setGateStatus(GateStatus gateStatus) {
            this.gateStatus = gateStatus;
            return this;
        }

        public Gate build(){
            //validations
            return new Gate(this);
        }
    }
}

/**
 * Schema Design
 * gate
 * id - primitve
 *
 *
 */
