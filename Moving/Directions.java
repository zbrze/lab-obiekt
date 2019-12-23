package Moving;

public enum Directions {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public static Directions toDirection(int x) {
        switch (x){
            case 0:
                return NORTH;
            case 1:
                return NORTHEAST;
            case 2:
                return EAST;
            case 3:
                return SOUTHEAST;
            case 4:
                return  SOUTH;
            case 5:
                return SOUTHWEST;
            case 6:
                return WEST;
            case 7:
                return NORTHWEST;

        }
        return null;
    }

    public static Vector2d toUnitVector(Directions direction) {
        {
            switch (direction) {
                case EAST:
                    return new Vector2d(1, 0);
                case NORTHEAST:
                    return new Vector2d(1, 1);
                case SOUTHEAST:
                    return new Vector2d(1, -1);
                case WEST:
                    return new Vector2d(-1, 0);
                case NORTHWEST:
                    return new Vector2d(-1, 1);
                case SOUTHWEST:
                    return new Vector2d(-1, -1);
                case SOUTH:
                    return new Vector2d(0, -1);
                case NORTH:
                    return new Vector2d(0, 1);
            }
            return null;
        }
    }

    public String toString(){
    switch (this){
        case NORTH:
            return "↑";
        case NORTHEAST:
            return "↗";
        case EAST:
            return "→";
        case SOUTHEAST:
            return "↘";
        case SOUTH:
            return "↓";
        case SOUTHWEST:
            return "↙";
        case WEST:
            return "←";
        case NORTHWEST:
            return "↖";

    }
 return null;
}

public Directions previous(){
       switch (this) {
        case NORTH:
            return NORTHWEST;
        case SOUTH:
            return SOUTHEAST;
        case WEST:
            return SOUTHWEST;
        case EAST:
            return NORTHEAST;
        case NORTHWEST:
            return WEST;
        case SOUTHWEST:
            return SOUTH;
        case NORTHEAST:
            return NORTH;
        case SOUTHEAST:
            return EAST;
    }
        return null;
}

public Directions next(){
    switch (this) {
        case NORTH:
            return NORTHWEST;
        case SOUTH:
            return SOUTHEAST;
        case WEST:
            return SOUTHWEST;
        case EAST:
            return NORTHEAST;
        case NORTHWEST:
            return WEST;
        case SOUTHWEST:
            return SOUTH;
        case NORTHEAST:
            return NORTH;
        case SOUTHEAST:
            return EAST;
    }
    return null;
}
}
