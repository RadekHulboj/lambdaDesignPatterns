package dp.chainofresponsibilities.oop;
public class EligibilityRule extends AbstractRule {
    private static final Long VALUE_LIMIT = 0L;
    @Override
    protected Boolean isValid(Long value) {
        return value > VALUE_LIMIT;
    }
}
