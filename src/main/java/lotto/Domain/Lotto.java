package lotto.Domain;

import java.util.List;
import lotto.Service.Validator;

/** 로또 한 장(6개의 숫자)을 표현하는 도메인 클래스 */
public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String ERROR_NUMBER_COUNT = "[ERROR] 로또 번호는 6개여야 합니다.";

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_NUMBER_COUNT);
        }

        Validator.validateDuplicate(numbers);
        Validator.validateRange(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
