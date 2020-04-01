import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AminoAcidLLTester {
    @Test
    public void testCreateFromRNASequence() {
        String a = "GCUACGGAGCUUCGGAGCCCGUAGGUGGACAAG";
        AminoAcidLL list = new AminoAcidLL();
        list.createFromRNASequence(a);

    }


}