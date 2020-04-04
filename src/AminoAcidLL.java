import java.util.Arrays;

class AminoAcidLL {
    char aminoAcid;
    String[] codons;
    int[] counts;
    AminoAcidLL next;


    AminoAcidLL() {

    }

    /********************************************************************************************/
    /* Creates a new node, with a given amino acid/codon
     * pair and increments the codon counter for that codon.
     * NOTE: Does not check for repeats!! */
    AminoAcidLL(String inCodon) {
        aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
        codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
        counts = new int[codons.length];
        incrementCodons(inCodon);
        next = null;

    }

    /********************************************************************************************/
    /* Recursive method that increments the count for a specific codon:
     * If it should be at this node, increments it and stops,
     * if not passes the task to the next node.
     * If there is no next node, add a new node to the list that would contain the codon.
     */
    private void addCodon(String inCodon) {
        //base case
        if (aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon)){
            incrementCodons(inCodon);
        }
        else {
            if (next != null) {
                //recursive case
                next.addCodon(inCodon);
            } else {
                // adds codon to list
                AminoAcidLL aminoacid = new AminoAcidLL(inCodon);
                next = aminoacid;
            }
        }
    }

    // helper method to increment counts of codon
    private void incrementCodons(String inCodon) {
        for (int i = 0; i < codons.length; i++) {
            if (codons[i].equals(inCodon)) {
                counts[i]++;
            }
        }
    }


    /********************************************************************************************/
    /* Shortcut to find the total number of instances of this amino acid */
    private int totalCount() {
        //adds all the counts in the counts array
        int sum = 0;
        for (int i = 0; i < counts.length; i++) {
            sum += counts[i];
        }
        return sum;
    }


    /********************************************************************************************/
    /* helper method for finding the list difference on two matching nodes
     *  must be matching, but this is not tracked */
    private int totalDiff(AminoAcidLL inList) {
        return Math.abs(totalCount() - inList.totalCount());
    }

    /********************************************************************************************/
    /* helper method for finding the list difference on two matching nodes
     *  must be matching, but this is not tracked */
    private int codonDiff(AminoAcidLL inList) {
        int diff = 0;
        for (int i = 0; i < codons.length; i++) {
            diff += Math.abs(counts[i] - inList.counts[i]);
        }
        return diff;
    }

    /********************************************************************************************/
    /* Recursive method that finds the differences in **Amino Acid** counts.
     * the list *must* be sorted to use this method */
    public int aminoAcidCompare(AminoAcidLL inList) {
        //Checks if Linked List is sorted
        if (inList.isSorted() == false) {
            //if not sorted, sorts the list
            inList = sort(inList);
        }
        // base case
        // if both lists are at their end takes the difference of counts of each Amino acid
        if (next == null && inList.next == null) {
            return totalDiff(inList);
        }

        //if the first list reaches its end, while the other one does not
        // takes the total count of inList and adds/recursively calls codonCompare
        if (next == null) {
            return inList.totalCount() + aminoAcidCompare(inList);
        }
        // if inList reaches its end
        // takes the total count of the original list
        // and adds it to the comparison(int) between the original list and inList
        if (inList.next == null) {
            return totalCount() + next.aminoAcidCompare(inList);
        }
        // if neither end reaches its end adds the totalDif of counts with
        // the comparison between the original list and the next position in inList
        // (iterates through the rest if the list)
        return totalDiff(inList) + next.aminoAcidCompare(inList.next);


    }

    /********************************************************************************************/
    /* Same ad above, but counts the codon usage differences
     * Must be sorted. */
    public int codonCompare(AminoAcidLL inList) {
        // Checks if Linked List is sorted
        if (!inList.isSorted()) {
            //if not sorted, sorts the list
            inList = sort(inList);
        }
        //base case
        // if both lists are in the same length and takes the difference of the total number of counts
        if (inList.next == null && next == null) {
            return totalDiff(inList);
        }
        // if first list reaches its
        if (next == null) {
            return inList.totalCount() + codonCompare(inList);
        }
        if (inList.next == null) {
            return totalCount() + next.codonCompare(inList);
        }
        if(aminoAcid < inList.aminoAcid){
            return totalCount() + next.codonCompare(inList);
        }
        if(aminoAcid > inList.aminoAcid){
            return inList.totalCount() + codonCompare(inList.next);
        }
        return codonDiff(inList) + next.codonCompare(inList.next);
    }


    /********************************************************************************************/
    /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
    public char[] aminoAcidList() {
        // if list is empty or points to null returns default aminoAcid
        if (next == null) {
            return new char[]{aminoAcid};
        }
        else{
            //initializes a char [] that recursively calls the next position of the list (iterates through list)
            // * does not start at the start of the list*
            char[] current = next.aminoAcidList();
            // creates empty char [] that will store the Amino Acids, uses the length of the list of the current
            // * adds one to the length, since current does not begin at the start of the list*
            char[] aminoAcidList = new char[current.length + 1];
            // iterates through the aminoAcidList and then assigns each index to the index of the char [] current
            for (int i = 0; i < current.length; i++) {
                //assigns the first position of the amino acid [] to the first amino acid in the list
                aminoAcidList[0] = aminoAcid;
                // does this for the rest of the positions not including 0
                aminoAcidList[i + 1] = current[i];
            }
            //printArray(aminoAcidList);
            return aminoAcidList;
        }
    }

    private static void printArray(char [] a){
        for(int i= 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }


    /********************************************************************************************/
    /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
    public int[] aminoAcidCounts() {
        if (next == null) {
            return new int []{totalCount()};
        }
        else{
            //does the same as aminoAcidList
            // calls aminoAcid counts recursively
            int[] current = next.aminoAcidCounts();
            int[] aminoAcidCounts = new int[current.length + 1];
            for (int i = 0; i < current.length; i++) {
                aminoAcidCounts[0] = totalCount();
                aminoAcidCounts[i + 1] = current[i];
            }
            //printArray(aminoAcidCounts);
            return aminoAcidCounts;
        }
    }

    private static void printArray(int [] a){
        for(int i= 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /********************************************************************************************/
    /* recursively determines if a linked list is sorted or not */
    public boolean isSorted() {
        //base case
        if (next == null) {
            return true;
        }
        //checks if the ASCII value of the first element of the list is greater than the next element of the list
        if (aminoAcid > next.aminoAcid) {
            return false;
        }
        //recursive call
        return next.isSorted();
    }


    /********************************************************************************************/
    /* Static method for generating a linked list from an RNA sequence */

    public static AminoAcidLL createFromRNASequence(String inSequence) {
        // creates new Linked List with the first three characters of inSequence
        AminoAcidLL list = new AminoAcidLL(inSequence.substring(0, 3));
        //updates the string inSequence to include the rest of the string excluding the first three chars
        inSequence = inSequence.substring(3);
        //uses for loop to iterate through the string and take substrings of every three letter sequence
        for (int i = 0; i < inSequence.length(); i++) {
            // checks if the sequence does not have a stop, and if it does breaks out of the loop
            if(AminoAcidResources.getAminoAcidFromCodon(inSequence.substring(0,3)) == '*'){
                break;
            }
            else{
                // adds the Amino Acid for ever three letter sequence
                list.addCodon(inSequence.substring(0, 3));
                //updates inSequence same as above
                inSequence = inSequence.substring(3);
                i--;
            }
            //decreases the iterator to avoid out-of-bounds exceptions, and to avoid missing the last codon of the string

        }
        printList(list);
        return list;
    }


    /********************************************************************************************/
    /* sorts a list by amino acid character*/
    public static AminoAcidLL sort(AminoAcidLL inList) {
        if (inList.isSorted() == true) {
            return inList;
        }
        else {
            //takes the place of a while loop and iterates through the linked list
            for (AminoAcidLL i = inList; i.next != null; i = i.next) {
                for (AminoAcidLL j = i.next; j != null; j = j.next) {
                    // uses selection sort to swap
                    if (i.aminoAcid > j.aminoAcid) {
                        //swap takes place
                        char temp = i.aminoAcid;
                        i.aminoAcid = j.aminoAcid;
                        j.aminoAcid = temp;
                    }
                }
            }
            //printList(inList);
            return inList;
        }
    }


    private static void printList(AminoAcidLL head) {
        while (head != null) {
            System.out.print(head.aminoAcid + " ");
            //System.out.print(Arrays.toString(head.codons)+ " ");
            System.out.print(Arrays.toString(head.counts)+ " ");

            head = head.next;
        }
        System.out.println();
    }

}