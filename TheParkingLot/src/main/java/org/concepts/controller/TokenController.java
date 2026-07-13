package org.concepts.controller;

import org.concepts.dto.IssueTokenRequest;
import org.concepts.dto.IssueTokenResponse;
import org.concepts.dto.RequestValidator;
import org.concepts.enums.ResponseStatus;
import org.concepts.model.Token;
import org.concepts.service.TokenService;

public class TokenController {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService){
        this.tokenService=tokenService;
    }

    //Api call
    public IssueTokenResponse issueToken(IssueTokenRequest issueTokenRequest){
        IssueTokenResponse issueTokenResponse = new IssueTokenResponse();
        try{
            RequestValidator.validate(issueTokenRequest);
            Token token = tokenService.issueToken(issueTokenRequest);
            issueTokenResponse.setMessage("Token has been assigned successfully.");
            issueTokenResponse.setTokenNo(token.getTokenNumber());
            issueTokenResponse.setStatus(ResponseStatus.SUCCESS);
            issueTokenResponse.setSlotNo(token.getAssignedSlot().getSlotNumber());
        } catch (Exception ex) {

            issueTokenResponse.setStatus(ResponseStatus.FAILURE);
            issueTokenResponse.setMessage("Failed to issue Token: " + ex.getMessage());

        }

        return issueTokenResponse;
    }
}
