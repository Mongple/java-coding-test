package question.programmers;

import java.util.Arrays;

public class StringSort1 {
    
    public static void main(String[] args) {
        System.out.println("solution : " + Arrays.toString(solution("hi12392")));
    }

    /**
     * Programmers. 문자열 정렬하기1
     * URL : <a href="https://school.programmers.co.kr/learn/courses/30/lessons/120850" />
     * 
     * [문제 설명]
     * 문자열 my_string이 매개변수로 주어질 때, my_string 안에 있는 숫자만 골라 오름차순 정렬한 리스트를 return 하도록 solution 함수를 작성해보세요.
     * 
     * [제한사항]
     * 1 ≤ my_string의 길이 ≤ 100
     * my_string에는 숫자가 한 개 이상 포함되어 있습니다.
     * my_string은 영어 소문자 또는 0부터 9까지의 숫자로 이루어져 있습니다. - - -
     * 
     */
    
    public static int[] solution(String myString) {

        String[] strArr = myString.split("");

        return Arrays.stream(strArr)
                .filter(str -> isNumber(str))
                .sorted()
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch(Exception ignore) {
            return false;
        }
        
        return true;
    }

    public int[] solution2(String myString) {

        String[] strArr = myString.replaceAll("[A-Z|a-z]", "").split("");

        return Arrays.stream(strArr)
                .filter(str -> isNumber(str))
                .sorted()
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
