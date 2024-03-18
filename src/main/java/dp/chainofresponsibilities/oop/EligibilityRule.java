package dp.chainofresponsibilities.oop;
public class EligibilityRule extends AbstractRule<Long> {
    private static final Long VALUE_LIMIT = 0L;
    @Override
    public Boolean isValid(Long value) {
        return value > VALUE_LIMIT;
    }
}
