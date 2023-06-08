# bangiao_bot
一个基于cqhttp的qq群机器人

# 使用方法

1. 下载go cqhttp
2. 登录 cq
3. 启动我们的项目就可以了

> 对了, 在启动项目前需要配置JVM启动参数
   ```
       -Dbot_qq=2033xxxx8 # 机器人qq, 也是邮箱的发送者
       -Demail_password=ngxxxxxx # 去邮箱官方去申请, 默认是 stmp 协议发送
       -Dadmin_qq=20xxxxxxx8 # 系统最高权限qq, 保证数据库在没有任何用户时使用该qq可以使用
       -Dto_email=20xxxxxxx7 # 可选, 默认是admin_qq
   ```
> 其中 `admin_qq` 必须配置, 要想邮箱生效, `bot_qq` 和 `email_password` 也需要配置
> 
> `email_password` 可以去任意邮箱官方申请, 这里我以qq邮箱为例
> `[首页]` => `[设置]` => `[POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务]`

## chatGPT相关配置

初次使用需要添加 chatGPT 的 key , 我们的项目默认使用
<a href="https://github.com/chatanywhere/GPT_API_free">chatanywhere提供的chatGPT国内节点</a>
如果需要使用, 就去申请

之后私聊机器人, `#add 你申请到的key` 添加到数据库, 之后 chatGPT 就可以用了
