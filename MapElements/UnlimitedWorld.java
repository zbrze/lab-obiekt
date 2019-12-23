package UnlimitedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import FaunaAndFlora.Animal;
import Moving.Vector2d;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
public class UnlimitedWorld implements IWorldMap {
    public List<Animal> animals = new ArrayList<>();
    final Vector2d lowerLeft;
    final Vector2d upperRight;
    final Vector2d jungleLowerLeft;
    final Vector2d jungleUpperRight;
    final int grassCalories;
    final int movementEnergy;
    final int startEnergy;
    public ListMultimap<Vector2d, IMapElement> objects = ArrayListMultimap.create();
    public UnlimitedWorld(int width, int height, int jungleRatio, int grassCalories, int moveEnergy, int startEnergy, int numberOfAncestors){
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
        this.jungleLowerLeft = new Vector2d((width - (width*jungleRatio))/2,  (height - (height*jungleRatio))/2);
        this.jungleUpperRight = new Vector2d(this.jungleLowerLeft.x + (width*jungleRatio), this.jungleLowerLeft.y + (height*jungleRatio));
        this.grassCalories = grassCalories;
        this.movementEnergy = moveEnergy;
        this.startEnergy = startEnergy;
        this.placeAncestors(numberOfAncestors);
    }
    public boolean placeAnimal(Animal animal) {

        this.objects.put(animal.getPosition(), animal);
        this.animals.add(animal);
        animal.addObserver(this);
        return true;
    }

    public void placeAncestors(int numberOfAncestors) {
        int x, y;
        Random random = new Random();
        Vector2d position = null;
        for (int i = 0; i < numberOfAncestors; i++) {
            x = random.nextInt(this.upperRight.x);
            y = random.nextInt(this.upperRight.y);
            position = new Vector2d(x, y);
        }
        while (this.isOccupied(position)) ;
        Animal ancestor = new Animal(this, position, this.startEnergy);

    }
    public boolean canMoveTo(Vector2d position) {
        for (Animal x : animals) {
            x.move();
        }
    }

    public boolean place(Animal animal){

    }

    @Override
    public void run(MoveDirection[] directions) {

    }

    public void procrete(Animal mum, Animal dad){
        if (mum.canProcreate() && dad.canProcreate() && mum.getPosition().equals(dad.getPosition())) {
            mum.procreate(dad);
        }

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return  objectAt(position)!=null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return objects.get(position);
    }

    @Override
    public int getmovementEnergy() {
        return this.movementEnergy;
    }

    public  void  feeding(Animal animal){
        int x = animal.getEnergy();
        x = x + this.grassCalories;
        animal.energyLvl = x;

    }

}
