public class Bag {
    private String type;
    private int numberOfGifts;

    private int id;
    private int region;
    private int vehicleType;
    private boolean isDistributed;

    public Bag(String type, int numberOfGifts) {
        this.type = type;
        this.numberOfGifts = numberOfGifts;

        this.isDistributed = false;
        this.region = Region.NO_REGION;
        this.vehicleType = Vehicle.NO_TYPE;
        for(char c : type.toCharArray()) {
            switch (c) {
                case 'a':
                    isDistributed = true;
                    break;
                case 'b':
                    this.region = Region.GREEN;
                    break;
                case 'c':
                    this.region = Region.RED;
                    break;
                case 'd':
                    this.vehicleType = Vehicle.TRAIN_TYPE;
                    break;
                case 'e':
                    this.vehicleType = Vehicle.REINDEER_TYPE;
                    break;
                default:
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfGifts() {
        return numberOfGifts;
    }

    public void setNumberOfGifts(int numberOfGifts) {
        this.numberOfGifts = numberOfGifts;
    }

    public void increaseNumberOfGifts(int numberOfGifts) { this.numberOfGifts += numberOfGifts; }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isDistributed() {
        return isDistributed;
    }

    public void setDistributed(boolean distributed) {
        isDistributed = distributed;
    }
}
