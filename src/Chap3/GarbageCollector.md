# 垃圾收集器及内存分配策略

## 引用计数算法

实现简单，但是很难解决循环引用的问题。

## 可达性分析算法

通过一系列称为"GC Roots"的对象作为起始点开始向下搜索，搜索的路径称为引用链，当一个对象与GC roots没有任何引用链相连时，此对象不可用。

JDK1.2后，引用分为

1. 强引用：最普通的引用，永远不会回收存在强引用的对象
2. 软引用：描述有用但非必须的对象，在发生内存溢出前才会讲这些对象列入回收范围内
3. 弱引用：弱饮用关联的对象只能存活到下一次gc之前
4. 虚引用：唯一目的是能在此对象被回首时收到一个系统通知

## 回收方法区

主要回收两部分：废弃常量和无用的类。

## 垃圾收集算法

### 1.标记清除算法

首先标记所有需要回收的对象，标记完成后统一回收。（效率不高，且会产生碎片）

### 2.复制算法

将内存按容量划分为两块，每次只使用一块，回收时将存活对象复制到另一块，然后一次性清理。

实际中划分内存的一般是分为一块较大的Eden空间和两块较小的Survivor空间。每次使用一块Eden和一块Survivor，回收时将Eden和Survivor中存活的对象豆腐知道另一个Survivor上，最后清理空间。默认Eden和Survivor大小比例时8:1。

### 3.标记-整理算法

让所有存货的对象向一段移动，然后清理掉边界以外的内存。

### 4.分代收集算法

## HotSpot算法实现

### 1.枚举根节点

1. 可作为GCRoots节点主要在全局性的引用和执行上下文中。但往往数量巨大
2. 可达性分析必须在一个能确保一致性的快照中进行，GC进行时必须停顿所有Java执行线程(Stop the World)
3. 目前主流JVM使用的都是准确式GC，系统不需要检查完所有执行上下文和全局引用，而是直接得知那些地方存在对象引用。HotSpot通过OopMap达到这个目的

### 2.安全点

HotSpot没有为每一条指令都生成OopMap，只是在特定的位置记录了这些信息。这些位置称为安全点。

### 3.安全区域

解决了程序不执行(如sleep或blocked状态)时期进入GC的的问题。安全区域中代码的引用关系不会发生任何变化，任意地方开始的GC都是安全的

## 垃圾收集器

### 1.Serial

单线程，进行垃圾收集时必须暂停其他所有工作线程。

### 2.ParNew

Serial的多线程版本

### CMS

以获取最短回收停顿时间为目标的收集器。

