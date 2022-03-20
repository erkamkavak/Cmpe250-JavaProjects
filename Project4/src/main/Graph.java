import java.util.*;

public class Graph {
    private HashMap<Integer, HashSet<Vehicle>> vehiclesByTypes;
    private ArrayList<Edge> edges;
    private HashMap<Integer, ArrayList<Integer>> adjList;
    private int[] level;
    private int[] prevInd;
    int currSize = 1;
    int source, sink;
    int m = 0;

    public Graph(int V) {
        this.edges = new ArrayList<>();
        this.level = new int[V];
        this.prevInd = new int[V];
        this.adjList = new HashMap<>();
        for(int i = 0; i < V; i++) adjList.put(i, new ArrayList<>());
        this.vehiclesByTypes = new HashMap<>();
        for(int i = 0; i < 9; i++) vehiclesByTypes.put(i, new HashSet<>());
        source = 0;
        sink = V - 1;
    }

    public void addEdge(int v, int u, int capacity) {
        edges.add(new Edge(v, u, capacity));
        edges.add(new Edge(u, v, 0));
        adjList.get(v).add(m++);
        adjList.get(u).add(m++);
    }

    public void addVehicle(Vehicle vehicle) {
        int id = currSize;
        currSize++;
        vehicle.setId(id);

        int capacity = vehicle.getCapacity();
        addEdge(id, sink, capacity);

        vehiclesByTypes.get(Region.NO_REGION + Vehicle.NO_TYPE).add(vehicle);
        vehiclesByTypes.get(Region.NO_REGION + vehicle.getType()).add(vehicle);
        vehiclesByTypes.get(vehicle.getRegion() + Vehicle.NO_TYPE).add(vehicle);
        vehiclesByTypes.get(vehicle.getRegion() + vehicle.getType()).add(vehicle);
    }


    public void addBag(Bag bag) {
        int id = currSize;
        currSize++;
        bag.setId(id);

        int capacity = bag.getNumberOfGifts();
        addEdge(source, id, capacity);

        if(bag.isDistributed()) {
            capacity = 1;
        }
        else{
            capacity = bag.getNumberOfGifts();
        }

        for(Vehicle vehicle : vehiclesByTypes.get(bag.getRegion() + bag.getVehicleType()) ){
            if(bag.isDistributed()) {
                capacity = vehicle.getSize(); 
            }
            else{
                capacity = bag.getNumberOfGifts(); 
            }
            addEdge(id, vehicle.getId(), capacity);

        }
    }


    public boolean bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        while(!queue.isEmpty()) {
            int v = queue.poll();

            for(int edgeId : adjList.get(v) ) {
                Edge edge = edges.get(edgeId);
                if(edge.getCapacity() <= edge.getFlow()) {
                    continue;
                }
                if(level[edge.getDest()] != -1) {
                    continue;
                }
                queue.add(edge.getDest());
                level[edge.getDest()] = level[edge.getSrc()] + 1;
            }
        }
        return level[sink] != -1;
    }

    public int dfs(int v, int currentFlow) {
        if(currentFlow == 0) {
            return 0;
        }
        if (v == sink){
            return currentFlow;
        }
        // If we tried a node in dfs, we don't want to reuse this node again until we create a new level order. Hence we
        // increase prevInd value to prevent using same node again and again.
        for(int id = prevInd[v]; id < adjList.get(v).size(); prevInd[v]++, id++) {
            int edgeId = adjList.get(v).get(id);
            Edge edge = edges.get(edgeId);
            if(level[v] + 1 != level[edge.getDest()] || edge.getCapacity() <= edge.getFlow() ) {
                continue;
            }
            int pushed = dfs(edge.getDest(), Math.min(currentFlow, edge.getCapacity() - edge.getFlow()));
            if (pushed == 0) continue;
            edge.increaseFlow(pushed);
            edges.get(edgeId ^ 1).increaseFlow(- pushed);
            return pushed;
        }
        return 0;
    }

    public int maxFlow() {
        int flow = 0;
        while(true) {
            Arrays.fill(level, -1);
            level[source] = 0;
            if( !bfs() ) {
                break;
            }
            Arrays.fill(prevInd, 0);
            int pushed = dfs(source, Integer.MAX_VALUE);
            while(pushed != 0){
                flow += pushed;
                pushed = dfs(source, Integer.MAX_VALUE);
            }
        }

        return flow;
    }

}


class Edge {
    private int src;
    private int dest;
    private int capacity;
    private int flow;

    public Edge(int src, int dest, int capacity) {
        this.src = src;
        this.dest = dest;
        this.capacity = capacity;
        this.flow = 0;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public void increaseFlow(int flow) { this.flow += flow; }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public String toString() {
        return this.getSrc() + ":"+ this.getDest();
    }
}