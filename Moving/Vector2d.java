package Moving;

public class Vector2d {
    public int x;
    public int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean precedes (Vector2d other){
        return this.x<=other.x && this.y<=other.y;
    }

    public boolean follows (Vector2d other){

        return this.x>=other.x && this.y>=other.y;
    }
    public Vector2d upperRight(Vector2d other){
        //Moving.Vector2d v=new Moving.Vector2d();
        int vx;
        int vy;
        if(other.x>this.x) vx=other.x; else vx=this.x;
        if(other.y>this.y) vy=other.y; else vy=this.y;
        return new Vector2d(vx,vy);
    }
    public Vector2d lowerLeft(Vector2d other){
        int vx;
        int vy;
        if(other.x<this.x) vx=other.x; else vx=this.x;
        if(other.y<this.y) vy=other.y; else vy=this.y;
        return new Vector2d(vx, vy);
    }
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x,this.y+other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x,this.y-other.y);
    }
    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return (this.x==that.x&&this.y==that.y);
    }
    public Vector2d opposite(){
        return new Vector2d((-1)*this.x,(-1)*this.y);
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }


}
