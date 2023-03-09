/**
 * Represents a word, that is read from the file
 * A ParagraphFrequency object will have its name and number of occurence
 */
public class ParagraphFrequency implements Comparable<ParagraphFrequency> {
    
    String word;
    Integer wordCount;

    /** 
     * Creates a new ParagraphFrequency with the given word and number of its occurence.
    */
    public ParagraphFrequency(String word, Integer wordCount){
        this.word = word;
        this.wordCount = wordCount;
    }

    /**
     * Changes the word
     * @param newWord This ParagraphFrequency object's new word
     */
    public void setWord(String newWord)
    {
        if(newWord != null)
            this.word = newWord;
    }

    /**
     * Changes the number of this word's number of the occurence
     * @param newWordCount This word's number of the occurence
     */
    public void setWordCount(Integer newWordCount)
    {
        if(newWordCount != null)
            this.wordCount = newWordCount;
    }

    /**
     * Gets the word name of ParagraphFrequency object.
     * @return this word
    */
    public String getWord() {
        return this.word;
    }

    /**
     * Gets the word frequency of ParagraphFrequency object.
     * @return the number of the occurence of this word
    */
    public Integer getWordCount() {
        return this.wordCount;
    }

    @Override
    public String toString(){
        return "Word Frequency [word=" + word + ", word count=" + wordCount + "]";
    }

    @Override
    public int compareTo(ParagraphFrequency otherWord) {
        int compareValue = 1;
        if(this.wordCount>otherWord.wordCount) {
            compareValue = 1;
        }
        if(this.wordCount<otherWord.wordCount) {
            compareValue = -1;
        }
        if(this.wordCount == otherWord.wordCount) {
            compareValue = 0;
        }
        return compareValue;
    }
}
