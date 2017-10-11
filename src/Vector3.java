public class Vector3 {
    private double x,y,z;

    Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Vector3 addVector(Vector3 vector){
        return new Vector3(x + vector.x, y + vector.y, z + vector.z);
    }

    Vector3 subtractVector(Vector3 vector){
        return new Vector3(x - vector.x, y - vector.y, z - vector.z);
    }

    Vector3 multiplyVector(double constant){
        return new Vector3(x * constant, y * constant, z * constant);
    }

    Vector3 vectoralMultiply(Vector3 vector) {
        return new Vector3(this.x * vector.x, this.y * vector.y, this.z * vector.z);
    }

    Vector3 divideVector(double constant){
        return new Vector3(x / constant, y / constant, z / constant);
    }

    Vector3 normalize(){
        double magnitude = Math.sqrt(x*x + y*y + z*z);
        return new Vector3(x / magnitude, y / magnitude, z / magnitude);
    }

    double dotProduct(Vector3 vector){
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
