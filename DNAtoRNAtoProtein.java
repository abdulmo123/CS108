import java.io.*;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class DNAtoRNAtoProtein {

    public static void main(String[] args) throws IOException {

        String DNAFile = "DNA.txt"; // create string DNAFile and assign to DNA text file
        String RNAFile = "RNA.txt"; // create string RNAFile and assign to RNA text file
        DNA2RNA(DNAFile, RNAFile); // call first method

        String ProteinFile = "Protein.txt"; // create string ProteinFile and assign to Protein text file
        String RNA2ProteinTable = "RNA2Protein.txt"; // create string RNA2Protein and assign to RNA2Protein text file
        RNA2Protein(RNAFile, ProteinFile,RNA2ProteinTable); // call second method
    }

    public static void DNA2RNA(String DNAFile, String RNAFile) throws IOException {

        FileReader fr = new FileReader(DNAFile); // create file reader taking in DNAFile
        BufferedReader br = new BufferedReader(fr); // create buffered reader taking in file reader
        FileWriter fw = new FileWriter(RNAFile); // create file writer taking in RNAFile
        BufferedWriter bw = new BufferedWriter(fw); // create BufferedWriter taking in file writer
        PrintWriter pw = new PrintWriter(bw); // create print writer taking in buffered writer

        String out = "";
        String onLine;

        try {
            while ((onLine = br.readLine()) != null) { // while not equal to an empty space
                out = "";
                for (int i = 0; i < onLine.length(); i++) {
                    if (onLine.charAt(i) == 'A') { // switch A to U
                        out += 'U';
                    }
                    else if (onLine.charAt(i) == 'T') { // switch T to A
                        out += 'A';
                    }
                    else if (onLine.charAt(i) == 'C') { // switch C to G
                        out += 'G';
                    }
                    else if (onLine.charAt(i) == 'G') { // switch G to C
                        out += 'C';
                    }
                    else {
                        throw new NoSuchElementException("Not a DNA character");
                        // exception if not 1 of 4
                    }
                }
                pw.println(out);
            }
        }

        catch (Exception e) {
            System.out.println("Not a DNA character"); // print exception
        }

        finally {
            bw.flush();
            fw.close();
            bw.close(); // flushing and closing
            fr.close();
            br.close();
        }
    }
    public static void RNA2Protein(String RNAFile, String ProteinFile, String RNA2ProteinTable) throws IOException {
        //RNA to Protein, generates Protein.txt
        FileReader fr1 = new FileReader(RNAFile); // create first file reader
        FileReader fr2 = new FileReader(RNA2ProteinTable); // create second file reader
        BufferedReader br1 = new BufferedReader(fr1); // create first buffered reader
        BufferedReader br2 = new BufferedReader (fr2); // create second buffered reader
        FileWriter fw = new FileWriter(ProteinFile); // create file writer
        BufferedWriter bw = new BufferedWriter(fw); // create buffered writer

        String str;

        HashMap<String, String> proteinTable = new HashMap<String, String>();

        try {
            while ((str = br2.readLine())!= null) { //
                String [] input = str.split(" "); // assign every other 3 letters of rna text file
                proteinTable.put(input[0], input[1]); //
            }

            while ((str = br1.readLine())!= null) {
                for ( int i = 0; i < str.length(); i+=3) { // loop thru string and increment by 3
                    String str1 = str.substring(i, i+3 ); // substring grabs every 3 rna elements
                    bw.write(proteinTable.get(str1));
                }
                bw.newLine();
                bw.flush();
            }
            bw.flush();
        }

        catch (Exception e) {
            System.out.println("Invalid RNA length");
        }

        finally {
            bw.close();
            fr1.close();
            br1.close(); // closing
            fr2.close();
            br2.close();
            fw.close();
        }
    }
}