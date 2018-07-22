package garbage;

public interface BeanFactory<T> {
    T constructor();
}
