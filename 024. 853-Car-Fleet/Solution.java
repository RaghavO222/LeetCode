class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        
        // Array to store [position, time_to_target]
        double[][] cars = new double[n][2];

        // Calculate the time it takes for each car to reach the target
        for(int i = 0; i < n; i++){
            cars[i][0] = position[i];
            cars[i][1] = (double)(target - position[i]) / speed[i];
        }

        // Sort cars by starting position in descending order (closest to target first)
        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

        int count = 0;
        double prevTime = 0;
        
        // Iterate through the sorted cars
        for(double[] car: cars){
            // If the current car takes longer than the fleet ahead, it forms a new fleet
            if(car[1] > prevTime){
                count++;
                prevTime = car[1]; // Update the bottleneck time for the cars behind
            }
        }

        return count;
    }
}