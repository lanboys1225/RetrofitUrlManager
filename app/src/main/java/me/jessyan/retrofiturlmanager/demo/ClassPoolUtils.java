package me.jessyan.retrofiturlmanager.demo;

import android.content.Context;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import me.jessyan.retrofiturlmanager.annotation.AllHeaders;
import retrofit2.http.Headers;

/**
 * Created by 蓝兵 on 2018/4/17.
 */

public class ClassPoolUtils {

    public static Class service(Context context, String apiServiceName) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass ctClass = pool.get(apiServiceName);

            Object[] annotations = ctClass.getAnnotations();
            for (Object annotation : annotations) {
                // 只对 AllHeaders 做处理
                if (annotation instanceof AllHeaders) {
                    String[] value = ((AllHeaders) annotation).value();
                    CtMethod[] declaredMethods = ctClass.getDeclaredMethods();

                    for (CtMethod declaredMethod : declaredMethods) {
                        System.out.println("declaredMethod.getName: " + declaredMethod.getName());
                        MethodInfo methodInfo = declaredMethod.getMethodInfo();
                        ConstPool cp = methodInfo.getConstPool();
                        //原来有的话 需要获取
                        AnnotationsAttribute visibleTagAttribute = (AnnotationsAttribute) methodInfo.getAttribute(AnnotationsAttribute.visibleTag);
                        if (visibleTagAttribute == null) {
                            visibleTagAttribute = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
                        }
                        Annotation[] visibleTagAnnotations = visibleTagAttribute.getAnnotations();

                        boolean needAddStudentAction = true;
                        for (Annotation ann : visibleTagAnnotations) {
                            System.out.println("visibleTagAnnotation: " + ann);
                            if (Headers.class.getName().equals(ann.getTypeName())) {
                                needAddStudentAction = false;
                            }
                        }
                        if (needAddStudentAction) {
                            String studentAction = Headers.class.getName();
                            Annotation annotation1 = new Annotation(studentAction, cp);

                            StringMemberValue[] elements = new StringMemberValue[value.length];

                            for (int i = 0; i < value.length; i++) {

                                elements[i] = new StringMemberValue(value[i], cp);
                            }
                            ArrayMemberValue amv = new ArrayMemberValue(cp);
                            amv.setValue(elements);

                            annotation1.addMemberValue("value", amv);

                            visibleTagAttribute.addAnnotation(annotation1);
                        }

                        visibleTagAnnotations = visibleTagAttribute.getAnnotations();
                        for (Annotation ann : visibleTagAnnotations) {
                            System.out.println("visibleTagAnnotation: " + ann);
                        }
                    }
                }
            }
            //ctClass.writeFile();
            return ctClass.toClass();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}