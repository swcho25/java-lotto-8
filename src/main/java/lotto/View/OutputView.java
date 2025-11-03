package lotto.View;

import lotto.Domain.Lotto;
import lotto.Domain.LottoStatistics;
import lotto.Domain.Prize;
import java.util.List;

/** 출력을 담당하는 View 클래스 */
public class OutputView {
    private static final String MESSAGE_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_PURCHASE_COUNT = "개를 구매했습니다.";
    private static final String MESSAGE_RESULT_HEADER = "당첨 통계";
    private static final String MESSAGE_RESULT_DIVIDER = "---";
    private static final String MESSAGE_PROFIT_RATE = "총 수익률은 %.1f%%입니다.";

    /** 프로그램 시작 시 구입 금액 입력 요청 메시지 출력 */
    public void startComment() {
        System.out.println(MESSAGE_INPUT_MONEY);
    }

    /**
     * 구매한 로또 개수를 출력합니다.
     * @param count 구매한 로또 개수
     */
    public void countComment(int count) {
        System.out.println(count+MESSAGE_PURCHASE_COUNT);
    }

    /**
     * 구매한 모든 로또 번호를 출력합니다.
     * @param lottos 로또 리스트
     */
    public void printLottos(List<Lotto> lottos) {
        for(Lotto lotto: lottos) {
            System.out.println(lotto.getNumbers().toString());
        }
    }

    /**
     * 당첨 통계 및 수익률을 출력합니다.
     * @param stats 로또 통계 정보
     */
    public void printResult(LottoStatistics stats) {
        System.out.println(MESSAGE_RESULT_HEADER);
        System.out.println(MESSAGE_RESULT_DIVIDER);

        for (Prize prize : Prize.values()) {
            if (prize == Prize.MISS) continue;
            System.out.println(prize.formatMessage() + " - " + stats.getCount(prize) + "개");
        }

        System.out.printf((MESSAGE_PROFIT_RATE) + "%n", stats.getRate());
    }
}
