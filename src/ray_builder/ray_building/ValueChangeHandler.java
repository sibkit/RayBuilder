package ray_builder.ray_building;

public interface ValueChangeHandler<T> {
    void handle(T obj, String field);
}
