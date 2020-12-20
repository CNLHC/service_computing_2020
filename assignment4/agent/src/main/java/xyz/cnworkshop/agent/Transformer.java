package xyz.cnworkshop.agent;

import javassist.*;
import xyz.cnworkshop.Profilier;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {
    private final ClassPool classPool = ClassPool.getDefault();

    @Override
    public byte[] transform(final ClassLoader loader,
                            final String className,
                            final Class<?> classBeingRedefined,
                            final ProtectionDomain protectionDomain,
                            final byte[] classfileBuffer) {


        if (className == null) {
            return null;
        }

        final String classNameDots = className.replaceAll("/", ".");
        final CtClass ctClass = classPool.getOrNull(classNameDots);

        if (ctClass == null) {
            return null;
        }
        if (ctClass.isFrozen()) {
            ctClass.detach();
            return null;
        }

        try {
            for (final CtBehavior behavior : ctClass.getDeclaredBehaviors()) {
//                !behavior.getLongName().contains("java.lang")
//                        &&
//                        !behavior.getLongName().contains("sun.launcher.")
//                        &&
//                        !behavior.getLongName().contains("java.util.")
                if (
                        !behavior.getLongName().contains("sun.launcher.") &&
                                !behavior.getLongName().contains("java.lang.invoke") &&
                                !behavior.getLongName().contains("java.lang.Class") &&
                                !behavior.getLongName().contains("java.util")
                ) {

//                    if (behavior.getLongName().contains("main")) {

//                    System.out.printf("instrumenting %s\n", behavior.getLongName());
                    instrument(behavior);
//                    }
                }
            }
            return ctClass.toBytecode();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            ctClass.detach();
        }

        return null;
    }

    private void instrument(final CtBehavior behavior)
            throws CannotCompileException, NotFoundException {

        behavior.addLocalVariable("$_elapsed_begin", CtClass.longType);
        behavior.insertBefore("$_elapsed_begin= System.nanoTime();");
//        behavior.insertBefore("System.out.println(123);");

        final String reportCode =
                Profilier.class.getName() +
                        ".getInstance().report(" +
                        "\"" + behavior.getLongName() + "\", " +
                        "$_elapsed_begin,System.nanoTime());";
        final String t = String.format("try {%s\n}catch(Exception e){}finally{}", reportCode);
        behavior.insertAfter(reportCode);
    }

}