import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AminoAcidLLTester {
    @Test
    public void testCreateFromRNASequence() {
        String a = "GCU";
        AminoAcidLL list = new AminoAcidLL();
        list.createFromRNASequence(a);

    }

    @Test
    public void testisSorted() {
        String a = "GCUACGGAGCUUCGGAGCCCGUAGGUGGACAAG";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(a);
        list.isSorted();
    }

    @Test
    public void testAminoAcidList() {
        String a = "GCUACGGAGCUUCGGAGCCCGUAGGUGGACAAGGCUGCUGCU";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(a);
        list.aminoAcidList();
    }

    @Test
    public void testAminoAcidCounts() {
        String a = "GCUACGGAGCUUCGGAGCCCGUAGGUGGACAAGGCUGCUGCUGCUGCUACG";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(a);
        list.aminoAcidCounts();
    }

    @Test
    public void testSort() {
        String a = "GCUACGGAGCUUCGGAGCCCGUAGGUGGACAAG";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(a);
        list.sort(list);
    }
}


