package cookbook.cache;


import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Die Teste der Klasse {@link CacheUtilsTest}
 */

@RunWith(JUnit4.class)
public class CacheUtilsTest {

    @Test
    @DisplayName("Method is not call, because function laziness is working")
    public void theLazinessIsWorkingForMemoizeMethod() {
        //given
        String cachedValue = "cached";
        int functionIsNotCalled = 0;
        //when
        List<Integer> counterList = new LinkedList<>();
        CacheUtils.memoize(() -> computationMethod(cachedValue, counterList));
        //then
        assertThat(counterList.size(), Is.is(functionIsNotCalled));
    }

    @Test
    @DisplayName("The function computationMethod is called one's, the rest is taken from the cache.")
    public void theMethodIsCalledOneTimeBecauseOfTheCaching() {
        //given
        String cachedValue = "cached";
        int functionIsCalledOnlyOneTime = 1;
        //when
        List<Integer> counterList = new LinkedList<>();
        Supplier<String> memoize = CacheUtils.memoize(() -> computationMethod(cachedValue, counterList));
        IntStream.range(0, 99).forEach(a -> memoize.get());
        //then
        assertThat(memoize.get(), IsEqual.equalTo(cachedValue));
        assertThat(counterList.size(), IsEqual.equalTo(functionIsCalledOnlyOneTime));
    }

    @Test
    @DisplayName("The memoize function works's in exclude way, just check")
    public void theMemoizeWorkSeparately() {
        //given
        String cachedValue1 = "cached1";
        String cachedValue2 = "cached2";
        //when
        Supplier<String> memoizeFor1 = CacheUtils.memoize(() -> cachedValue1);
        Supplier<String> memoizeFor2 = CacheUtils.memoize(() -> cachedValue2);
        //then
        assertThat(memoizeFor1.get(), IsEqual.equalTo(cachedValue1));
        assertThat(memoizeFor2.get(), IsEqual.equalTo(cachedValue2));
    }

    private static String computationMethod(String cachedValue, List<Integer> list) {
        list.add(1);
        return cachedValue;
    }
}