package lotto.Service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Validator {

    public static int validateMoney(String input) {
        try {
            int money = Integer.parseInt(input.trim());
            if (money <= 0) {
                throw new IllegalArgumentException("[ERROR] 구입 금액은 양의 정수여야 합니다.");
            }
            if (money % 1000 != 0) {
                throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
            }
            return money;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    public static int validateBonusNumber(String input, List<Integer> winningNumbers) {
        try {
            int bonus = Integer.parseInt(input.trim());
            if (bonus < 1 || bonus > 45) {
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
            }
            if (winningNumbers.contains(bonus)) {
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
            }
            return bonus;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }

    public static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 번호가 존재합니다.");
        }
    }

    public static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
            }
        }
    }
}
