package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoIssuer;
import lotto.model.LottoPurchase;
import lotto.validator.LottoPurchaseValidator;
import lotto.view.InputView;

public class LottoGameService {
    private final LottoIssuer lottoIssuer = new LottoIssuer();

    public LottoPurchase createLottoPurchase(int purchaseAmount) {
        return new LottoPurchase(purchaseAmount);
    }

    public LottoPurchase inputPurchaseAmount() {
        while (true) {
            try {
                String input = InputView.inputPurchaseAmount();
                int purchaseAmount = LottoPurchaseValidator.validateAndParse(input);
                return createLottoPurchase(purchaseAmount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Lotto> generateLottoTickets(LottoPurchase lottoPurchase) {
        int count = lottoPurchase.getAmount() / 1000;
        List<Lotto> tickets = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Lotto lotto = lottoIssuer.issue();
            tickets.add(lotto);
        }
        return tickets;
    }
}
