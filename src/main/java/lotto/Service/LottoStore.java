package lotto.Service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Domain.Lotto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoStore {

    public static int calculateTicketCount(int money) {
        return money/1000;
    }

    public static List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            lottos.add(new Lotto(generateNumbers()));
        }
        return lottos;
    }

    private static List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        Collections.sort(numbers);
        return numbers;
    }
}
