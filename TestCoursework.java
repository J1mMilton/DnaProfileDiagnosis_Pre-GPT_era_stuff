
public class TestCoursework {

    public static void main(String[] args) {
        
        String db1 = "data/db1.csv";
        DnaProfileDiagnosis test = new DnaProfileDiagnosis(db1);
		
        String dna1 = "data/dna1.txt";
        test.readDna(dna1);
        System.out.println(test.checkProfile()); // Alice
        System.out.println(test.diagnoseHd());   // Normal
		
        String dna2 = "data/dna2.txt";
        test.readDna(dna2);
        System.out.println(test.checkProfile()); // Bob
        System.out.println(test.diagnoseHd());   // Huntington's
        
        String db2 = "data/db2.csv";
        DnaProfileDiagnosis test2 = new DnaProfileDiagnosis(db2);
        System.out.println(test2.checkProfile()); // IllegalArgumentException thrown

    }
}
