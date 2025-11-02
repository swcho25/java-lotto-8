package lotto.View;

import lotto.Domain.Lotto;
import lotto.Domain.LottoStatistics;
import java.util.List;

public class OutputView {

    public void startComment() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void countComment(int count) {
        System.out.println(count+"개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for(Lotto lotto: lottos) {
            System.out.println(lotto.getNumbers().toString());
        }
    }

    public void printResult(LottoStatistics stats) {
        System.out.println("당첨 통계");
        System.out.println("---");
    }
}
