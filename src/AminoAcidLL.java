class AminoAcidLL {
    private char aminoAcid;
    private String[] codons;
    private int[] counts;
    private AminoAcidLL next;


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
        if (AminoAcidResources.getAminoAcidFromCodon(inCodon) == aminoAcid) {
            incrementCodons(inCodon);
        } else {
            if (next != null) {
                next.addCodon(inCodon);
            } else {
                AminoAcidLL aminoacid = new AminoAcidLL(inCodon);
                next = aminoacid;
            }
        }
    }

    // helper method to increment counts of codon
    public int[] incrementCodons(String inCodon) {
        for (int i = 0; i < codons.length; i++) {
            if (inCodon.equals(codons[i])) {
                counts[i]++;
            }
        }
        return counts;
    }


    /********************************************************************************************/
    /* Shortcut to find the total number of instances of this amino acid */
    private int totalCount() {
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

        return 0;
    }

    /********************************************************************************************/
    /* Same ad above, but counts the codon usage differences
     * Must be sorted. */
    public int codonCompare(AminoAcidLL inList) {

        return 0;
    }


    /********************************************************************************************/
    /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
    public char[] aminoAcidList() {
        if (next == null) {
            return new char[]{aminoAcid};
        }
        char[] a = next.aminoAcidList();
        char[] aminoAcidList = new char[a.length + 1];
        aminoAcidList[0] = aminoAcid;
        for (int i = 0; i < a.length; i++) {
            aminoAcidList[i + 1] = a[i];
        }
        return aminoAcidList;

    }


    /********************************************************************************************/
    /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
    public int[] aminoAcidCounts() {
        if (next == null) {
            return new int[]{this.totalCount()};
        }
        int[] a = next.aminoAcidCounts();
        int[] aminoAcidCounts = new int[a.length + 1];
        aminoAcidCounts[0] = totalCount();
        for (int i = 0; i < a.length; i++) {
            aminoAcidCounts[i + 1] = a[i];
        }

        return aminoAcidCounts;
    }

    /********************************************************************************************/
    /* recursively determines if a linked list is sorted or not */
    public boolean isSorted() {
        if (next == null) {
            return true;
        }
        if (aminoAcid > next.aminoAcid) {
            return false;
        }
        return next.isSorted();
    }


    /********************************************************************************************/
    /* Static method for generating a linked list from an RNA sequence */
    public static AminoAcidLL createFromRNASequence(String inSequence) {
        AminoAcidLL list = new AminoAcidLL(inSequence.substring(0, 3));
        inSequence = inSequence.substring(3);
        for (int i = 0; i < inSequence.length(); i++) {
            list.addCodon(inSequence.substring(0, 3));
            inSequence = inSequence.substring(3);
            i = i - 1;
        }
        //printList(list);
        return list;
    }


    /********************************************************************************************/
    /* sorts a list by amino acid character*/
    public static AminoAcidLL sort(AminoAcidLL inList) {
        if (inList.isSorted() == true) {
            return inList;
        } else {
            //takes the place of a while loop and iterates through the linked list
            for (AminoAcidLL i = inList; i.next != null; i = i.next) {
                for (AminoAcidLL j = i.next; j != null; j = j.next) {
                    if (i.aminoAcid > j.aminoAcid) {
                        char temp = i.aminoAcid;
                        i.aminoAcid = j.aminoAcid;
                        j.aminoAcid = temp;
                    }
                }
            }
            printList(inList);
            return inList;

        }
    }


    public static void printList(AminoAcidLL head) {
        while (head != null) {
            System.out.print(head.aminoAcid + " ");
            head = head.next;
        }
        System.out.println();
    }
}