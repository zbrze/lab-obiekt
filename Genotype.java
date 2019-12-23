import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genotype {
    public ArrayList<Integer> genotype = new ArrayList<>(32);

    public Genotype() {
        Genotype genotype = new Genotype();
        genotype.genotype.add(0);
        genotype.genotype.add(1);
        genotype.genotype.add(2);
        genotype.genotype.add(3);
        genotype.genotype.add(4);
        genotype.genotype.add(5);
        genotype.genotype.add(6);
        genotype.genotype.add(7);
        for (int i = 8; i < 32; i++) {
            int randomGen = (int) (Math.random() * 7 + 0);
            genotype.genotype.add(randomGen);
        }
    }

    public Genotype(Genotype mum, Genotype dad) {
        Random random = new Random();
        int mumIndex = random.nextInt(32);
        int dadIndex;
        do{
            dadIndex = random.nextInt(32);
        }while(mumIndex == dadIndex);

        if(mumIndex>dadIndex){
            int tmp=mumIndex;
            mumIndex=dadIndex;
            dadIndex=tmp;
        }
        for(int i = 0; i< 32; i++){
            if(i <= mumIndex || i >= dadIndex){
                int dna = mum.genotype.get(i);
                this.genotype.set(i, dna);
            }
            else{
                int dna = dad.genotype.get(i);
                this.genotype.set(i, dna);
            }
        }
        this.VerifyGenotype();
    }
 public void VerifyGenotype(){
        Random random = new Random();
        int [] typesOfGenes = new int[8];
        for(int i = 0; i < 32; i++){
            typesOfGenes[this.genotype.get(i)]++;
        }
        for(int i = 0; i < 8; i++){
            if(typesOfGenes[i] <= 0){
                int dna;
                do{
                    dna = random.nextInt(8);
                }while (typesOfGenes[dna] < 2);
                typesOfGenes[dna]--;
                typesOfGenes[i]++;
            }
        }
        int c = 0;
        for(int i=0; i<8; i++){
            while (typesOfGenes[i] --!= 0){
                this.genotype.set(c++, i);
            }
        }
 }

    public Directions get() {
        int randomIndex = new Random().nextInt(32);
        int randomGene=  genotype.get(randomIndex);
        Directions direction = Directions.toDirection(randomGene);
        return direction;
    }
}
