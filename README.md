Change CC register to int[].

Add registerStack and class ALU.

Change some functions in the control panel from Integer.parseInt to
Long.parseLong for avoiding integer overflow.

We cannot use Memory class to new Cache, so we add class Cache

以下是还没完成的部分：

1· IN和OUT需要IO操作，函数不完整，需要配合GUI

2· cache还没和CPU连在一起

3· update GUI

4· Demonstrate 1st program running on your simulator.

5· update design note