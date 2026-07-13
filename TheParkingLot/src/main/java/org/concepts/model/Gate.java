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
}

/**
 * Schema Design
 * gate
 * id - primitve
 *
 *
 */
