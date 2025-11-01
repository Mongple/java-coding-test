package question.programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayReverse {
    
    public static void main(String[] args) {
        System.out.println("solution : " + Arrays.toString(solution(new int[]{1, 0, 1, 1, 1, 3, 5})));
    }

    /**
     * Programmers. 배열 뒤집기
     * URL : <a href="https://school.programmers.co.kr/learn/courses/30/lessons/120821" />
     * 
     * [문제 설명]
     * 정수가 들어 있는 배열 num_list가 매개변수로 주어집니다. num_list의 원소의 순서를 거꾸로 뒤집은 배열을 return하도록 solution 함수를 완성해주세요.
     * 
     * [제한사항]
     * 1 ≤ num_list의 길이 ≤ 1,000
     * 0 ≤ num_list의 원소 ≤ 1,000
     * 
     */

    public static int[] solution(int[] numList) {
        
        List<Integer> list = Arrays.stream(numList).boxed().collect(Collectors.toList());
        
        Collections.reverse(list);
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
