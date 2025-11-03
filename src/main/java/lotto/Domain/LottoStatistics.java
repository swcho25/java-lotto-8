package lotto.Domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/** 전체 로또 구매 결과를 기반으로 통계를 계산하는 클래스 */
public class LottoStatistics {
    private static final int LOTTO_COST = 1000;
    private static final int PERCENTAGE = 100;
    private static final int ROUND_NUMBER = 10;

    private final Map<Prize, Integer> result = new EnumMap<>(Prize.class);
    private final double rate;

    /**
     * 로또 목록과 당첨 번호를 받아 통계 정보를 계산합니다.
     * @param lottos 구매한 로또 목록
     * @param winningNumbers 당첨 번호 및 보너스 번호 정보
     */
    public LottoStatistics(List<Lotto> lottos, WinningNumbers winningNumbers) {
        initializeResult();
        int totalPrize = 0;
        for(Lotto lotto: lottos) {
            int matchCount = countMatch(lotto.getNumbers(), winningNumbers.getNumbers());
            boolean bonusMatched = lotto.getNumbers().contains(winningNumbers.getBonus());
            Prize prize = Prize.valueOf(matchCount, bonusMatched);

            result.put(prize, result.get(prize)+1);
            totalPrize += prize.getReward();
        }
        double totalCost = lottos.size() * LOTTO_COST;
        double rawRate = (totalPrize / totalCost) * PERCENTAGE; // 퍼센트 계산
        this.rate = Math.round(rawRate * ROUND_NUMBER) / (double)ROUND_NUMBER;     // 소수점 첫째 자리 반올림
    }

    private void initializeResult() {
        for(Prize prize: Prize.values()) {
            result.put(prize, 0);
        }
    }

    private int countMatch(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        int count = 0;
        for (int n : lottoNumbers) {
            if (winningNumbers.contains(n)) count++;
        }
        return count;
    }

    /**
     * 특정 등수의 당첨 개수를 반환합니다.
     * @param prize 조회할 등수
     * @return 해당 등수의 당첨 개수
     */
    public int getCount(Prize prize) {
        return result.getOrDefault(prize, 0);
    }

    public double getRate() {
        return rate;
    }
}
