package question.programmers;

import java.time.Duration;

public class VideoPlayer {

    public static void main(String[] args) {
//        String videoLen = "34:33";
//        String pos = "13:00";
//        String opStart = "00:55";
//        String opEnd = "02:55";
//        String[] commands = {"next", "prev"};

        String videoLen = "10:55";
        String pos = "00:05";
        String opStart = "00:15";
        String opEnd = "06:55";
        String[] commands = {"prev", "next", "next"};

//        String videoLen = "07:22";
//        String pos = "04:05";
//        String opStart = "00:15";
//        String opEnd = "04:07";
//        String[] commands = {"next"};
        System.out.println("solution : " + solution(videoLen, pos, opStart, opEnd, commands));
    }

    /**
     * # Programmers. 동영상 플레이어
     * URL : <a href="https://school.programmers.co.kr/learn/courses/30/lessons/340213" />
     * 
     * - [문제 설명]
     * 당신은 동영상 재생기를 만들고 있습니다. 당신의 동영상 재생기는 10초 전으로 이동, 10초 후로 이동, 오프닝 건너뛰기 3가지 기능을 지원합니다. 각 기능이 수행하는 작업은 다음과 같습니다.
     *  - 10초 전으로 이동: 사용자가 "prev" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 전으로 이동합니다. 현재 위치가 10초 미만인 경우 영상의 처음 위치로 이동합니다. 영상의 처음 위치는 0분 0초입니다.
     *  - 10초 후로 이동: 사용자가 "next" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 후로 이동합니다. 동영상의 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동합니다. 영상의 마지막 위치는 동영상의 길이와 같습니다.
     *  - 오프닝 건너뛰기: 현재 재생 위치가 오프닝 구간(opStart ≤ 현재 재생 위치 ≤ opEnd)인 경우 자동으로 오프닝이 끝나는 위치로 이동합니다.
     * 
     * 동영상의 길이를 나타내는 문자열 videoLen, 기능이 수행되기 직전의 재생위치를 나타내는 문자열 pos, 오프닝 시작 시각을 나타내는 문자열 opStart, 오프닝이 끝나는 시각을 나타내는 문자열 opEnd, 사용자의 입력을 나타내는 1차원 문자열 배열 commands가 매개변수로 주어집니다. 이때 사용자의 입력이 모두 끝난 후 동영상의 위치를 "mm:ss" 형식으로 return 하도록 solution 함수를 완성해 주세요.
     * 
     * - [제한사항]
     *  - videoLen의 길이 = pos의 길이 = opStart의 길이 = opEnd의 길이 = 5
     *      - videoLen, pos, opStart, opEnd는 "mm:ss" 형식으로 mm분 ss초를 나타냅니다.
     *      - 0 ≤ mm ≤ 59
     *      - 0 ≤ ss ≤ 59
     *      - 분, 초가 한 자리일 경우 0을 붙여 두 자리로 나타냅니다.
     *      - 비디오의 현재 위치 혹은 오프닝이 끝나는 시각이 동영상의 범위 밖인 경우는 주어지지 않습니다.
     *      - 오프닝이 시작하는 시각은 항상 오프닝이 끝나는 시각보다 전입니다.
     *  - 1 ≤ commands의 길이 ≤ 100
     *      - commands의 원소는 "prev" 혹은 "next"입니다.
     *      -"prev"는 10초 전으로 이동하는 명령입니다.
     *      - "next"는 10초 후로 이동하는 명령입니다.
     *      
     * - [입출력 예]
     * videoLen	pos	opStart	opEnd	commands	result
     * "34:33"	"13:00"	"00:55"	"02:55"	["next", "prev"]	"13:00"
     * "10:55"	"00:05"	"00:15"	"06:55"	["prev", "next", "next"]	"06:55"
     * "07:22"	"04:05"	"00:15"	"04:07"	["next"]	"04:17"
     */
    
    
    
    public static String solution(String videoLen, String pos, String opStart, String opEnd, String[] commands) {
        Duration posDuration = toDuration(pos);
        Duration videoStartDuration = toDuration("00:00");
        Duration videoLenDuration = toDuration(videoLen);
        Duration opStartDuration = toDuration(opStart);
        Duration opEndDuration = toDuration(opEnd);
        
        for (String command : commands) {
            // 명령어 처리전 오프닝 시간 체크하여 전처리
            // 오프닝 시작시간보다 처리 후 결과가 이후거나, 오프닝 종료시간보다 처리 후 결과가 이전이면 오프닝종료시간
            if (isInOpening(posDuration, opStartDuration, opEndDuration)) {
                posDuration = opEndDuration;
            }
            
            if (command.equals("next")) {
                posDuration = posDuration.plusSeconds(10);
            } else if (command.equals("prev")) {
                posDuration = posDuration.minusSeconds(10);
            }

            // 명령어 처리후 오프닝 시간 체크하여 후처리
            // 오프닝 시작시간보다 처리 후 결과가 이후거나, 오프닝 종료시간보다 처리 후 결과가 이전이면 오프닝종료시간
            if (isInOpening(posDuration, opStartDuration, opEndDuration)) {
                posDuration = opEndDuration;
            }
            
            if (videoStartDuration.compareTo(posDuration) >= 0) {
                // 비디오시작시간 (00:00) 보다 처리 후 결과가 이전이면 비디오시작시간 return
                posDuration = videoStartDuration;
            }
            
            if (posDuration.compareTo(videoLenDuration) >= 0) {
                // 비디오종료시간보다 처리 후 결과가 이후면 비디오종료시간 return
                posDuration = videoLenDuration;
            }
        }
        
        return toStringWithAnswerFormat(posDuration);
    }

    private static boolean isInOpening(Duration posDuration, Duration opStartDuration, Duration opEndDuration) {
        return opStartDuration.compareTo(posDuration) <= 0 && opEndDuration.compareTo(posDuration) >= 0;
    }

    private static Duration toDuration(String data) {
        String[] dataArr = data.split(":");
        return Duration.ofMinutes(Integer.parseInt(dataArr[0]))
                .plusSeconds(Integer.parseInt(dataArr[1]));
    }

    private static String toStringWithAnswerFormat(Duration duration) {
        return String.format("%02d", duration.toMinutesPart()) 
                + ":" 
                + String.format("%02d", duration.toSecondsPart());
    }
}
