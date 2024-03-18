package dp.chainofresponsibilities.functional;

import dp.chainofresponsibilities.oop.AbstractRule;
import dp.chainofresponsibilities.oop.EligibilityRule;
import dp.chainofresponsibilities.oop.ReportableRule;

import java.util.List;

public class MainFunRuler {
    public static void main(String... args) {
        List<AbstractRule<Long>> matchedRules = ChainOfRes.<AbstractRule<Long>, Long>init()
                .addRule(EligibilityRule::new)
                .addRule(ReportableRule::new)
                .checkChain(5L);
    }
}
