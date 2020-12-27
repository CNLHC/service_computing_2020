package xyz.cnworkshop.agent;

import java.lang.instrument.Instrumentation;

public class ProfilingAgent {

    public static void premain(final String agentArgs,
                               final Instrumentation inst) {
        System.out.println(
                "Hey, look: I'm instrumenting a freshly started JVM!");
        inst.addTransformer(new Transformer());
    }

    public static void agentmain(final String agentArgs,
                                 final Instrumentation inst) {
        System.out.println("Hey, look: I'm instrumenting a running JVM!");
    }

}
