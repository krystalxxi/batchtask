package com.ecommerce.batchtask.service.taskprocess.util;

import com.ecommerce.batchtask.service.taskprocess.bo.FileHead;
import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.IntegerMemberValue;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public final class DynamicBeanUtil {
    /**
     * @param vo
     * @param properties
     * @throws NotFoundException
     * @throws IOException
     * @throws CannotCompileException
     */
    public static void generateObject(String vo, Map<String, FileHead> properties) throws IOException {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.makeClass(vo);
            ctClass.defrost();
            ctClass.setSuperclass(pool.get("com.ecommerce.batchtask.service.taskprocess.bo.ImportVo"));
            Set keySet = properties.keySet();
            for (Iterator i = keySet.iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                CtField field$key = new CtField(pool.get(properties.get(key).getField()), key, ctClass);
                field$key.setModifiers(Modifier.PRIVATE);
                ctClass.addField(field$key);
                //生成get/set方法
                String setName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                String getName = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);

                ctClass.addMethod(CtNewMethod.setter(setName, field$key));
                ctClass.addMethod(CtNewMethod.getter(getName, field$key));

                FieldInfo fieldInfo = field$key.getFieldInfo();
                ConstPool cp = fieldInfo.getConstPool();

                AnnotationsAttribute attribute = (AnnotationsAttribute) fieldInfo.getAttribute(AnnotationsAttribute.visibleTag);
                // todo 优化 此处只针对excel格式的处理了
                Annotation annotation = new Annotation("com.alibaba.excel.annotation.ExcelProperty", cp);
                annotation.addMemberValue("index", new IntegerMemberValue(cp, properties.get(key).getIndex()));
                if (attribute == null) {
                    attribute = new AnnotationsAttribute(fieldInfo.getConstPool(), AnnotationsAttribute.visibleTag);
                }
                attribute.addAnnotation(annotation);
            }
            String targetClassPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            ctClass.writeFile(targetClassPath);
        } catch (NotFoundException | CannotCompileException e) {
            throw new RuntimeException(e);
        }
    }
}
