package lotto.Service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

/** 로또 관련 입력값의 유효성을 검증하는 클래스 */
public class Validator {
    private static final int LOTTO_PRICE_UNIT = 1000;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_MONEY = ERROR_PREFIX + "구입 금액은 양의 정수여야 합니다.";
    private static final String ERROR_MONEY_UNIT = ERROR_PREFIX + "구입 금액은 1000원 단위여야 합니다.";
    private static final String ERROR_MONEY_NUMBER = ERROR_PREFIX + "구입 금액은 숫자여야 합니다.";
    private static final String ERROR_BONUS_RANGE = ERROR_PREFIX + "보너스 번호는 1부터 45 사이여야 합니다.";
    private static final String ERROR_BONUS_DUPLICATE = ERROR_PREFIX + "보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    private static final String ERROR_BONUS_NUMBER = ERROR_PREFIX + "보너스 번호는 숫자여야 합니다.";
    private static final String ERROR_DUPLICATE = ERROR_PREFIX + "중복된 번호가 존재합니다.";
    private static final String ERROR_RANGE = ERROR_PREFIX + "로또 번호는 1부터 45 사이여야 합니다.";

    /**
     * 구입 금액의 유효성을 검사합니다.
     * 숫자인지 여부 / 양수 여부 / 1000의 배수 여부
     * @param input 사용자 입력 문자열
     * @return 정수 형태의 구입 금액
     * @throws IllegalArgumentException 입력 값이 유효하지 않은 경우
     */
    public static int validateMoney(String input) {
        try {
            int money = Integer.parseInt(input.trim());
            if (money <= 0) throw new IllegalArgumentException(ERROR_INVALID_MONEY);
            if (money % LOTTO_PRICE_UNIT != 0) throw new IllegalArgumentException(ERROR_MONEY_UNIT);
            return money;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MONEY_NUMBER);
        }
    }

    /**
     * 보너스 번호의 유효성을 검사합니다.
     * 숫자인지 여부 / 1~45 사이의 숫자인지 여부 / 당첨 번호와 중복되지 않는지에 대한 여부
     * @param input 보너스 번호 입력 값
     * @param winningNumbers 당첨 번호 리스트
     * @return 검증된 보너스 번호
     * @throws IllegalArgumentException 입력 값이 유효하지 않은 경우
     */
    public static int validateBonusNumber(String input, List<Integer> winningNumbers) {
        try {
            int bonus = Integer.parseInt(input.trim());
            validateBonusRange(bonus);
            validateBonusDuplicate(winningNumbers, bonus);
            return bonus;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER);
        }
    }

    private static void validateBonusRange(int bonus) {
        if (bonus < MIN_NUMBER || bonus > MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_BONUS_RANGE);
        }
    }

    private static void validateBonusDuplicate(List<Integer> winningNumbers, int bonus) {
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException(ERROR_BONUS_DUPLICATE);
        }
    }

    /**
     * 로또 번호 중 중복이 존재하는지 검사
     * @param numbers 로또 번호 리스트
     * @throws IllegalArgumentException 중복된 번호가 존재할 경우
     */
    public static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    /**
     * 로또 번호가 1~45 사이의 숫자인지 검사
     * @param numbers 로또 번호 리스트
     * @throws IllegalArgumentException 유효 범위를 벗어난 번호가 존재할 경우
     */
    public static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException(ERROR_RANGE);
            }
        }
    }
}
