package com.xdf.annotation_lib;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.xdf.annotation_lib.annotation.BindView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class InjectTool {


    public static void Inject(Object object){
            injectBindView(object);
    }

    private static void injectBindView(Object object) {
        // 1、通过类 获得 类里面的注解 （找到注解的class - 找到注解的作用范围 类、方法、属性..）
        //    找到 类、方法、属性..再获得其 指定的注解
        Class<?> mainActivityClass = object.getClass();
        // 1.1 获得属性方法
        Field[] fields = mainActivityClass.getDeclaredFields();

        if(fields.length ==0){
            Log.d("InjectTool ","injectBindView fields 属性未找到");
            return;
        }
        for (Field field : fields) {
            field.setAccessible(true);// 让JVM不要去管 private 修饰的
            // 只关心 BindVIew注解的 字段，其他的不管
            BindView bindView = field.getAnnotation(BindView.class);
            if(bindView ==null){
                continue;
            }
            // 2、获取注解的值
            int viewId = bindView.value();

            // 3、通过反射调用 findviewbyId 查找控件
            try {
                Method findViewIdMethod = mainActivityClass.getMethod("findViewById",int.class);
                Object viewResult =  findViewIdMethod.invoke(object,viewId);

                // 4、将查找的控件 赋值到属性上
                field.set(object,viewResult);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
