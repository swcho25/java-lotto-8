package lotto.Domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final Map<Prize, Integer> result = new EnumMap<>(Prize.class);
    private final double rate;

    public LottoStatistics(List<Lotto> lottos, WinningNumbers winningNumbers) {
        for(Prize prize: Prize.values()) {
            result.put(prize, 0);
        }
        int totalPrize = 0;
        for(Lotto lotto: lottos) {
            int matchCount = countMatch(lotto.getNumbers(), winningNumbers.getNumbers());
            boolean bonusMatched = lotto.getNumbers().contains(winningNumbers.getBonus());
            Prize prize = Prize.valueOf(matchCount, bonusMatched);

            result.put(prize, result.get(prize)+1);
            totalPrize += prize.getReward();
        }
        double totalCost = lottos.size() * 1000.0;
        double rawRate = (totalPrize / totalCost) * 100; // 퍼센트 계산
        this.rate = Math.round(rawRate * 10) / 10.0;     // 소수점 첫째 자리 반올림
    }

    private int countMatch(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        int count = 0;
        for (int n : lottoNumbers) {
            if (winningNumbers.contains(n)) count++;
        }
        return count;
    }

    public int getCount(Prize prize) {
        return result.getOrDefault(prize, 0);
    }

    public double getRate() {
        return rate;
    }
}
