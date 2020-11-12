package sandbox.cookbook.singleton;

public class MainSingleton {

    private static Singleton<OneObject> oneObjectSingleton = Singleton.of(OneObject.class);

    public static void main (String[] args)  {
        oneObjectSingleton = Singleton.of(OneObject.class);
        oneObjectSingleton = Singleton.of(OneObject.class);

        OneObject oneObject1 = oneObjectSingleton.instance();
        OneObject oneObject2 = oneObjectSingleton.instance();

        System.out.println(oneObject1.equals(oneObject2));

    }
}
