package lotto;

import lotto.Controller.LottoController;
import lotto.View.InputView;
import lotto.View.OutputView;

/** 프로그램 실행의 진입점 */
public class Application {
    public static void main(String[] args) {
        try {
            InputView inputView = new InputView();
            OutputView outputView = new OutputView();
            LottoController lottoController = new LottoController(inputView, outputView);
            lottoController.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
