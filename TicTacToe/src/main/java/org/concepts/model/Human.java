package org.concepts.model;

import org.concepts.model.enums.CellState;
import org.concepts.model.enums.PlayerType;

import java.util.Scanner;

public class Human extends Player{

    private String age;
    private Scanner scanner = new Scanner(System.in);

    public Human(int id, String name, Symbol symbol,String age) {
        super(id, name, symbol, PlayerType.HUMAN);
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public Move makeMove(Game game) {
        System.out.println("Please enter row and column to make a move");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        validateMove(row,col,game.getBoard());

        return new Move(this,new Cell(row,col));
    }

    private void validateMove(int row, int col, Board board) {
        if(row >=0 && row< board.getSize()
                && col>=0 && col < board.getSize()
                && board.getCells().get(row).get(col).getCellState() != CellState.FULL){
            return;
        } else throw new RuntimeException("you cannot make that move.");
    }

}
