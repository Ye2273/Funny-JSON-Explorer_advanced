import java.util.*;

/**
 * JSONElementIterator类实现了Iterator接口，用于迭代JSON元素。
 * 使用了迭代器模式。
 */
public class JSONElementIterator implements Iterator<JSONElement> {
    private List<JSONElement> elements;
    private int index = 0;

    public JSONElementIterator(Map<String, Object> jsonObject) {
        elements = new ArrayList<>();
        BuildElementList.buildElementList(jsonObject, 0, elements, new boolean[0]);
    }

    @Override
    public boolean hasNext() {
        return index < elements.size();
    }

    public int getSize() {
        return elements.size();
    }

    @Override
    public JSONElement next() {
        return elements.get(index++);
    }
}
