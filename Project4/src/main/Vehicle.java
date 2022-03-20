public class Vehicle {
    public static int NO_TYPE = 0;
    public static int TRAIN_TYPE = 1;
    public static int REINDEER_TYPE = 2;

    private int id;
    private int capacity;
    private int type;
    private int region;

    private int size; 



    public Vehicle(int capacity, int size, int type, int region){
        this.capacity = capacity;
        this.type = type;
        this.region = region;

        this.size = size; 
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
