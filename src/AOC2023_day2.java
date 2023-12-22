import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class AOC2023_day2 {
    public static void main(String[] args) {
        String path = "path to text goes here";
        System.out.println(solve1(path));
        System.out.println(solve2(path));
    }

    private static int solve1(String path) {
        int sumId = 0;
        int gameId;
        int red = 12; //number of cubes
        int green = 13;
        int blue = 14;
        int currentCubes;
        char color;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                gameId = readInt(line, 0);

                for (int i = line.indexOf(":")-1; i < line.length(); i++) {
                    if (Character.isDigit(line.charAt(i))) {

                        currentCubes = readInt(line, i); //count ammount of cubes
                        i += String.valueOf(currentCubes).length();

                        color = line.charAt(i + 1); // get the color of the cubes

                        switch (color) { // check if there are more cubes than possible
                            case 'r':
                                if (red < currentCubes) {
                                    i = line.length();
                                }
                                break;
                            case 'b':
                                if (blue < currentCubes) {
                                    i = line.length();
                                }
                                break;
                            case 'g':
                                if (green < currentCubes) {
                                    i = line.length();
                                }
                                break;
                        }
                    }
                    if (i == line.length() - 1) { //if game is possible add the gameID to total
                        sumId += gameId;
                    }
                }
                line = reader.readLine();
            }
            return sumId;
        } catch (IOException e) {
            return -1;
        }
    }

    private static int readInt(String line, int from) { //Parses a integer from a string of numbers
        int fIdx = 0;
        int sIdx = 0;
        for (int i = from; fIdx == 0 || sIdx == 0; i++) {
            if (Character.isDigit(line.charAt(i)) && fIdx == 0) {
                fIdx = i;
            }
            if (fIdx != 0 && !Character.isDigit(line.charAt(i))) {
                sIdx = i;
            }
        }
        return Integer.parseInt(line.substring(fIdx, sIdx));
    }

    private static int solve2(String path){
        int red, green, blue;
        int cubes;
        int setSum = 0;
        char color;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while(line != null){
                red = green = blue = 0;

                for(int i = line.indexOf(":"); i < line.length(); i++){
                    if(Character.isDigit(line.charAt(i))){
                        cubes = readInt(line, i); //read how many cubes
                        i += String.valueOf(cubes).length();

                        color = line.charAt(i + 1); //color of cubes
                        switch(color){ //checks if there are more cubes needed for the set than the current set
                            case 'r':
                                red = Math.max(red, cubes);
                                break;

                            case 'g':
                                green = Math.max(green, cubes);
                                break;

                            case 'b':
                                blue = Math.max(blue, cubes);
                                break;
                        }
                    }
                }
               setSum += (red*green*blue);
               line = reader.readLine();
            }
            return setSum;
        }
        catch(IOException e){
            return -1;
        }
    }
}

