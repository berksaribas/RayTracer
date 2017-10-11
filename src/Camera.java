public class Camera {
    double width, height, fov, aspectRatio, angle;
    Vector3 position;

    Camera(double width, double height, double fov){
        this.fov = fov;
        this.width = width;
        this.height = height;
        aspectRatio = width / height;
        angle = Math.tan(Math.PI * 0.5 * fov / 180);
    }

    void setPosition(Vector3 position) {
        this.position = position;
    }
}
