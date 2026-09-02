class TimeMap {

    class TimeStampedValue{
        public String value;
        public int time;

        public TimeStampedValue(String value, int time){
            this.value = value;
            this.time = time;
        }
    }

    Map<String, ArrayList<TimeStampedValue>> entries;

    public TimeMap() {
        entries = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!entries.containsKey(key)){
            entries.put(key, new ArrayList<>());
        }
        ArrayList<TimeStampedValue> tSV = entries.get(key);
        tSV.add(new TimeStampedValue(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if(!entries.containsKey(key)){
            return "";
        }

        ArrayList<TimeStampedValue> timeStampedValues = entries.get(key);
        Optional<TimeStampedValue> resStamp = binaryStampSearch(timeStampedValues, timestamp);

        if(resStamp.isEmpty()){
            return "";
        }
        return resStamp.get().value;
    }

    public Optional<TimeStampedValue> binaryStampSearch(ArrayList<TimeStampedValue> arr, int target){
        int low = 0;
        int high = arr.size() - 1;
        int res = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            TimeStampedValue cur = arr.get(mid);
            
            // Valid candidate found, record it and search for a closer one
            if(cur.time <= target){
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if(res == -1){
            return Optional.empty();
        }
        return Optional.of(arr.get(res));
    }
}
