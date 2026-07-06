package org.concepts.model;

import org.concepts.model.enums.CellState;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<Cell>> cells;
    private int size;

    public Board(int size) {
        this.size = size;
        this.cells = makeEmptyCanvas(size);
    }

    private List<List<Cell>> makeEmptyCanvas(int size) {
        List<List<Cell>> cells = new ArrayList<>();
       for(int i=0;i<size;i++){
          cells.add(new ArrayList<>());
           for(int j=0;j<size;j++){
           cells.get(i).add(new Cell(i,j));
           }
       }
       return cells;
    }

    public void display(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells.get(i).get(j).display();
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }
}


