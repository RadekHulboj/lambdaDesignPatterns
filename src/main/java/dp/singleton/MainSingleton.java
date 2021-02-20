package dp.singleton;

import dp.singleton.model.OneObject;
import dp.singleton.model.SecondObject;

public class MainSingleton {

    private static Singleton<OneObject> oneObjectSingleton = Singleton.of(OneObject::new);
    private static Singleton<SecondObject> secObjectSingleton = Singleton.of(SecondObject::new);

    public static void main (String[] args)  {
        oneObjectSingleton = Singleton.of(OneObject::new);
        oneObjectSingleton = Singleton.of(OneObject::new);

        OneObject oneObject1 = oneObjectSingleton.instance();
        OneObject oneObject2 = oneObjectSingleton.instance();

        secObjectSingleton = Singleton.of(SecondObject::new);
        secObjectSingleton = Singleton.of(SecondObject::new);

        SecondObject secObject1 = secObjectSingleton.instance();
        SecondObject secObject2 = secObjectSingleton.instance();

        System.out.println(oneObject1.equals(oneObject2));
        System.out.println(secObject1.equals(secObject2));
        System.out.println(oneObject1.equals(secObject1));

    }
}
