package lotto.Domain;

import java.util.Arrays;

public enum Prize {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    MISS(0, false, 0);

    private static final String MISS_MESSAGE = "낙첨";
    private static final String BONUS_MESSAGE = ", 보너스 볼 일치 ";
    private static final String MATCH_FORMAT = "%d개 일치%s(%,d원)";
    private static final int SECOND_PRIZE_NUMBER = 5;

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
            return MISS_MESSAGE;
        }
        String bonusText = " ";
        if (bonus) {
            bonusText = BONUS_MESSAGE;
        }
        return String.format(MATCH_FORMAT, matchCount, bonusText, reward);
    }

    public static Prize valueOf(int matchCount, boolean bonusMatched) {
        if (matchCount == SECOND_PRIZE_NUMBER && bonusMatched) return SECOND;
        return Arrays.stream(values())
                .filter(p -> p.matchCount == matchCount && !p.bonus)
                .findFirst()
                .orElse(MISS);
    }

    public int getReward() {
        return reward;
    }
}