##小插曲
由于执行``brew install redis``时出现了错误
```Error: 
     homebrew-core is a shallow clone.
   To `brew update`, first run:
     git -C /usr/local/Homebrew/Library/Taps/homebrew/homebrew-core fetch --unshallow
   This command may take a few minutes to run due to the large size of the repository.
   This restriction has been made on GitHub's request because updating shallow
   clones is an extremely expensive operation due to the tree layout and traffic of
   Homebrew/homebrew-core and Homebrew/homebrew-cask. We don't do this for you
   automatically to avoid repeatedly performing an expensive unshallow operation in
   CI systems (which should instead be fixed to not use shallow clones). Sorry for
   the inconvenience!
```
所以在brew上倒弄了一些时间,也记录一下吧。

更新brew是报错如上
brew update
按提示执行
```
git -C /usr/local/Homebrew/Library/Taps/homebrew/homebrew-core fetch --unshallow
```
结果会爆
`RPC failed; curl 56 LibreSSL SSL_read: SSL_ERROR_SYSCALL, errno 54`
最终解决：
把homebrew-core给删除
rm -rf /usr/local/Homebrew/Library/Taps/homebrew/homebrew-core
然后再次执行
brew update
如有失败再次执行一次
brew update



##redis安装以及配置redis主从复制
**1、安装redis**
`brew install redis`
**2、创建redis数据存储目录**
`mkdir /Users/sunchangji/Documents/GitHub/geek-java/week12/logs/redis0`
`mkdir /Users/sunchangji/Documents/GitHub/geek-java/week12/logs/redis1`
**3、切到第一台redis启动文件配置目录**
`cd /Users/sunchangji/Documents/GitHub/geek-java/week12`
**4、启动第一台redis服务**
`redis-server redis6379.conf`
**5、切到第二台redis启动文件配置目录**
`cd /Users/sunchangji/Documents/GitHub/geek-java/week12`
**6、启动第二台redis启动文件配置目录**
`redis-server redis6380.conf`
**7、连接6379redis服务**
`redis-cli -p 6379`
**8.查看所有数据**
`keys *`
**9、设置数据**
`set suncj01 11`
**10、获取数据**
`get suncj01`
**11、连接6380redis服务**
`redis-cli -p 6380`
`keys *`
`set suncj01 666`
`get suncj01`
**12、配置6380是6379的从**
`SLAVEOF 127.0.0.1 6379`
13、`get suncj01` 此时看到的数据变成了111