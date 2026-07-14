package org.concepts.model;

public class Operator extends BaseEntity {

    private int empId;

    private Operator() {
    }

    private Operator(Builder builder) {
        this.empId = builder.empId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int empId;

        public Builder empId(int empId) {
            this.empId = empId;
            return this;
        }

        public Operator build() {

            if (empId <= 0) {
                throw new IllegalStateException("Employee Id must be greater than 0.");
            }

            return new Operator(this);
        }
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
}