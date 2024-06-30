package dp.chainofresponsibilities.oop;

import java.util.Arrays;
import java.util.List;

public class MainRuler {
    public static void main(String... args) {
        ReportableRule reportableRule = new ReportableRule();
        EligibilityRule eligibilityRule = new EligibilityRule();
        List<IRule> check = IRule.check(Arrays.asList(reportableRule, eligibilityRule), 44445L);
    }
}
