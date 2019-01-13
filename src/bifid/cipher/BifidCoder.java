package bifid.cipher;

public class BifidCoder {
    private char[][] square;
    private PolybiusSquare polybiusSquare;

    public BifidCoder(PolybiusSquare square) {
        this.square = square.getSquare();
        this.polybiusSquare = square;
    }

    public PolybiusSquare getPolybiusSquare() {
        return polybiusSquare;
    }

    public char[][] getSquare() {
        return square;
    }

    public void setSquare(char[][] square) {
        this.square = square;
    }

    public void setPolybiusSquare(PolybiusSquare polybiusSquare) {
        this.polybiusSquare = polybiusSquare;
        this.setSquare(polybiusSquare.getSquare());
    }

    public String getCodedString(String string) {
        int[] codedCoordinatesArray = getCodedCoordinatesArray(string.toUpperCase());
        String codedString = "";
        for (int i = 0; i < codedCoordinatesArray.length; i = i + 2) {
            int[] myIntArray = {codedCoordinatesArray[i], codedCoordinatesArray[i + 1]};
            codedString += getCharByCoordinate(myIntArray);
        }
        return codedString;
    }

    private int[] getCodedCoordinatesArray(String string) {
        int[] codedCoordinatesArray = new int[2 * string.length()];
        for (int i = 0; i < string.length(); i++) {
            codedCoordinatesArray[i] = getCharCoordinate(string.charAt(i))[0];
        }
        for (int i = 0; i < string.length(); i++) {
            codedCoordinatesArray[string.length() + i] = getCharCoordinate(string.charAt(i))[1];
        }

        return codedCoordinatesArray;
    }


    private int[] getCharCoordinate(char c) {
        int[] coordinate = new int[2];
        char[][] square = this.square;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (square[i][j] == c) {
                    coordinate[0] = i;
                    coordinate[1] = j;
                    break;
                }
            }
        }
        return coordinate;
    }

    private char getCharByCoordinate(int[] coordinate) {
        char[][] square = this.square;
        return square[coordinate[0]][coordinate[1]];
    }

}