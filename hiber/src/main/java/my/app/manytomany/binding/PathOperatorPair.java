package my.app.manytomany.binding;

import com.querydsl.core.types.Ops;

import java.util.Objects;

public class PathOperatorPair {

    private static final char OPERATOR_START = '[';
    private static final char OPERATOR_END = ']';
    private String path;
    private Ops operator;

    public PathOperatorPair(Ops operator, String path) {
        this.operator = operator;
        this.path = path;
    }

    public static PathOperatorPair from(Ops operator, String path) {
        return new PathOperatorPair(operator, path);
    }

    public static PathOperatorPair fromString(String s) {
        String operator = null;
        String path;
        if (s.lastIndexOf(OPERATOR_START) != -1 && s.lastIndexOf(OPERATOR_END) != -1) {
            operator = s.substring(s.lastIndexOf(OPERATOR_START) + 1, s.lastIndexOf(OPERATOR_END));
            path = s.substring(0, s.lastIndexOf(OPERATOR_START));
        } else {
            path = s;
        }
        Ops ops = operator != null ? Ops.valueOf(operator.toUpperCase()) : null;
        return new PathOperatorPair(ops, path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Ops getOperator() {
        return operator;
    }

    public void setOperator(Ops operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathOperatorPair that = (PathOperatorPair) o;
        return Objects.equals(path, that.path) &&
                operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, operator);
    }
}
