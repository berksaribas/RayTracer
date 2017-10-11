import java.util.ArrayList;

public class Scene {
    private int width, heigth;
    private Camera camera;
    private ArrayList<Sphere> lights;
    private ArrayList<Object> objects;
    Vector3 backgroundColor;

    Scene(){
        objects = new ArrayList<Object>();
        lights = new ArrayList<Sphere>();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public ArrayList<Sphere> getLights() {
        return lights;
    }

    public void setLights(ArrayList<Sphere> lights) {
        this.lights = lights;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Object> objects) {
        this.objects = objects;
    }

    public Vector3 getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Vector3 backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
