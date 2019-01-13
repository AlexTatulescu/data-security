package bifid.cipher;

public class PolybiusSquare {
    private char[][] square;

    public PolybiusSquare() {
        char[][] square = {
                {'B', 'G', 'W', 'K', 'Z'},
                {'Q', 'P', 'N', 'D', 'S'},
                {'I', 'O', 'A', 'X', 'E'},
                {'F', 'C', 'L', 'U', 'M'},
                {'T', 'H', 'Y', 'V', 'R'}
        };
        this.square = square;
    }

    public char[][] getSquare() {
        return square;
    }
}