package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_아니라면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 로또_번호가_범위를_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 양의 정수가 아니면 예외가 발생한다.")
    @Test
    void 구입금액이_음수이거나_0이면_예외가_발생한다() {
        assertThatThrownBy(() -> Validator.validateMoney(-1000))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateMoney(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000으로 나누어 떨어지지 않으면 예외가 발생한다.")
    @Test
    void 구입금액이_1000원으로_나누어떨어지지_않으면_예외가_발생한다() {
        assertThatThrownBy(() -> Validator.validateMoney(1500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 보너스번호가_범위를_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> Validator.validateBonusNumber(0, List.of(1, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Validator.validateBonusNumber(46, List.of(1, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스번호가_당첨번호와_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> Validator.validateBonusNumber(5, List.of(1,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액으로 구매 가능한 로또 개수를 계산한다.")
    @Test
    void 구입금액으로_로또개수를_계산한다() {
        int count = LottoStore.calculateTicketCount(8000);
        assertThat(count).isEqualTo(8);
    }

    @DisplayName("총 상금과 구입 금액을 기반으로 수익률을 계산한다.")
    @Test
    void 수익률을_정확히_계산한다() {
        double profitRate = Lotto.calculateRate(5000, 8000);
        assertThat(profitRate).isEqualTo(62.5);
    }
}
