package domain.util;

/**
 * Created by oliv on 4/14/16.
 */
public class Triplet <L,C,R> {
    private final L left;
    private final C center;
    private final R right;

    public Triplet(L left, C center, R right) {
        this.left = left;
        this.center = center;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public C getCenter() {
        return center;
    }

    public R getRight() {
        return right;
    }

}
