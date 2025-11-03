package lotto.Domain;

import java.util.List;

/** 로또 당첨 번호와 보너스 번호를 표현하는 도메인 클래스 */
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
