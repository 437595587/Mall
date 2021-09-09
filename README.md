# 若依--商城项目

## 相关文件说明：

## 1、dev-tools

nacos、seata、sentinel等服务

## 2、sql

数据库文件，基本的商城表。

nacos配置中心的配置和seata配置 有关的表

## 3、mydata

docker容器中的配置

## 4、需要启动的服务

openzipkin/zipkin:2.23

rabbitmq:3.8.22-management

nginx:1.10

kibana:7.4.2

elasticsearch:7.4.2

mysql8

redis3.2

## 5、主机Host配置
192.168.1.16 mall.com

192.168.1.16 search.mall.com

192.168.1.16 item.mall.com

192.168.1.16 auth.mall.com

192.168.1.16 cart.mall.com

192.168.1.16 order.mall.com

192.168.1.16 member.mall.com

192.168.1.16 seckill.mall.com



127.0.0.1 ssoserver.com

127.0.0.1 client1.com

127.0.0.1 client2.com

