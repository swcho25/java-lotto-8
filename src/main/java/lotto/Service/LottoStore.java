package lotto.Service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Domain.Lotto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 로또 발행과 관련된 기능을 담당하는 서비스 클래스 */
public class LottoStore {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    /**
     * 구입한 금액을 기반으로 구매 가능한 로또 개수를 계산합니다.
     * @param money 사용자가 입력한 구매 금액
     * @return 구매 가능한 로또 개수
     */
    public static int calculateTicketCount(int money) {
        return money/LOTTO_PRICE;
    }

    /**
     * 지정된 개수만큼 로또를 생성하여 반환합니다.
     * @param count 생성할 로또 개수
     * @return 로또 객체 리스트
     */
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
