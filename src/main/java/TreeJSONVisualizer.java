import java.util.*;

/**
 * TreeJSONVisualizer类实现了JSONVisualizer接口，用于树状显示JSON对象。
 * 使用了组合模式来处理嵌套的JSON对象。
 */

public class TreeJSONVisualizer implements JSONVisualizer, JSONElementVisitor {
    private IconFamily iconFamily;

    public TreeJSONVisualizer(IconFamily iconFamily) {
        this.iconFamily = iconFamily;
    }

    @Override
    public void visualize(Map<String, Object> jsonObject) {
        JSONElementIterator iterator = new JSONElementIterator(jsonObject);
        while (iterator.hasNext()) {
            JSONElement element = iterator.next();
            element.accept(this); // 使用访问者模式
        }
    }

    /**
     * 访问JSON元素
     * 使用了访问者模式。
     */
    @Override
    public void visit(JSONElement element) {
        draw(element);
    }

    private void draw(JSONElement element) {
        printIndent(element.getLevel(), element.getParentHasNext());
        switch (element.getPosition()) {
            case FIRST:
            case MIDDLE:
                System.out.print("├─ ");
                break;
            case LAST:
                System.out.print("└─ ");
                break;
        }
        if (element.getValue() instanceof Map) {
            System.out.println(iconFamily.getIntermediateNodeIcon() + element.getKey());
        } else {
            System.out.println(iconFamily.getLeafNodeIcon() + element.getKey() + (element.getValue() == null ? "" : ":" + element.getValue()));
        }
    }

    private void printIndent(int level, boolean[] parentHasNextParent) {
        for (int i = 0; i < level; i++) {
            if (parentHasNextParent[i]) {
                System.out.print("│  ");
            } else {
                System.out.print("   ");
            }
        }
    }
}
