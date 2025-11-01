package question.programmers;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class StringSort2 {
    
    public static void main(String[] args) {
        System.out.println("solution : " + solution("Bcad"));
    }

    /**
     * Programmers. 문자열 정렬하기2
     * URL : <a href="https://school.programmers.co.kr/learn/courses/30/lessons/120911" />
     * 
     * [문제 설명]
     * 영어 대소문자로 이루어진 문자열 my_string이 매개변수로 주어질 때, my_string을 모두 소문자로 바꾸고 알파벳 순서대로 정렬한 문자열을 return 하도록 solution 함수를 완성해보세요.
     * 
     * [제한사항]
     * 0 < my_string 길이 < 100
     * 
     */
    
    public static String solution(String myString) {
        return Arrays.stream(myString.toLowerCase(Locale.ROOT).split(""))
                .sorted()
                .collect(Collectors.joining());
    }
}
