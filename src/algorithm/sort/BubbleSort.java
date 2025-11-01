package algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        System.out.println("solution : " + Arrays.toString(solution(new int[]{8, 5, 7, 2, 4})));
    }

    /**
     * 두개의 데이터를 비교하여 크기에 따라 위치를 서로 교하는 정렬 방식
     */
    public static int[] solution(int[] data) {
        int compareCount = 0;
        
        for (int i = 0; i < data.length - 1; i++) {
            int sortCount = 0;
            
            // data.length - 1 - i 반복 횟차가 늘어날 수록 sort 줄어들도록
            for (int j = 0; j < data.length - 1 - i; j++) {
                compareCount++;
                
                int d1 = data[j];
                int d2 = data[j + 1];

                if (d1 > d2) {
                    data[j] = d2;
                    data[j + 1] = d1;
                    sortCount++;
                }
                
                System.out.println("compareCount : " + compareCount + ", sort result : " + Arrays.toString(data));
            }
            
            // 변경 없으면 다음 횟차 실행 x
            if (sortCount == 0) break;
        }
        
        return data;
    }
}
