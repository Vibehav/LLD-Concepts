package org.concepts.dto;

import org.concepts.exception.InvalidRequestException;

import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    public static void validate(IssueTokenRequest request) {
        List<String> errors = new ArrayList<>();

        if (request.getGateId() <= 0) errors.add("gateId must be positive");
        if (request.getParkingLotId() <= 0) errors.add("parkingLotId must be positive");
        if (request.getVehicleNumber() == null || request.getVehicleNumber().isBlank())
            errors.add("vehicleNumber is required");
        if (request.getVehicleType() == null) errors.add("vehicleType is required");
        if (request.getOperatorPreference() == null) errors.add("operatorPreference is required");
        if (request.getParkingSlotType() == null) errors.add("parkingSlotType is required");

        if (!errors.isEmpty()) {
            throw new InvalidRequestException(String.join("; ", errors));
        }
    }
}
