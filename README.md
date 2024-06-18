# Funny JSON Explorer（进阶）

Funny JSON Explorer（**FJE**），是一个JSON文件可视化的命令行界面小工具

对已有FJE实现进行设计重构：改用迭代器+访问者模式

## 类图

进阶：

在上次的基础上，只写出了增加的类和函数：

![fje_advanced](C:\Users\22739\Desktop\github\learning-note\c++项目\images\fje_advanced.jpg)

初版：

![fje](C:\Users\22739\Desktop\third_fall\Software\fje\fje.jpg)	





## 进阶：

添加**迭代器模式**和**访问者模式**：修改了和添加了以下类：

### JSONElementVisitor 接口

**描述：** 该接口定义了访问`JSONElement`的方法。

- **访问者模式：** 定义了访问`JSONElement`的接口，使得不同的可视化器可以实现该接口来处理JSON元素。



### JSONElementIterator 类

**描述：** 该类实现了`Iterator<JSONElement>`接口，用于迭代和构建JSON元素列表，支持嵌套JSON对象的遍历。

- **迭代器模式：** 提供了遍历和访问JSON元素的接口，实现了`hasNext()`和`next()`方法，确保逐个访问JSON元素。



### JSONElement 类

**描述：** 该类表示JSON对象中的一个元素及其层级和位置，使用了组合模式。

- **组合模式：** 处理嵌套的JSON对象。
- **访问者模式：** 提供`accept`方法接受访问者。



### RectangleJSONVisualizer 类

**描述：** 该类实现了`JSONVisualizer`接口，用于矩形显示JSON对象。使用了访问者模式和迭代器模式来处理和显示嵌套的JSON对象。

- **访问者模式：** 实现了`JSONElementVisitor`接口，通过`visit`方法处理每个JSON元素。
- **迭代器模式：** 使用`JSONElementIterator`类遍历JSON元素。
- **组合模式：** 通过递归处理嵌套的JSON对象。



### TreeJSONVisualizer 类

**描述：** 实现了`JSONVisualizer`接口，用于树状显示JSON对象。

- **组合模式：** 处理嵌套的JSON对象，显示其层级结构。
- **访问者模式：** 实现了`JSONElementVisitor`接口，通过`visit`方法处理每个JSON元素。
- **迭代器模式：** 使用`JSONElementIterator`类遍历JSON元素。





## 运行结果

![image-20240618175617893](C:\Users\22739\Desktop\github\learning-note\c++项目\images\image-20240618175617893.png)



![image-20240618175628158](C:\Users\22739\Desktop\github\learning-note\c++项目\images\image-20240618175628158.png)



![image-20240618175638056](C:\Users\22739\Desktop\github\learning-note\c++项目\images\image-20240618175638056.png)



![image-20240618175645691](C:\Users\22739\Desktop\github\learning-note\c++项目\images\image-20240618175645691.png)

