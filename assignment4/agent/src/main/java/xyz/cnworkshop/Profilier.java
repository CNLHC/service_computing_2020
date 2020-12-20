package xyz.cnworkshop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Profilier implements AutoCloseable {
    private static Profilier singleton = new Profilier();

    private Profilier() {
    }

    public static Profilier getInstance() {
        return singleton;
    }


    private final ConcurrentHashMap<Integer, FileWriter> flamestack_book = new ConcurrentHashMap<>();

    @Override
    public void close() {
        System.out.println(("finalize"));
        for (FileWriter fp : flamestack_book.values()) {
            try {
                fp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void write_to_file(Integer tid, String line) throws IOException {

        FileWriter writer = flamestack_book.get(tid);
        if (writer == null) {
            writer = new FileWriter("./" + tid.toString() + ".flametrace");
            flamestack_book.put(tid, writer);
        }
        writer.write(line);
        writer.write("\n");
        System.out.println(line);
        writer.flush();
    }

    public void report(final String methodName,
                       final long begin,
                       final long end
    ) {
        System.out.printf("%s-Report: %d-%d\n", methodName, begin, end);
        Integer tid = Math.toIntExact(Thread.currentThread().getId());
        System.out.printf("TID: %s\n", tid.toString());

        List<StackTraceElement> stack = Arrays.asList(Thread.currentThread().getStackTrace());
        Collections.reverse(stack);
        String output = "";

        for (final StackTraceElement e : stack) {
            output += e.toString();
            output += ";";
        }
        output += String.format(" %d", end - begin);
        try {
            write_to_file(tid, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
