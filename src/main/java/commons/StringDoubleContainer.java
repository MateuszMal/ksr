package commons;

public class StringDoubleContainer implements Comparable<StringDoubleContainer> { //zmienic nazwe - czesto uzywane
    private String word;
    private double occurrence;

    public StringDoubleContainer(String input_string, double input_int) {
        word = input_string;
        occurrence = input_int;
    }

    public String getWord() {
        return word;
    }

    public double getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(double occurrence) {
        this.occurrence = occurrence;
    }

    public int compareTo(StringDoubleContainer k2) {
        if (this.occurrence > k2.occurrence) {
            return 1;
        } else if (this.occurrence == k2.occurrence) {
            return 0;
        } else {
            return -1;
        }
    }
}
