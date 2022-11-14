package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {
    @DisplayName("천원으로 나누어 떨어지면 정상적으로 생성된다.")
    @Test
    void validMoney() {
        Money money = new Money("1000");

        assertThat(money.getWon()).isEqualTo(1000);
    }

    @DisplayName("돈 단위가 유효하지 않은 경우 예외가 발생한다.")
    @ValueSource(strings = {"", "a", "0", "500", "1500"})
    @ParameterizedTest(name = "입력 받은 돈 : {0}")
    void invalidMoney(String money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class);
    }
}