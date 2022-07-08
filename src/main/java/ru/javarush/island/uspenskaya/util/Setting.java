package ru.javarush.island.uspenskaya.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//@Inherited Позволяет классу-наследнику реализовать наследование аннотаций родительского класса
public @interface Setting {
    double mass(); //default 50;
    int speed(); //default 3;
    int maxQuality(); //default 30;
    double saturation(); //default 8;
    String icon(); //default "🐺";
}
