package lotto.View;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** 입력을 담당하는 View 클래스 */
public class InputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_NUMBER = ERROR_PREFIX + "숫자만 입력 가능합니다.";
    private static final String SPLIT_DELIMITER = ",";

    /**
     * 콘솔에서 한 줄의 문자열을 입력 받습니다.
     * @return 입력 받은 문자열
     */
    public String readInput() {
        return Console.readLine();
    }

    /**
     * 당첨 번호를 입력 받아서 쉼표 기준으로 분리한 뒤 정수 리스트로 반환합니다.
     * @return 당첨 번호 리스트
     * @throws IllegalArgumentException 숫자가 아닌 값이 포함되어 있는 경우
     */
    public List<Integer> readWinningNumbers() {
        String input = readInput();
        List<Integer> numbers;

        try {
            numbers = Arrays.stream(input.split(SPLIT_DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return numbers;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBER);
        }
    }
}
