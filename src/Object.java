public abstract class Object {
    Vector3 position, diffuseColor, specularColor;
    int id;

    abstract double checkIntersection(Ray ray);
    abstract Vector3 getNormal(Vector3 intersectionPoint);
}
