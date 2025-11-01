package algorithm.search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] data = {4,2,3,1,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int target = 6;
        System.out.println("solution : " + solution(data, target));
    }
    
    public static int solution(int[] data, int target) {
        int searchStart = 0;
        int searchEnd = data.length - 1;
        int mid;
        int loopCount = 0;
        
//        문제에서 배열의 순서를 보장하지 않을 때
//        Arrays.sort(data);    // O(n)
        
        // O(logN)
        while (searchStart <= searchEnd) {
            
            mid = searchStart + (searchEnd - searchStart) / 2;
            
            if (data[mid] == target) {
                return loopCount;
            }
            
            if (data[mid] < target) {
                searchStart = mid + 1;
            } else {
                searchEnd = mid - 1;
            }

            loopCount++;
        }
        
        return -1;
    }
}
