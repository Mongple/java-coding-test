package question.programmers;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Bandage {

    /**
     * Programmers. [PCCP 기출문제] 1번 / 붕대 감기 
     * URL : <a href="https://school.programmers.co.kr/learn/courses/30/lessons/250137" />
     */
    public static void main(String[] args) {
        int[] bandage = {5, 1, 5};
        int health = 30;
        int[][] attacks = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};
        System.out.println("solution : " + solution2(bandage, health, attacks));
    }

    public int solution(int[] bandage, int health, int[][] attacks) {
        // int[] bandage = {5, 1, 5}; 시전시간, 초당 회복, t초 연속시 추가 회복 수치

        LinkedHashMap<Integer, Integer> damageMap = getDamageMap(attacks);
        int lastAttackSecond = new ArrayList<>(damageMap.keySet()).get(damageMap.size() - 1);
        int successHealCount = 0;
        int addHealingCount = bandage[0];
        int heal = bandage[1];
        int maxHealth = health;
        
        // 1초 ~ 마지막 공격시간초까지 loop
        for (int i = 1; i <= lastAttackSecond; i++) {
            int damage = damageMap.getOrDefault(i, 0);
            if (damage > 0) {
                health -= damage;
                successHealCount = 0;
//                System.out.println(i + " | health : " + health + ", damage : " + damage + ", heal : 0, successHealCount : " + successHealCount);

                // 죽었을 경우 -1 리턴
                if (health <= 0) {
                    health = -1;
                    break;
                }
                
                continue;
            }

            health += heal;
            successHealCount++;
            
            if (successHealCount == addHealingCount) {
                health += bandage[2];
                successHealCount = 0;
            }

            // 힐 결과가 최대 체력보다 클경우 최대체력으로
            if (maxHealth < health) health = maxHealth;
            
//            System.out.println(i + " | health : " + health + ", damage : " + damage + ", heal : " + heal + ", successHealCount : " + successHealCount);
            
        }
        
        return health;
    }

    public static int solution2(int[] bandage, int health, int[][] attacks) {
        int cnt = bandage[0]; // 추가 체력 기준
        int now = health; // 현재 체력
        int std = 0; // 마지막으로 공격당한 시간

        int v1, v2; // 추가 체력 받을 수 있나?
        for (int[] atk: attacks) {
            if (now <= 0) {
                return -1;
            }

            v1 = atk[0] - std - 1; // 시간 차이
            v2 = v1 / cnt; // 추가 체력 회수

            // 맞기 직전까지의 체력 정산
            std = atk[0];
            now = Math.min(health, now + (v1 * bandage[1]));
            now = Math.min(health, now + (v2 * bandage[2]));

            now -= atk[1];
        }

        return now <= 0 ? -1 : now;
    }
    
    private LinkedHashMap<Integer, Integer> getDamageMap(int[][] attacks) {

        LinkedHashMap<Integer, Integer> damageMap = new LinkedHashMap<>();

        for (int[] attack : attacks) {
            damageMap.put(attack[0], attack[1]);    // 데미지 적용시간을 key, 데미지 value
        }
        
        return damageMap;
    }
}
