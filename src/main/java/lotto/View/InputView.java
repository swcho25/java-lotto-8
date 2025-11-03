package lotto.View;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_NUMBER = ERROR_PREFIX + "숫자만 입력 가능합니다.";
    private static final String SPLIT_DELIMITER = ",";

    public String readInput() {
        return Console.readLine();
    }

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
