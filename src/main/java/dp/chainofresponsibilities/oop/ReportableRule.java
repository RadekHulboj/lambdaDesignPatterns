package dp.chainofresponsibilities.oop;
public class ReportableRule extends AbstractRule {
    private static final Long REPORTABLE_LIMIT = 3000L;
    @Override
    protected Boolean isValid(Long value) {
        return value > REPORTABLE_LIMIT;
    }
}
