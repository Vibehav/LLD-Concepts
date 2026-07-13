package org.concepts.repository;

import org.concepts.model.Token;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class TokenRepository {

    private Map<Integer, Token> tokenDb;
    private int id = 0; // Tracks the last assigned ID

    public TokenRepository(Map<Integer, Token> tokenDb) {
        this.tokenDb = tokenDb;
    }


    public Token save(Token token) {

        if(token.getId()!=0){
            token.setUpdatedAt(new Date());
            tokenDb.put(token.getId(), token);
            return token;
        }
        this.id += 1;
        token.setId(id);
        token.setCreatedAt(new Date());
        token.setUpdatedAt(new Date());

        tokenDb.put(id, token);
        return token;
    }


    public Optional<Token> findById(int tokenId) {
        if (tokenDb.containsKey(tokenId)) {
            return Optional.of(tokenDb.get(tokenId));
        }
        return Optional.empty();
    }
}