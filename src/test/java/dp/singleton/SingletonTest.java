package dp.singleton;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import dp.singleton.model.TestedSingletonClass1;
import dp.singleton.model.TestedSingletonClass2;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(JUnit4.class)
public class SingletonTest {


    @Test
    @DisplayName("Tested is a case with one class type - TestedSingletonClass1")
    public void oneCallOfMethodWithOneClassType() {
        // given
        Singleton<TestedSingletonClass1> of = Singleton.of(TestedSingletonClass1.class);
        // when
        TestedSingletonClass1 instance1a = of.instance();
        TestedSingletonClass1 instance1b = of.instance();
        //then
        Assert.assertThat(instance1a, is(instance1b));
    }

    @Test
    @DisplayName("Tested is a case with two different class types TestedSingletonClass1 " +
            "and TestedSingletonClass2, each class type work as singleton")
    public void manyCallOfMethodWithManyClassType() {
        // given
        Singleton<TestedSingletonClass1> of1 = Singleton.of(TestedSingletonClass1.class);
        Singleton<TestedSingletonClass2> of2 = Singleton.of(TestedSingletonClass2.class);
        // when
        TestedSingletonClass1 instance1a = of1.instance();
        TestedSingletonClass1 instance1b = of1.instance();
        // and
        TestedSingletonClass2 instance2a = of2.instance();
        TestedSingletonClass2 instance2b = of2.instance();
        //then
        Assert.assertThat(instance1a, is(instance1b));
        Assert.assertThat(instance2a, is(instance2b));
        Assert.assertThat(instance1a, is(not((instance2a))));
    }
}