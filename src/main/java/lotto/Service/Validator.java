package lotto.Service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

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

    public static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    public static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException(ERROR_RANGE);
            }
        }
    }
}
