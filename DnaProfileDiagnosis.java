
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class DnaProfileDiagnosis {
    
    // you may modify/add more instance variables
    // but your algorithms must primarily use the following list and/or map
    private LinkedList<String[]> list = new LinkedList<>();
    private HashMap<Integer, String> map = new HashMap<>();
    private String dna;
    // build a database from database.csv
    public DnaProfileDiagnosis(String database) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(database));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                list.add(values);
            }
            br.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    // store a dna sequence with no whitespace from dna.txt
    public void readDna(String dna) {
        String line;
        String str = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(dna));
            while ((line = br.readLine()) != null) {
                str = str + line;
            }
            br.close();
            this.dna = str.replaceAll("\\s", "");
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        
    }

    // based on the STR counts, return either a name in 
    // database, or "No Match"
    // throws IllegalArgumentException if dna has not been set
    public String checkProfile() {
        
        if (dna == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 1; i < list.size(); i++) {
            for (int j = 1; j < list.get(0).length; j++) {
                String str = list.get(0)[j];
                int count = 0; int max = 0;
                int found; int start = 0;
                while (true) {
                    found = dna.indexOf(str, start);
                    if (found != -1) {
                        count++;
                        if (count > max) {
                            max = count;
                        }
                        start = found + str.length();
                        if (start + str.length() > dna.length()) {
                            break;
                        }
                        if (dna.substring(start, start + str.length()).equals(str) == false) {
                            count = 0;
                        }
                    } else {
                        break;
                    }
                }
                start = 0;
                int num = Integer.parseInt(list.get(i)[j]);
                if (!(max == num)) {
                    break;
                }
                if (j == list.get(0).length-1) {
                    return list.get(i)[0];
                }
            }
        }
        
        
        return "No match";
    }

    // based on the CAG repeats, return either "Faulty Test",
    // "Normal", "High Risk", or "Huntington's"
    // throws IllegalArgumentException if dna has not been set
    public String diagnoseHd() {
        if (dna == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < 10; i++) {
            map.put(i, "Faulty Test");
        }
        for (int i = 10; i < 36; i++) {
            map.put(i, "Normal");
        }
        for (int i = 36; i < 40; i++) {
            map.put(i, "High Risk");
        }
        for (int i = 40; i < 181; i++) {
            map.put(i, "Huntington's");
        }
        int count = 0;
        int max = 0;
        int found;
        int start = 0;
        while (true) {
            found = dna.indexOf("CAG", start);
            if (found != -1) {
                count++;
                if (count > max) {
                    max = count;
                }
                start = found + 3;
                if (start + 3 > dna.length()) {
                    break;
                }
                if (dna.substring(start, start + 3).equals("CAG") == false) {
                    count = 0;
                }
            } else {
                break;
            }
        }
        if (max > 180) {
            return "Faulty Test";
        }
        return map.get(max);
    }

}
