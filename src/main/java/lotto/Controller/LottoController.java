package lotto.Controller;

import lotto.Domain.Lotto;
import lotto.Domain.WinningNumbers;
import lotto.Domain.LottoStatistics;
import lotto.Service.Validator;
import lotto.View.InputView;
import lotto.View.OutputView;
import lotto.Service.LottoStore;
import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.startComment();
        int money = Validator.validateMoney(inputView.readInput());
        int count = LottoStore.calculateTicketCount(money);
        outputView.countComment(count);

        List<Lotto> lottos = LottoStore.generateLottos(count);
        outputView.printLottos(lottos);

        List<Integer> winning = inputView.readWinningNumbers();
        String bonusInput = inputView.readInput();
        int bonus = Validator.validateBonusNumber(bonusInput, winning);

        WinningNumbers winningNumbers = new WinningNumbers(winning, bonus);
        LottoStatistics stats = new LottoStatistics(lottos, winningNumbers);
        outputView.printResult(stats);
    }
}
