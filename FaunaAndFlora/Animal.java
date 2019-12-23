package FaunaAndFlora;

import Moving.Directions;
import Moving.IPositionChangeObserver;
import UnlimitedMap.IWorldMap;
import UnlimitedMap.MoveDirection;
import Moving.Vector2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Animal {
    private Vector2d position;
    private Directions direction;
    public int energyLvl;
    private Genotype genotype;
    private IWorldMap map;
    public int energyToReproduce;
    private List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d position, int energy) {
        this.genotype = new Genotype();
        this.map = map;
        this.direction = Directions.NORTH;
        this.energyLvl = 30;
        this.energyToReproduce = 15;
    }

    public Animal(IWorldMap map, Animal mum, Animal dad) {
        this.map = map;
        this.position = mum.position;
        this.genotype = new Genotype(mum.genotype, dad.genotype);
        this.energyLvl = 20;
    }


    public int getEnergy() {
        return this.energyLvl;
    }

    public Vector2d getPosition() {
        return position;
    }

    public boolean canProcreate() {
        return energyLvl > 10;
    }

    public boolean isDead() {
        return this.energyLvl <= 0;
    }

    public Animal procreate(Animal dad) {
        Animal newborn = new Animal(this.map, this, dad);
        this.energyLvl = -10;
        dad.energyLvl = -10;
        this.genotype = new Genotype(this.genotype, dad.genotype);
        return newborn;
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public void positionChanged() {
        for (Iterator<IPositionChangeObserver> it =
             observers.iterator(); it.hasNext(); ) {
            IPositionChangeObserver o = it.next();
        }
    }

  /*  public void move() {
       Moving.Vector2d movement =  genotype.get();
       Moving.Vector2d newPosition = this.position.add(movement);
       this.position= newPosition;
        this.energyLvl -= this.map.getmovementEnergy();
    }*/
    public void move (MoveDirection direction) {
        Directions movement =  genotype.get();

        switch (direction) {
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction= this.direction.previous();
                break;
            case FORWARD:
                Vector2d delta = Directions.toUnitVector(this.direction);

                if (map.canMoveTo(this.position.add(delta))) {
                    this.position = this.position.add(delta);
                    for(IPositionChangeObserver o:observers){
                        o.positionChanged(this.position,delta);
                    }
                }
                break;
            case BACKWARD:
                Vector2d deltaB = this.position.opposite();
                if (map.canMoveTo(this.position.add(deltaB))) {
                    this.position = this.position.add(deltaB);
                }
                for(IPositionChangeObserver o:observers) {
                    o.positionChanged(this.position, deltaB);
                }
                break;
        }
    }
    public void noutrition(int calories){
        this.energyLvl += calories;
    }
    public String toString(){
        if(isDead()) return "✝";
        else return this.direction.toString();
    }
}
