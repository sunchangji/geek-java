**1、首先配置6个redis.config文件,redis cluster需要3master 3slave,所以需要至少6个redis节点服务**
这6个redis.config文件分别是:redis6379.conf、redis6380.conf、redis6381.conf、redis6382.conf、redis6383.conf
 2、在配置文件中修改集群相关配置参数
 `port 7000`修改为对应端口号
 `cluster-enabled yes`
 `cluster-config-file nodes.conf`
 `cluster-node-timeout 5000`
 `appendonly yes`
 
 **3、分别启动这6个redis节点服务**
 cd /Users/sunchangji/Documents/GitHub/geek-java/week12
 redis-server redis6379.conf
 redis-server redis6380.conf
 redis-server redis6381.conf
 redis-server redis6382.conf
 redis-server redis6383.conf
 redis-server redis6384.conf
 
 **4、再开一个命令行窗口配置集群**
`redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 --cluster-replicas 1`
启动信息：
```$xslt
sunchangjideMacBook-Pro-2% redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 --cluster-replicas 1
>>> Performing hash slots allocation on 6 nodes...
Master[0] -> Slots 0 - 5460
Master[1] -> Slots 5461 - 10922
Master[2] -> Slots 10923 - 16383
Adding replica 127.0.0.1:6383 to 127.0.0.1:6379
Adding replica 127.0.0.1:6384 to 127.0.0.1:6380
Adding replica 127.0.0.1:6382 to 127.0.0.1:6381
>>> Trying to optimize slaves allocation for anti-affinity
[WARNING] Some slaves are in the same host as their master
M: 1c8f599861531740b5c66a4ef217316f46c91fc1 127.0.0.1:6379
   slots:[0-5460] (5461 slots) master
M: f67fe8ea773b6f076fa8e59814f854411ce362de 127.0.0.1:6380
   slots:[5461-10922] (5462 slots) master
M: 5e184092512a8e97538ae3190ec0502e48f9190f 127.0.0.1:6381
   slots:[10923-16383] (5461 slots) master
S: 43938493a02735931e7c5b053aae7e08d2cc05c9 127.0.0.1:6382
   replicates 1c8f599861531740b5c66a4ef217316f46c91fc1
S: d0395ccbd64eb64d5851b37d43aac340c0e674c2 127.0.0.1:6383
   replicates f67fe8ea773b6f076fa8e59814f854411ce362de
S: 146c6880e3fed4e8fa00ff4f7bf3c10722873b8c 127.0.0.1:6384
   replicates 5e184092512a8e97538ae3190ec0502e48f9190f
Can I set the above configuration? (type 'yes' to accept): yes
```
**5、看到如下提示时,输入yes**
Can I set the above configuration? (type 'yes' to accept):
```$xslt
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join
.
>>> Performing Cluster Check (using node 127.0.0.1:6379)
M: 1c8f599861531740b5c66a4ef217316f46c91fc1 127.0.0.1:6379
   slots:[0-5460] (5461 slots) master
   1 additional replica(s)
S: 146c6880e3fed4e8fa00ff4f7bf3c10722873b8c 127.0.0.1:6384
   slots: (0 slots) slave
   replicates 5e184092512a8e97538ae3190ec0502e48f9190f
M: f67fe8ea773b6f076fa8e59814f854411ce362de 127.0.0.1:6380
   slots:[5461-10922] (5462 slots) master
   1 additional replica(s)
S: 43938493a02735931e7c5b053aae7e08d2cc05c9 127.0.0.1:6382
   slots: (0 slots) slave
   replicates 1c8f599861531740b5c66a4ef217316f46c91fc1
S: d0395ccbd64eb64d5851b37d43aac340c0e674c2 127.0.0.1:6383
   slots: (0 slots) slave
   replicates f67fe8ea773b6f076fa8e59814f854411ce362de
M: 5e184092512a8e97538ae3190ec0502e48f9190f 127.0.0.1:6381
   slots:[10923-16383] (5461 slots) master
   1 additional replica(s)
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.
```
**6、集群配置完成,然后命令行连接集群redis**
`redis-cli -c -p 6379`
具体操作信息如下:

sunchangjideMacBook-Pro-2% redis-cli -c -p 6379
127.0.0.1:6379> set suncj0 1111
-> Redirected to slot [7852] located at 127.0.0.1:6380
OK
127.0.0.1:6380> set hello world
-> Redirected to slot [866] located at 127.0.0.1:6379
OK
127.0.0.1:6379> get hello
"world"
127.0.0.1:6379> get suncj0
-> Redirected to slot [7852] located at 127.0.0.1:6380
"1111"
127.0.0.1:6380>

 