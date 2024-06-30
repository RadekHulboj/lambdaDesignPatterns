package dp.chainofresponsibilities.functional;

import dp.chainofresponsibilities.oop.IRule;
import dp.chainofresponsibilities.oop.EligibilityRule;
import dp.chainofresponsibilities.oop.ReportableRule;

import java.util.List;

public class MainFunRuler {
    public static void main(String... args) {
        List<IRule<Long>> matchedRules = ChainOfRes.<IRule<Long>, Long>init()
                .addRule(EligibilityRule::new)
                .addRule(ReportableRule::new)
                .checkChain(5L);

        List<IRule<Long>> iRules = dp.chainofresponsibilities.functional.paper.ChainOfRes.<IRule<Long>, Long>init()
                .addRule(EligibilityRule::new)
                .addRule(ReportableRule::new)
                .checkWithValue(5L);
    }
}
