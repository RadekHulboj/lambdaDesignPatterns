package dp.singleton

import dp.singleton.model.TestedSingletonClass1
import dp.singleton.model.TestedSingletonClass2
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import spock.lang.Specification

@RunWith(JUnit4.class)
class SingletonSpock extends Specification {
    
    @Test
    void "Tested is a case with one class type - TestedSingletonClass1"() {
        given:
        def of = Singleton.of(TestedSingletonClass1.class)

        when:
        def instance1a = of.instance()
        def instance1b = of.instance()

        then:
        instance1a == instance1b
    }

    @Test
    void "Tested is a case with two different class types TestedSingletonClass1 and TestedSingletonClass2, each class type work as singleton"() {
        given:
        def of1 = Singleton.of(TestedSingletonClass1.class)
        def of2 = Singleton.of(TestedSingletonClass2.class)

        when:
        TestedSingletonClass1 instance1a = of1.instance()
        TestedSingletonClass1 instance1b = of1.instance()
        and:
        TestedSingletonClass2 instance2a = of2.instance()
        TestedSingletonClass2 instance2b = of2.instance()

        then:
        instance1a == instance1b
        instance2a == instance2b
        instance1a != instance2a
    }
}
