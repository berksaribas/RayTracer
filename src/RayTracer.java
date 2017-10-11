import java.util.ArrayList;

public class RayTracer {

    RayTracer(String [] args){

        if(args.length == 0){
            System.out.println("Please put a filename as an argument");
            return;
        }

        FileReader fileReader = new FileReader();

        Scene scene = fileReader.loadFile(args[0]);

        Vector3 imageData[] = render(scene);

        ImageSaver.getInstance().saveImage(imageData, scene.getWidth(), scene.getHeigth(), args[0] + ".png");

    }

    Vector3[] render(Scene scene){

        Camera camera = scene.getCamera();
        ArrayList<Sphere> lights = scene.getLights();
        ArrayList<Object> objects = scene.getObjects();
        Vector3 backgroundColor = scene.getBackgroundColor();

        double t;
        int pixelCount = (int)(camera.width * camera.height);

        Vector3 imageData[] = new Vector3[pixelCount];

        double sampleMatrix[] = {-1.0/4.0,  3.0/4.0, 3.0/4.0,  1.0/3.0, -3.0/4.0, -1.0/4.0, 1.0/4.0, -3.0/4.0};

        for (int x = 0; x < camera.width; x++) {
            for (int y = 0; y < camera.height; y++){

                Vector3 color;

                Vector3 pixCol = new Vector3(0, 0, 0);
                int addedColorCount = 0;

                for(Sphere light : lights) {

                    for (int sample = 0; sample < 4; sample++) {

                        double xx = (2 * ((x + sampleMatrix[sample]) / camera.width) - 1) * camera.angle * camera.aspectRatio;
                        double yy = (1 - 2 * ((y + sampleMatrix[sample + 1]) / camera.height)) * camera.angle;

                        Ray ray = new Ray(camera.position, new Vector3(xx, yy, -1).normalize());

                        double nearestPoint = 1e8;
                        Object targetObject = null;

                        for (int i = 0; i < objects.size(); i++) {
                            double intersectionValue = objects.get(i).checkIntersection(ray);
                            if (intersectionValue != -1) {
                                t = intersectionValue;
                                if (t < nearestPoint) {
                                    nearestPoint = t;
                                    targetObject = objects.get(i);
                                }
                            }
                        }

                        if (targetObject != null) {
                            Vector3 intersectionPoint = ray.originPoint.addVector(ray.direction.multiplyVector(nearestPoint));
                            Vector3 lightRay = light.position.subtractVector(intersectionPoint);

                            boolean shadowed = false;
                            Ray rayLight = new Ray(intersectionPoint, lightRay.normalize());

                            for (int i = 0; i < objects.size(); i++) {
                                double intersectionValue = objects.get(i).checkIntersection(rayLight);
                                if (intersectionValue != -1) {
                                    if (objects.get(i).id != targetObject.id) {
                                        shadowed = true;
                                    }
                                }
                            }

                            if (!shadowed) {
                                lightRay = lightRay.normalize();
                                Vector3 normal = targetObject.getNormal(intersectionPoint);
                                normal = normal.normalize();
                                Vector3 diffuse = targetObject.diffuseColor.divideVector(255).vectoralMultiply(light.diffuseColor.divideVector(255)).multiplyVector(Math.max(0.0, lightRay.dotProduct(normal)));

                                Vector3 reflectedRay = (normal.multiplyVector(2).subtractVector(lightRay));
                                reflectedRay = reflectedRay.normalize();
                                Vector3 specular = targetObject.specularColor.divideVector(255).vectoralMultiply(light.diffuseColor.divideVector(255)).multiplyVector(Math.pow(Math.max(0.0, reflectedRay.dotProduct(
                                        (ray.originPoint.subtractVector(intersectionPoint)).normalize())), 128));

                                Vector3 finalColor = diffuse.addVector(specular);
                                pixCol = pixCol.addVector(finalColor.multiplyVector(255));
                            }

                            addedColorCount ++;
                        }
                    }
                }
                if(addedColorCount != 4 * lights.size()){
                    pixCol = pixCol.addVector(backgroundColor);
                }
                color = pixCol.divideVector(addedColorCount);

                int currentPixel = (int)(y*camera.width) + x;

                imageData[currentPixel] = color;
            }
        }

        return imageData;
    }

}
