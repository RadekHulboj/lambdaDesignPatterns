package dp.chainofresponsibilities.oop;
public class ReportableRule extends AbstractRule<Long> {
    private static final Long REPORTABLE_LIMIT = 3000L;
    @Override
    public Boolean isValid(Long value) {
        return value > REPORTABLE_LIMIT;
    }
}
