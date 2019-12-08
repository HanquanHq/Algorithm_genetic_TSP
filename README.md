# Algorithm_genetic_TSP
人工智能导论：使用遗传算法，解决TSP问题

#### Symmetric traveling salesman problem (TSP)
Given a set of n nodes and distances for each pair of nodes, find a roundtrip of minimal total length visiting each node exactly once. The distance from node i to node j is the same as from node j to node i.

### 旅行商问题
假设有一个旅行商人要拜访n个城市，他必须选择所要走的路径，路径的限制是**每个城市只能拜访一次，而且最后要回到原来出发的城市**。路径的选择目标是要求得的路径路程为所有路径之中的最小值。

#### [附：TSPLIB上的旅行商问题标准测试用例](http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/tsp/)

CSDN博客地址：https://blog.csdn.net/sinat_42483341/article/details/103443859

----

### 遗传算法
生物进化与遗传算法

遗传算法是从代表问题可能潜在的解集的一个种群（population）开始的，而一个种群则由经过基因（gene）编码的一定数目的个体(individual)组成。每个个体实际上是染色体(chromosome)带有特征的实体。染色体作为遗传物质的主要载体，即多个基因的集合，其内部表现（即基因型）是某种基因组合，它决定了个体的形状的外部表现。因此，在一开始需要实现从表现型到基因型的映射即编码工作。如二进制编码;

初代种群产生之后，按照适者生存和优胜劣汰的原理，逐代（generation）演化产生出越来越好的近似解，在每一代，根据问题域中个体的适应度（fitness）大小选择（selection）个体，并借助于自然遗传学的遗传算子（genetic operators）进行组合交叉（crossover）和变异（mutation），产生出代表新的解集的种群。

这个过程将导致种群像自然进化一样的新生代种群比前代更加适应于环境，最后一代种群中的最优个体经过解码（decoding），可以作为问题近似最优解。
