package ch.heigvd.gen.monopoly.board;

import ch.heigvd.gen.monopoly.board.square.*;
/**
 * Class Board representing the board in the Monopoly Game.
 * Can give a square of the board.
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class Board {
    private final static int MAX_SQUARES = 40;
    private Square[] squares = new Square[MAX_SQUARES];

    /**
     * Constructor by default of the class Board. This will construct the squares
     */
    public Board(){
        //Creation of the special squares
        squares[0] = new GoSquare();
        squares[4] = new IncomeTaxSquare();
        squares[30] = new GoToJailSquare();

        //Creation of the regular squares
        for(int i = 0; i < MAX_SQUARES; i++){
            if (squares[i] == null)
                squares[i] = new RegularSquare(i);
        }
    }

    /**
     * Returns the square of destination starting from a square and the number of square to jump
     * @param oldLoc Square representing the starting square
     * @param fvTot int being the number of square to jump
     * @return a square that is the square of destination or null if the square is not found
     * @throws IllegalArgumentException if the square is null or fvTot isn't between 2 and 12
     */
    public Square getSquare(Square oldLoc, int fvTot) {
        //Verification that parameters are valid
        if(oldLoc == null || fvTot < 2 || fvTot > 12){
            throw new IllegalArgumentException("Square cannot be null and faceValue total can only be between 2 and 12");
        }
        int position;
        for(int i = 0; i < MAX_SQUARES; i++){
            if(oldLoc.getName().equals(squares[i].getName())){
                //The modulo is for the addition to not pass the array if a player arrives
                //at case 39 + 2 it will go to the square at position 1
                position = (i + fvTot) % MAX_SQUARES;
                return squares[position];
            }
        }
        //If square does not exist null is returned
        return null;
    }

    /**
     * Overload of the function getSquare to be able to return a square from a position desired
     * @param position int being the position of the wanted square
     * @return Square in the position given null if parameters are incorrect
     * @throws IllegalArgumentException if the position is negative or higher than MAX_SQUARES
     */
    public Square getSquare(int position) {
        if(position < 0 || position > MAX_SQUARES){
            throw new IllegalArgumentException("Square cannot be on a negative position(< 0) or higer than " + MAX_SQUARES);
        }
        return squares[position];
    }
}
