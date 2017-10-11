import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TracerMain {
    public static void main(String [] args)
    {
        long currentTime = System.currentTimeMillis();
        System.out.println("Rendering has been started");
        RayTracer tracer = new RayTracer(args);
        long endTime = System.currentTimeMillis();
        System.out.println("The process took " + (endTime - currentTime) / 1000 + " seconds");
    }
}



