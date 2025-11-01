package question.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MostReceivedGift {

    public static void main(String[] args) {
        // String[] friends = {"muzi", "ryan", "frodo", "neo"};
        // String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};

        String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};

        // String[] friends = {"a", "b", "c"};
        // String[] gifts = {"a b", "b a", "c a", "a c", "a c", "c a"};
        
        
        System.out.println("solution : " + solution(friends, gifts));
    }

    /*
        Programmers. 가장 많이 받은 선물
        URL : <a href="https://school.programmers.co.kr/learn/courses/30/lessons/258712" />
        ---------------------------

        [문제 설명]
        두 사람이 선물을 주고받은 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
            - 예를 들어 A가 B에게 선물을 5번 줬고, B가 A에게 선물을 3번 줬다면 다음 달엔 A가 B에게 선물을 하나 받습니다.
        두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
            - 선물 지수는 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값입니다.
            - 예를 들어 A가 친구들에게 준 선물이 3개고 받은 선물이 10개라면 A의 선물 지수는 -7입니다. B가 친구들에게 준 선물이 3개고 받은 선물이 2개라면 B의 선물 지수는 1입니다.
              만약 A와 B가 선물을 주고받은 적이 없거나 정확히 같은 수로 선물을 주고받았다면, 다음 달엔 B가 A에게 선물을 하나 받습니다.
            - 만약 두 사람의 선물 지수도 같다면 다음 달에 선물을 주고받지 않습니다.
        
        [제한사항]
        2 ≤ friends의 길이 = 친구들의 수 ≤ 50
            - friends의 원소는 친구의 이름을 의미하는 알파벳 소문자로 이루어진 길이가 10 이하인 문자열입니다.
            - 이름이 같은 친구는 없습니다.
        1 ≤ gifts의 길이 ≤ 10,000
            - gifts의 원소는 "A B"형태의 문자열입니다. A는 선물을 준 친구의 이름을 B는 선물을 받은 친구의 이름을 의미하며 공백 하나로 구분됩니다.
            - A와 B는 friends의 원소이며 A와 B가 같은 이름인 경우는 존재하지 않습니다.
        
        [입출력 예제]
        friends                                         | gifts                                                                                                       | result
        ["muzi", "ryan", "frodo", "neo"]                | ["muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"] | 2
        ["joy", "brad", "alessandro", "conan", "david"] | ["alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"]           | 4
        ["a", "b", "c"]                                 | ["a b", "b a", "c a", "a c", "a c", "c a"]                                                                  | 0
    */
    public static int solution(String[] friends, String[] gifts) {
        
        List<Person> people = Arrays.stream(friends)
                .map(x -> new Person(x, friends))
                .collect(Collectors.toList());
        
        for (String gift : gifts) {
            String[] giftArr = gift.split(" ");

            // 누가 나에게 몇개의 선물을 주었는지 정보 설정
            people.stream()
                    .filter(person -> person.name.equals(giftArr[1]))
                    .findFirst()
                    .ifPresent(person -> person.addSenderCount(giftArr[0]));
            
            // 내가 누구에게 몇개의 선물을 주었는지 정보 설정
            people.stream()
                    .filter(person -> person.name.equals(giftArr[0]))
                    .findFirst()
                    .ifPresent(person -> person.addReceiverCount(giftArr[1]));
        }
        
        int answer = 0;
        
        for (Person person : people) {
            AtomicInteger nextMonthGiftCount = new AtomicInteger(0);
            
            // 내가 선물보낸사람 목록
            person.receiverMap.keySet().forEach(receiverName -> {
                
                if (receiverName.equals(person.name)) return;
                
                int sendCount = person.receiverMap.get(receiverName);               // 리시버에게 보낸 선물 수
                int receiveCount = getGiftCount(person.senderMap, receiverName);    // 리시버로 부터 받은 선물 수
                
                if (sendCount == receiveCount) {
                    int receiverScore = people.stream()                                 // 리시버의 스코어 
                            .filter(sperson -> sperson.name.equals(receiverName))
                            .findFirst()
                            .map(Person::getScore)
                            .orElse(0);

                    if (person.getScore() > receiverScore) {
                        nextMonthGiftCount.incrementAndGet();
                    }
                    
                } else if (sendCount > receiveCount) {
                    nextMonthGiftCount.incrementAndGet();
                }
            });
            
            // System.out.println("name : " + person.name + ", nextMonthGiftCount : " + nextMonthGiftCount);
            
            if (nextMonthGiftCount.get() > answer) {
                answer = nextMonthGiftCount.get();
            }
        }
        
        return answer;
    }
    
    public static int getGiftCount(Map<String, Integer> map, String targetName) {
        AtomicInteger giftCount = new AtomicInteger(0);
        
        map.keySet().forEach(personName -> {
            if (personName.equals(targetName)) {
                giftCount.set(map.get(personName));
            }
        });
        
        return giftCount.get();
    }
    
    public static class Person {
        private final String name;
        private final Map<String, Integer> senderMap = new HashMap<>();   // 누가 나에게 몇개의 선물을 주었는지 정보
        private final Map<String, Integer> receiverMap = new HashMap<>();  // 내가 누구에게 몇개의 선물을 주었는지 정보
        
        public Person(String name, String[] friends) {
            this.name = name;
            
            for (String friend : friends) {
                if (!friend.equals(name)) {
                    this.senderMap.put(friend, 0);
                    this.receiverMap.put(friend, 0);
                }
            }
        }

        public void addSenderCount(String name) {
            this.senderMap.put(name, this.senderMap.getOrDefault(name, 0) + 1);
        }

        public void addReceiverCount(String name) {
            this.receiverMap.put(name, this.receiverMap.getOrDefault(name, 0) + 1);
        }
        
        public int getScore() {
            return receiverMap.values().stream().mapToInt(Integer::intValue).sum() 
                    - senderMap.values().stream().mapToInt(Integer::intValue).sum();
        }
    }
}
