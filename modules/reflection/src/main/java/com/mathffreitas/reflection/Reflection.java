package com.mathffreitas.reflection;

import java.lang.IO;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection {

    public static void explore(Object target) throws Exception {
        IO.println("Getting attributes");

        for (Field field : target.getClass().getDeclaredFields()) {
            IO.println("\t" + field.getName() + ":" + field.getType().getName());
        }

        IO.println("Getting methods");
        for (Method method : target.getClass().getDeclaredMethods()) {
            IO.println("\t" + method.getName() + ":" + method.getReturnType().getName());
        }

        IO.println("Getting data inside object");
        for(Field field : target.getClass().getDeclaredFields()){
            //check if we can access the field before trying to get it
            if (!field.canAccess(target)) {
                //turn in accessible to get private fields
                field.setAccessible(true);
                IO.println("\t" + field.get(target));
                //turn back to not accessible
                field.setAccessible(false);
            }
        }

        IO.println("Getting fields with annotation");
        for(Field field : target.getClass().getDeclaredFields()){
            if (field.isAnnotationPresent(Visible.class)){
                field.setAccessible(true);
                IO.println("\t(Visible)" + field.getName() + ":" + field.get(target));
                field.setAccessible(false);
            }
            else {
                IO.println("\t(Not Visible)" + field.getName());
            }
        }

        IO.println("Getting data from getters");
        for(Method method : target.getClass().getDeclaredMethods()){
            if(method.getName().startsWith("get")){
                IO.println("\t" + method.getName() + ": value -> " + method.invoke(target, null));
            }
        }

        IO.println("Getting data from private methods");
        for(Method method : target.getClass().getDeclaredMethods()){
            //check if we can access the method before trying to invoke it
            if (!method.canAccess(target)) {
                //turn in accessible to get private methods
                method.setAccessible(true);
                IO.println("\t" + method.getName() + ": value -> " + method.invoke(target, null));
                //turn back to not accessible
                method.setAccessible(false);
            }
        }

        IO.println("Setting data to set method");
        for (Method method : target.getClass().getDeclaredMethods()){
            if (method.getName().equals("calculateDiscountedPrice")){
                Object result = method.invoke(target, 10.0, 100.0);
                IO.println("\t" + method.getName() + ": result -> " + result);
            }
        }
    }
}
