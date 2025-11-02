package lotto.Domain;

import java.util.Arrays;

public enum Prize {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    MISS(0, false, 0);

    private final int matchCount;
    private final boolean bonus;
    private final int reward;

    Prize(int matchCount, boolean bonus, int reward) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.reward = reward;
    }

    public String formatMessage() {
        if (this == MISS) {
            return "낙첨";
        }
        String bonusText = " ";
        if (bonus) {
            bonusText = ", 보너스 볼 일치 ";
        }
        return matchCount + "개 일치" + bonusText
                + "(" + String.format("%,d", reward) + "원)";
    }

    public static Prize valueOf(int matchCount, boolean bonusMatched) {
        if (matchCount == 5 && bonusMatched) return SECOND;
        return Arrays.stream(values())
                .filter(p -> p.matchCount == matchCount && !p.bonus)
                .findFirst()
                .orElse(MISS);
    }

    public int getReward() {
        return reward;
    }
}