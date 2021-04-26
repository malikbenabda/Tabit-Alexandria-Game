package de.tabit.test.alexandria.engine.toolz;

import java.util.Random;

public class Utilities {


    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = new Random().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
