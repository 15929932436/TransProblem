# Trans问题
本文档主要说明代码结构及如何开始本工程

## 代码结构
```
    |--src
    |  |--com
    |  |  |--darian
    |  |  |  |--Main.java
    |  |  |  |--data
    |  |  |  |  |--DataSource.java
    |  |  |  |--entry
    |  |  |  |  |--ServiceException.java
    |  |  |  |  |--Town.java
    |  |  |  |--impl
    |  |  |  |  |--FrameWork.java
    |  |--test
    |  |  |--TranTest.java
```
- Main 程序运行入口
- DataSource 数据源
- ServiceException 自定义异常类
- Town 城镇类
- FrameWork 核心实现类
- TranTest 测试类
## 如何使用
使用java IDE(eclipse或IDEA)打开该工程，并添加JDK1.8到工程中，运行Main.java

## 运行结果
```
9
5
13
22
NO SUCH ROUTE
2
3
9
9
7

```