package lotto.Domain;

import java.util.Arrays;

/**
 * 로또의 당첨 등수를 정의하는 Enum 클래스입니다.
 * 각 등수는 일치 개수, 보너스 여부, 상금 금액으로 구분됩니다.
 */
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

    /**
     * 해당 등수의 당첨 결과 메시지를 반환합니다.
     * @return "낙첨" 또는 "n개 일치 (상금)" 형태의 문자열
     */
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

    /**
     * 로또 번호 일치 및 보너스 번호 일치 여부를 바탕으로 등수를 반환합니다.
     * @param matchCount 일치한 번호 개수
     * @param bonusMatched 보너스 번호 일치 여부
     * @return 해당하는 등수 (없다면 MISS)
     */
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