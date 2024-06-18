import java.util.*;

/**
 * RectangleJSONVisualizer类实现了JSONVisualizer接口，用于矩形显示JSON对象。
 * 使用了组合模式来处理嵌套的JSON对象。
 */
public class RectangleJSONVisualizer implements JSONVisualizer, JSONElementVisitor {
    private IconFamily iconFamily;
    private int elementSize;
    private int count;
    private Map<String, Integer> resultMap = new LinkedHashMap<>();
    private int resultSize;

    public RectangleJSONVisualizer(IconFamily iconFamily) {
        this.iconFamily = iconFamily;
        this.elementSize = 0;
        this.count = 0;
    }

    @Override
    public void visualize(Map<String, Object> jsonObject) {
        // 使用迭代器模式构建和遍历JSON元素
        JSONElementIterator iterator = new JSONElementIterator(jsonObject);
        elementSize = iterator.getSize();
        while (iterator.hasNext()) {
            JSONElement element = iterator.next();
            element.accept(this); // 使用访问者模式
        }
        draw(resultSize);
    }

    /**
     * 访问JSON元素
     * 使用了访问者模式。
     */
    @Override
    public void visit(JSONElement element) {
        String result = makeResult(element);
        resultSize = Math.max(resultSize, result.length());
        resultMap.put(result, result.length());
    }

    private void draw(int maxResultSize) {
        int idx = 0;
        int len = maxResultSize + 4;
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            for (int i = 0; i < len - value; i++) {
                key += "─";
            }
            if (idx == 0) {
                key += "┐";
            } else if (idx == elementSize - 1) {
                key += "┘";
            } else {
                key += "┤";
            }
            idx++;
            System.out.println(key);
        }
    }

    private String makeResult(JSONElement element) {
        count += 1;
        String result = printIndent(element.getLevel(), element.getParentHasNext(), count);
        switch (element.getPosition()) {
            case FIRST:
                if (element.getLevel() > 0){
//                    System.out.print("├─");
                    result += "├─";
                } else {
//                    System.out.print("┌─");
                    result += "┌─";
                }

                break;
            case LAST:
            case MIDDLE:
                if (count != elementSize){
//                    System.out.print("├─");
                    result += "├─";
                }
                break;
        }

        if (element.getValue() instanceof Map) {
            result += iconFamily.getIntermediateNodeIcon() + element.getKey();
        } else {
            result += iconFamily.getLeafNodeIcon() + element.getKey() + (element.getValue() == null ? "" : ": " + element.getValue());
        }
        return result;
    }

    private String printIndent(int level, boolean[] parentHasNextParent, int idx) {
        String result = "";
        if (idx == elementSize) {
            result += "└──";
            for (int i = 0; i < level; i++) {
                result += "─┴─";
            }
            return result;

        }
        for (int i = 0; i < level; i++) {
            result += "│   ";
        }
        return result;
    }

}
