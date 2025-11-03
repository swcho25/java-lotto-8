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
        int money = readMoney();
        int count = LottoStore.calculateTicketCount(money);
        outputView.countComment(count);

        List<Lotto> lottos = LottoStore.generateLottos(count);
        outputView.printLottos(lottos);

        WinningNumbers winningNumbers = readWinningNumbers();
        LottoStatistics stats = new LottoStatistics(lottos, winningNumbers);
        outputView.printResult(stats);
    }

    private int readMoney() {
        String moneyInput = inputView.readInput();
        return Validator.validateMoney(moneyInput);
    }

    private WinningNumbers readWinningNumbers() {
        List<Integer> winning = inputView.readWinningNumbers();
        String bonusInput = inputView.readInput();
        int bonus = Validator.validateBonusNumber(bonusInput, winning);
        return new WinningNumbers(winning, bonus);
    }
}
