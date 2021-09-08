**1、切到sentinel0.conf配置文件所在目录**
`cd /Users/sunchangji/Documents/GitHub/geek-java/week12`

**2、启动第一台sentinel**
`redis-sentinel sentinel0.conf`

**3、再开个命令窗口,切到sentinel1.conf配置文件所在目录**
`cd /Users/sunchangji/Documents/GitHub/geek-java/week12`
**4、启动第二台sentinel**
`redis-sentinel sentinel1.conf`

**5、在6379的redis服务器上执行如下**
`set suncj02 888`

**6、切到6379的redis服务器命令窗口,ctrl+c关闭掉该redis服务**

**7、在连接6380的redis命令窗口执行**
`set suncj03 999`
**8、启动6379的redis服务**
`redis-server redis6379.conf`
**9、连接6379redis服务**
`redis-cli -p 6379`
**10、执行命令**
`get suncj03`


