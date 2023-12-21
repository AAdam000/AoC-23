import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class AOC2023_day1 {
    public static void main(String[] args) {
        String path = "path goes here"; //path to file to read
        System.out.println(solve1(path));
        System.out.println(solve2(path));
    }

    public static int solve1(String path) {
        int lineSum = 0; //each sum to be added to final sum
        int rSum = 0; //final sum
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                for (int i = 0; i < line.length(); i++) { //find first digit
                    if (Character.isDigit(line.charAt(i))) {
                        lineSum = 10 * (line.charAt(i) - '0');
                        break;
                    }
                }
                for (int i = line.length() - 1; i >= 0; i--) { //find second digit
                    if (Character.isDigit(line.charAt(i))) {
                        lineSum += (line.charAt(i) - '0');
                        break;
                    }
                }
                rSum += lineSum; //add lineSum to total
                line = reader.readLine();
            }
            return rSum;
        } catch (IOException e) {
            return -1;
        }
    }

    public static int solve2(String path){
        int lSumFst = 0; //lineSum of the first digit
        int lSumSnd = 0; //lineSum of the last digit
        int rSum = 0;
        int idxfst;
        int idxsnd;
        Map <String, Integer> numbers = new HashMap<>();
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);
        numbers.put("five", 5);
        numbers.put("six", 6);
        numbers.put("seven", 7);
        numbers.put("eight", 8);
        numbers.put("nine", 9);

        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while(line != null){
                idxfst = 0;
                idxsnd = line.length()-1;
                for(int i = 0; i < line.length(); i++){
                    if(Character.isDigit(line.charAt(i))){
                        lSumFst = (line.charAt(i) - '0');
                        idxfst = i;
                        break;
                    }
                }
                for(int i = line.length()-1; i >= 0; i--){
                    if(Character.isDigit(line.charAt(i))){
                        lSumSnd = (line.charAt(i) - '0');
                        idxsnd = i;
                        break;
                    }
                }
                for(String sLine : numbers.keySet()){
                    //gets first occurrence of digit as word and checks if it is before digit
                    if(line.contains(sLine) && line.indexOf(sLine) < idxfst){
                        lSumFst = numbers.get(sLine);
                        idxfst = line.indexOf(sLine);
                    }
                    //checks last occurrence --//--
                    if(line.contains(sLine) && line.lastIndexOf(sLine) > idxsnd){
                        lSumSnd = (numbers.get(sLine));
                        idxsnd = line.lastIndexOf(sLine);
                    }
                }

                rSum += lSumFst * 10 + lSumSnd;
                line = reader.readLine();
            }
            return rSum;
        }
        catch (IOException e){
            return -1;
        }
    }
}