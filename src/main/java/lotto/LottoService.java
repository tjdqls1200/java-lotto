package lotto;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoService {
    private final RandomGenerator randomGenerator = new RandomGenerator();
    private final List<Lotto> lotteries = new ArrayList<>();
    private Lotto winningNumber;
    private Number bonusNumber;

    public void purchase(Money money) {
        int buyQuantity = money.getWon() / 1000;

        for (int i = 0; i < buyQuantity; i++) {
            lotteries.add(new Lotto(randomGenerator.createRandomNumbers()));
        }
    }

    public List<Lotto> getPurchaseLotteries() {
        return lotteries;
    }

    public void setWinningNumber(String inputWinningNumber) {
        winningNumber = new Lotto(convertToNumbers(inputWinningNumber));
    }

    public void setBonusNumber(String inputBonusNumber) {
        bonusNumber = convertToNumber(inputBonusNumber);
    }

    private List<Number> convertToNumbers(String input) {
        return splitByComma(input)
                .map(LottoService::convertToNumber)
                .collect(Collectors.toList());
    }

    private Stream<String> splitByComma(String input) {
        try {
            return Arrays.stream(input.split(","));
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException();
        }
    }

    private static Number convertToNumber(String inputBonusNumber) {
        try {
            return new Number(Integer.parseInt(inputBonusNumber));
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException();
        }
    }
}
