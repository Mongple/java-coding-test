package question.programmers;

public class DfsTargetNumber {
    
    /*
        <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43165" />
     */
    
    public static void main(String[] args) {
        int[] numbers = {4, 1, 2, 1};
        int target = 4;
        System.out.println("solution : " + solution(numbers, target));
    }

    public static int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    public static int dfs(int[] numbers, int target, int index, int sum) {
        if (numbers.length == index) {
            return sum == target ? 1 : 0;
        }

        return dfs(numbers, target, index + 1, sum + numbers[index])
                + dfs(numbers, target, index + 1, sum - numbers[index]);
    }
}
