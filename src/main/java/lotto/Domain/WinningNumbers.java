package lotto.Domain;

import java.util.List;

public class WinningNumbers {
    private final List<Integer> numbers;
    private final int bonus;

    public WinningNumbers(List<Integer> numbers, int bonus) {
        this.numbers = numbers;
        this.bonus = bonus;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonus() {
        return bonus;
    }
}
