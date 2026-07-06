package org.concepts.model;

import org.concepts.exception.InvalidPlayerCountException;
import org.concepts.model.enums.CellState;
import org.concepts.model.enums.GameState;
import org.concepts.strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState = GameState.NOT_STARTED;
    private int nextTurn;
    private List<WinningStrategy> winningStrategies;

    private Game(int size,
                List<Player> players,
                List<WinningStrategy> winningStrategies) {
        this.board = new Board(size);  // Strong , HAS-A relationship
        this.players = players; // Weak , HAS-A relationship
        this.gameState = GameState.IN_PROGRESS;
        this.nextTurn = 0;
        this.winningStrategies = winningStrategies;
        this.moves = new ArrayList<>();
    }



    public void makeMove(Game game) {
        //Step 1: Select Player from the game
        Player player = players.get(nextTurn);
        System.out.println("Its " + player.getName() + "'s move.");

        //Step 2: Player selects the cell ( Factory Design Pattern )
        Move move = player.makeMove(this); // Ideally, player will make the move based on player type.
        // i.e. If bot object is called at runtime bot algo will be invoked or else player logic will get executed

        //Step 3: Select next Player and add the move in moves list
        nextTurn = (nextTurn+1)%players.size();
        this.moves.add(move);

        // Fill the cell
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell currCell = board.getCells().get(row).get(col);
        currCell.setCellState(CellState.FULL);
        currCell.setPlayer(player);

        // step 4 : Check the winner OR Check if it's a draw

        if(checkWinner(move)) {
            this.setWinner(player);
            this.setGameState(GameState.COMPLETE);
        }
        else if(moves.size()==this.board.getSize()*this.board.getSize()) {
            this.setGameState(GameState.DRAW);
        }
    }

    private boolean checkWinner(Move move) {
        for(WinningStrategy winningStrategy: winningStrategies) {
            if(winningStrategy.checkWinner(this.board,move)) {
                return true;
            }
        }
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(int nextTurn) {
        this.nextTurn = nextTurn;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder(){
        return new Builder();
    }
    private Game(Builder builder){
        this.board = new Board(builder.getSize());
        this.players=builder.getPlayers();
        this.winningStrategies=builder.getWinningStrategies();
        this.gameState = GameState.IN_PROGRESS;
        this.nextTurn = 0;
        this.moves = new ArrayList<>();
    }

    public static class Builder{
        int size;
        List<Player> players;
        List<WinningStrategy> winningStrategies;

        public int getSize() {
            return size;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build(){
            validateNumberOfPlayers();
            return new Game(this);
        }

        private void validateNumberOfPlayers() {
            if(this.players.size() >= this.size) throw new InvalidPlayerCountException("Number of player she be less then "+ (this.size-1));
        }
    }

}
