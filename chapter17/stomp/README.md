# 使用STOMP发送消息  

&nbsp;&nbsp;&nbsp;&nbsp;在使用WebSocket时，通常使用STOMP等子协议作为客户端和服务器之间的通用格式，以便客户端和服务器都知道应该发生什么并作出相应的反应。Spring框架支持STOMP。  
&nbsp;&nbsp;&nbsp;&nbsp;STOMP是一种简单的基于帧的消息传递协议（建立在HTTP基础之上），可以用于任何可靠的双向流网络协议，比如WebSocket。STOMP由标准的协议格式；在浏览器中可以利用JavaScript客户端支持来发送和接收消息，并可选择插入支持STOMP的传统消息代理（比如RabbitMQ和ActiveMQ）。Spring框架支持一个简单的代理，可以用来处理订阅请求以及向内存中链接的客户端广播消息。在本例中，将使用这个简单的代理，并将全功能的代理设置作为联系。  
> ⚠ 有关STOMP协议的完整说明，请参阅http://stomp.github.io/stomp-specification-1.2.html  

&nbsp;&nbsp;&nbsp;&nbsp;在STOMP示例中，将创建一个简单的股票行情应用程序，该应用程序显示一些预定义的股票代码、它们的当前价格以及价格变化时的时间戳。新的股票代码和开盘价格也可以通过用户界面添加。任何连接的客户端将看到与订阅消息广播相同的数据。每一秒，每只股票的价格都会更新为新的随机价格并更新时间戳。  
&nbsp;&nbsp;&nbsp;&nbsp;为了确保客户能够使用该股票行情的应用程序，即使他们的浏览器不支持WebSocket，也会使用SocketJS来透明地处理任何传输交换。需要注意的是，由spring-messaging库提供STOMP消息支持。