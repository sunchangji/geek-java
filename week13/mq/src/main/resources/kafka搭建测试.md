##搭建一个3节点Kafka集群
### 1、在kafka官网下载包
`https://www.apache.org/dyn/closer.cgi?path=/kafka/2.8.0/kafka_2.13-2.8.0.tgz`
### 2、解压
### 3、配置集群文件
(1)、进入解压包`kafka_2.13-2.8.0/config`目录下,复制3分`server.properties`文件,分别为`kafka9090.properties`、`kafka9091.properties`、`kafka9092.properties`
(2)、修改对应每个复制出的三个文件如下几个配置项
broker.id=0  备注(# 三台服务器的ID不能相同，第一台是0，第二台是1，第三台是2)
log.dirs=/tmp/kafka-logs9090 备注(日志路径,kafka-logs9090,kafka-logs9091,kafka-logs9092)
打开listeners=PLAINTEXT://localhost:9090 备注(kafka服务器监听IP和端口,9090,9091,9092)
zookeeper.connect=localhost:2181/kafka
### 4、启动3集群
(1)切到kafka目录
`cd /Users/sunchangji/Documents/devtool/kafka_2.13-2.8.0`
(2)启动zookeeper
`nohup bin/zookeeper-server-start.sh config/zookeeper.properties &`
(3)依次启动3台kafka
`nohup bin/kafka-server-start.sh config/kafka9090.properties &
 nohup bin/kafka-server-start.sh config/kafka9091.properties &
 nohup bin/kafka-server-start.sh config/kafka9092.properties &`

### 5、测试功能
`cd /Users/sunchangji/Documents/devtool/kafka_2.13-2.8.0`
创建带有副本的 topic
`bin/kafka-topics.sh --zookeeper localhost:2181/kafka --create --topic test01 --partitions 3 --replication-factor 2`
启动生产者
`bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test01`
启动监听消费者
`bin/kafka-console-consumer.sh --bootstrap-server localhost:9091 --topic test01 --from-beginning`

### 6、压测
生产者压测
`bin/kafka-producer-perf-test.sh --topic test01 --num-records 100000 --record-size 1000 --throughput 100000 --producer-props bootstrap.servers=localhost:9091`
结果:
`100000 records sent, 20977.554017 records/sec (20.01 MB/sec), 1114.77 ms avg latency, 1974.00 ms max latency, 1094 ms 50th, 1802 ms 95th, 1908 ms 99th, 1967 ms 99.9th.`
消费者压测
`bin/kafka-consumer-perf-test.sh --bootstrap-server localhost:9091 --topic test01 --fetch-size 1048576 --messages 100000 --threads 1`
结果:
`WARNING: option [threads] and [num-fetch-threads] have been deprecated and will be ignored by the test
 start.time, end.time, data.consumed.in.MB, MB.sec, data.consumed.in.nMsg, nMsg.sec, rebalance.time.ms, fetch.time.ms, fetch.MB.sec, fetch.nMsg.sec
 2021-09-15 15:17:06:712, 2021-09-15 15:17:07:618, 95.3675, 105.2621, 100003, 110378.5872, 447, 459, 207.7722, 217871.4597`
