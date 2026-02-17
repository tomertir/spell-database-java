package hw;


import java.util.LinkedList;
import java.util.List;

public class HashAVLSpellTable {
    private LinkedList<AVLTree> buckets[];
    private int tableSize;
    private int numSpells;

    public HashAVLSpellTable(int size) {
        this.tableSize=size;
        this.numSpells=0;
        this.buckets = new LinkedList[tableSize];
        for (int i=0; i<tableSize; i++){
            buckets[i]= new LinkedList<>();
        }
    }
    //calculate the hash func value
    private int hash(String category) {
        int hash=0;
        for (char c:category.toCharArray()){
            hash=hash+c;
        }
        return (hash%tableSize);
    }


    //add new spell to the data structure
    public void addSpell(Spell s) {
        int i=hash(s.getCategory());
        numSpells++;
        if (buckets[i].isEmpty()){
            buckets[i].add(new AVLTree(s));
            return;
        }
        else{
            for (int j=0; j<buckets[i].size();j++){
                if ((buckets[i].get(j).getCategory()).equals(s.getCategory())){
                    buckets[i].get(j).insert(s);
                    return;
                }
            }
            buckets[i].add(new AVLTree(s));
        }
    }

    //return the spell if exists
    public Spell searchSpell(String category, String spellName, int powerLevel) {
        int i=hash(category);
        if (buckets[i].isEmpty()){
            return null;
        }
        for (int j=0; j<buckets[i].size();j++){
            if ((buckets[i].get(j).getCategory()).equals(category)){
                return(buckets[i].get(j)).search(spellName,powerLevel) ;
            }
        }
        return null;
    }
    //get the num of spells (from all data structure)
    public int getNumberSpells(){
        return numSpells;
    }

    //get the num of spells in category
    public int getNumberSpells(String category){
        int i=hash(category);
        if (buckets[i].isEmpty()) {
            return 0;
        }
        for (int j=0; j<buckets[i].size();j++){
            if ((buckets[i].get(j).getCategory()).equals(category)){
                return(buckets[i].get(j)).getSize();
            }
        }
        return 0;
    }

    //get the top k-th spell in the category (largest power level)
    public List<Spell> getTopK(String category,int k) {
        int i=hash(category);
        if (buckets[i].isEmpty()){
            return null;
        }
        for (int j=0; j<buckets[i].size();j++) {
            if ((buckets[i].get(j).getCategory()).equals(category)) {
                return buckets[i].get(j).getTopK(k);
            }
        }
        return null;
    }
}
