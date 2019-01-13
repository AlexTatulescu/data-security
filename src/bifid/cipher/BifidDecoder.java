package bifid.cipher;

public class BifidDecoder {
    private char[][] square;
    private PolybiusSquare polybiusSquare;

    public BifidDecoder(PolybiusSquare square) {
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


    public String getDecodedString(String string) {
        int[] codedCoordinatesArray = getCodedCoordinatesArray(string.toUpperCase());
        return retrieveDecodedStringByCoordinatesArray(codedCoordinatesArray);
    }

    private String retrieveDecodedStringByCoordinatesArray(int[] codedCoordinatesArray) {
        String decodedString = "";
        for (int i = 0; i < codedCoordinatesArray.length / 2; i++) {
            int[] myIntArray = {codedCoordinatesArray[i], codedCoordinatesArray[i + (codedCoordinatesArray.length / 2)]};
            decodedString += getCharByCoordinate(myIntArray);
        }
        return decodedString;
    }


    private int[] getCodedCoordinatesArray(String string) {
        int[] codedCoordinatesArray = new int[2 * string.length()];
        int j = 0;
        for (int i = 0; i < string.length(); i++) {
            codedCoordinatesArray[j] = getCharCoordinate(string.charAt(i))[0];
            codedCoordinatesArray[j + 1] = getCharCoordinate(string.charAt(i))[1];
            j = j + 2;
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