public class Sphere extends Object{
    double radius;

    Sphere(Vector3 position, double radius, Vector3 diffuseColor, int id) {
        this.position = position;
        this.radius = radius;
        this.diffuseColor = diffuseColor;
        this.id = id;
        this.specularColor = new Vector3(180, 180, 180);
    }

    @Override
    double checkIntersection(Ray ray) {
        double t;

        Vector3 length = position.subtractVector(ray.originPoint);
        double tca = length.dotProduct(ray.direction);
        if(tca < 0){
            return -1;
        }
        double d2 = length.dotProduct(length) - tca * tca;
        if(d2 > radius * radius){
            return -1;
        }

        double thc = Math.sqrt(radius * radius - d2);

        double t0, t1;

        t0 = tca - thc;
        t1 = tca + thc;

        t = (t0 < t1) ? t0 : t1;

        return t;
    }

    @Override
    Vector3 getNormal(Vector3 intersectionPoint) {
        return intersectionPoint.subtractVector(position);
    }

}
