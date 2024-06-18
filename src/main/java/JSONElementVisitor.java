/**
 * JSONElementVisitor接口定义了访问JSON元素的方法。
 * 使用了访问者模式。
 */
public interface JSONElementVisitor {
    void visit(JSONElement element);
}
