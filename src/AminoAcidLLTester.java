import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AminoAcidLLTester {

    /* Test's the basic functionality comparing counts of Amino Acids
     * Creates a linked list and then takes the total amount of AminoAcids
     * (includes repeating), and will take the difference of the two
     * Input: GGGGCCGUC --> List 1
     * Input: UACGCC --> List 2
     * List 1 --> A A V (Count: 3)
     * List 2 --> T A (Count: 2)
     * 3 - 2 = 1
     * Expected: 1
     */

    @Test
    public void testAminoAcidCompare1() {
        String a = "GGGGCCGUC";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(a);
        String b = "UACGCC";
        AminoAcidLL list2 = new AminoAcidLL();
        list2 = list2.createFromRNASequence(b);
        list.codonCompare(list2);
        assertEquals(list.aminoAcidCompare(list2), 1);

    }

    /* Test's the basic functionality comparing counts of Amino Acids
     * Creates a linked list and then takes the total amount of AminoAcids
     * (includes repeating), and will take the difference of the two
     * Input: AAGAAAACCACAUCU --> List 1
     * Input: AAACGGUGG --> List 2
     * List 1 --> K K T T S (Count: 5)
     * List 2 --> K R W (Count: 3)
     * 5 - 3 = 2
     * Expected: 2
     */

    @Test
    public void testAminoAcidCompare2(){
        String a = "AAGAAAACCACAUCU";
        String b = "AAACGGUGG";
        AminoAcidLL list1 = new AminoAcidLL();
        AminoAcidLL list2 = new AminoAcidLL();
        list1 = list1.createFromRNASequence(a);
        list2 = list2.createFromRNASequence(b);
        assertEquals(list1.aminoAcidCompare(list2),2 );
    }

    /* Test's the basic functionality comparing counts of Amino Acids
     * Creates a linked list and then takes the total amount of AminoAcids
     * (includes repeating), and will take the difference of the two
     * Input: AAGAAAACCACAUCU --> List 1
     * Input: AAACGGUGG --> List 2
     * List 1 --> K             T               S
     *           [1,1] [0, 1, 1, 0, 0, 0] [0, 0, 0, 0, 0, 1]
     * List 2 --> K           R            W
     *           [0,1] [0, 0, 1, 0, 0, 0] [1]
     * K: 2 - 1 = 1; T: 2 - 1 = 1; S: 1; W: 1;
     * CodonDiff = 1 + 1 + 1 + 1 = 4
     * Expected: 4
     */

    @Test
    public void testCodonCompare1(){
        String a = "AAGAAAACCACAUCU";
        String b = "AAACGGUGG";
        AminoAcidLL list1 = new AminoAcidLL();
        AminoAcidLL list2 = new AminoAcidLL();
        list1 = list1.createFromRNASequence(a);
        list2 = list2.createFromRNASequence(b);
        assertEquals(list1.codonCompare(list2), 4);
    }

    /* Test's the basic functionality comparing counts of Amino Acids
     * Creates a linked list and then takes the total amount of AminoAcids
     * (includes repeating), and will take the difference of the two
     * Input: AAGAAAACCACAUCU --> List 1
     * Input: GGUGGAGCUGGU --> List 2
     * List 1 --> E         G              A       K
     *           [1,1] [1, 0, 0, 0] [0, 0, 1, 0] [1,0]
     * List 2 --> G1            A1          G2          A2
     *           [0,1,0,2] [0, 0, 0, 1] [0,1,0,2] [0, 0, 0, 1]
     * E: 2; G1: 1 - 3 = 2; A2: 1- 1 = 0; K:1; G2: 3 - 1 = 2; A2: 1- 1 =0;
     * CodonDiff = 2 + 2 + 0 + 1 + 2 = 7
     * Expected: 7
     */

    @Test
    public void testCodonCompare2(){
        String a = "GAGGAAGGGGCCAAG";
        String b = "GGUGGAGCUGGU";
        AminoAcidLL list1 = new AminoAcidLL();
        AminoAcidLL list2 = new AminoAcidLL();
        list1 = list1.createFromRNASequence(a);
        list2 = list2.createFromRNASequence(b);
        System.out.println(list1.aminoAcidCompare(list2));
        assertEquals(list1.codonCompare(list2), 7);
    }

    /* Test's the basic functionality on how to create
    * a Linked List from a RNA sequence
    * Input: GCUCGGCCGAAGGGAAGC
    * Expected: A R P K G S
    * Assert Equals Expected : A
    *   assertEquals will only test if the first node
    *   matches the amino acid from expected. */

    @Test
    public void testCreateFromRNASequence1() {
        String a = "GCUCGGCCGAAGGGAAGC";
        AminoAcidLL list = new AminoAcidLL();
        list.createFromRNASequence(a);
        assertEquals(list.createFromRNASequence(a).aminoAcid, 'A');
    }

    /* Test's the basic functionality on how to create
     * a Linked List from a RNA sequence, testing with other
     * factors like repeating codons and a stop
     * Input: GCUGCUACGGAGCUUCGGAGCCCGUAGGUGGACAAG
     * Expected: A T E L R S P
     * Assert Equals Expected : A
     *   assertEquals will only test if the first node
     *   matches the amino acid from expected. */

    @Test
    public void testCreateFromRNASequence2() {
        String a = "GCUGCUACGGAGCUUCGGAGCCCGUAGGUGGACAAG";
        AminoAcidLL list = new AminoAcidLL();
        assertEquals(list.createFromRNASequence(a).aminoAcid, 'A');
    }

    /* Test's the basic functionality on how to check if the linked list is sorted
     * Creates a linked list first, and then test the isSorted method
     * Checks it with other edge cases like STOP, and repeating aminoacids
     * Input: GCUACGGAGCUUCGGAGCCCGUAGGUGGACAAG
     *  List A T E L R S P (Not Sorted)
     * Expected: False
     */

    @Test
    public void testisSorted() {
        String a = "GCUACGGAGCUUCGGAGCCCGUAGGUGGACAAG";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(a);
        assertEquals(list.isSorted(), false);
    }

    /* Test's the basic functionality on generating a char [] of the amino acids from the linked listt
     * Will first generate a linked list, and then implement the aminoAcidList method
     * Checks it with other edge cases like repeating amino acids
     * Input: GCUACGGAGCUUCGGAGCCCGUAGGUGGACAAGGCUGCUGCU
     * Expected: A T E L R S P V D K
     */

    @Test
    public void testAminoAcidList() {
        String a = "GCUACGGAGCUUCGGAGCCCGGUGGACAAGGCUGCUGCUGCUGCUACG";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(a);
        char [] answer = {'A', 'T', 'E', 'L', 'R', 'S', 'P', 'V', 'D', 'K'};
        assertArrayEquals(list.aminoAcidList(), answer);
    }

    /* Test's the basic functionality on generating a int [] of how many times the aminoacid appears in the linked listt
     * Will first generate a linked list, and then implement the aminoAcidList method
     * Checks it with other edge cases like repeating amino acids
     * Input: GCUACGGAGCUUCGGAGCCCGGUGGACAAGGCUGCUGCUGCUGCUACG
     * Expected: 6 2 1 1 1 1 1 1 1 1
     */

    @Test
    public void testAminoAcidCounts() {
        String a = "GCUACGGAGCUUCGGAGCCCGGUGGACAAGGCUGCUGCUGCUGCUACG";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(a);
        int [] answer = {6,2,1,1,1,1,1,1,1,1};
        assertArrayEquals(list.aminoAcidCounts(), answer);
    }

    /* Test's the basic functionality of sorting a linked list
     * Will first generate a linked list, and then implement the sort method
     * Input: GCUACGGAGCUUCGGAGCCCGUAGGUGGACAAG
     *  List: T E L R S P
     * Output: E L P R S T
     * Assert Equals Expected : E
     *   assertEquals will only test if the first node
     *   matches the amino acid from expected.
     * */

    @Test
    public void testSort() {
        String a = "ACGGAGCUUCGGAGCCCGUAGGUGGACAAG";
        AminoAcidLL list = new AminoAcidLL();
        list = list.createFromRNASequence(a);
        assertEquals(list.sort(list).aminoAcid, 'E');
    }
}


