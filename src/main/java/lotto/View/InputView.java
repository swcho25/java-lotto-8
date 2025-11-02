package lotto.View;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public String readInput() {
        return Console.readLine();
    }

    public List<Integer> readWinningNumbers() {
        String input = readInput();
        List<Integer> numbers;

        try {
            numbers = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return numbers;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
    }
}
