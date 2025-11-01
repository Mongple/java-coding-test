package question.programmers;

import java.util.*;

public class StrangeSort {
    
    public static void main(String[] args) {
        int[] numList = {1, 2, 3, 4, 5, 6};
        int n = 4;
        
        System.out.println("solution : " + Arrays.toString(solution2(numList, n)));
    }

    /**
     * Programmers. 특이한 정렬
     * URL : <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12914" />
     * 
     * [문제 설명]
     * 정수 n을 기준으로 n과 가까운 수부터 정렬하려고 합니다. 
     * 이때 n으로부터의 거리가 같다면 더 큰 수를 앞에 오도록 배치합니다. 
     * 정수가 담긴 배열 numlist와 정수 n이 주어질 때 numlist의 원소를 n으로부터 가까운 순서대로 정렬한 배열을 return하도록 solution 함수를 완성해주세요.
     * 
     * [제한사항]
     * 1 ≤ n ≤ 10,000
     * 1 ≤ numlist의 원소 ≤ 10,000
     * 1 ≤ numlist의 길이 ≤ 100
     * numlist는 중복된 원소를 갖지 않습니다.
     * 
     */
    
    public int[] solution(int[] numList, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        
        // key : num, value : 거리
        for (int num : numList) {
            map.put(num, Math.abs(num - n));
        }
        
        List<Integer> keyList = new ArrayList<>(map.keySet());

        keyList.sort((key1, key2) -> {
            
            // 거리가 같으면 키(배열 요소)가 큰수로 정렬
            if (map.get(key1).equals(map.get(key2))) {
                return key1 > key2 ? -1 : 1;
            }
            
            return map.get(key1).compareTo(map.get(key2));
        });
        
        return keyList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] solution2(int[] numList, int n) {
        
        return Arrays.stream(numList)
                .boxed()
                .sorted((o1, o2) -> {
                    int distance1 = Math.abs(o1 - n);
                    int distance2 = Math.abs(o2 - n);

                    // 거리가 같으면 numList 요소가 큰거 (-1)
                    if (distance1 == distance2) {
                        return o1 > o2 ? -1 : 1;
                    }
                    
                    // 거리가 짧은면 (-1)
                    return distance1 > distance2 ? 1 : -1;
                })
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
