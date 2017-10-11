public class Plane extends Object {

    Vector3 normal;

    Plane(Vector3 position, Vector3 diffuseColor, int id) {
        this.position = position;
        this.diffuseColor = diffuseColor;
        this.id = id;
        this.specularColor = new Vector3(180, 180, 180);
        this.normal = new Vector3(0, 1, 0);
    }

    @Override
    double checkIntersection(Ray ray) {

        double denom = normal.dotProduct(ray.direction);
        if (Math.abs(denom) > 0)
        {
            double t = (position.subtractVector(ray.originPoint)).dotProduct(normal) / denom;
            if (t >= 0) return t;
        }
        return -1;

    }

    @Override
    Vector3 getNormal(Vector3 intersectionPoint) {
        return normal;
    }
}
