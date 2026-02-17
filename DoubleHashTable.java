package hw;

public class DoubleHashTable {
    private SpellSimple[] table;
    private int capacity;
    private int size;
    private int steps=0;

    public DoubleHashTable(int capacity) {
        this.capacity=capacity;
        this.table= new SpellSimple[capacity];
        this.size=0;
    }

    //adding new spell to the table using hash funcs
    public boolean put(SpellSimple spell) {
        int h1= hash1(spell.getName());
        int h2= hash2(spell.getName());
        for (int i=0; i<capacity; i++){
            int index = (h1+i*h2)%capacity;
            if (table[index]==null){
                steps=i;
                size++;
                table[index]=spell;
                return true;
            }
        }
            return false;
    }

    //searching for a word by his name
    public String getCastWords(String name) {
        for (int i=0; i<capacity; i++){
            if (table[i]!=null){
                if (table[i].getName().equals(name)){
                    steps=i;
                    return table[i].getWords();
                }

            }
            steps=i;
        }
        return null;
    }

    //number of currently spells in the hash-table;
    public int getSize() {
        return size;
    }

    //return the number of steps from func: put or getcastwords
    public int getLastSteps() { return steps; }

    //calculate the hash1 func value
    private int hash1(String name) {
        int hash1=0;
        for ( char c:name.toCharArray() ){
            hash1= hash1+c*31;
        }
        return hash1%capacity;
    }

    //calculate the hash2 func value
    private int hash2(String name) {
        int hash2=0;
        for (char c:name.toCharArray()){
            hash2=hash2+c*13;
        }
        return (1+hash2%(capacity-2));
    }
}