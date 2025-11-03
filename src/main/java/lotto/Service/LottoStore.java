package lotto.Service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Domain.Lotto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoStore {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public static int calculateTicketCount(int money) {
        return money/LOTTO_PRICE;
    }

    public static List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            lottos.add(new Lotto(generateNumbers()));
        }
        return lottos;
    }

    private static List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, LOTTO_NUMBER_COUNT));
        Collections.sort(numbers);
        return numbers;
    }
}
