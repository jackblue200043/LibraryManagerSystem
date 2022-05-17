<h1>项目简介</h1>
<p>
    1. 此项项目使用MVC + DAO的设计模式,使得项目结构清晰,代码易于管理
       , 所有的功能都是由组件的方式实现.
</p>
<ul>
    <caption>包名的意义</caption>
    <li>filter: 过滤器主键, 先于特定监听sevlet运行, 可以进行预处理用户提交的请求</li>
    <li>povo: 数据层, 在项目中所有的数据都是以<strong>值javaBean</strong>的形式出现的;</li>
    <li>util: 工具类, 比如数据库的管理, 中文乱码解决等通用工具类</li>
    <li>
        dao: 数据库访问接口, 接口与实现, 基于数据库的原始操作
        <ul>
            <li>dao模式要有数据库访问工具类获得connection对象</li>
            <li>要有数据类javaBean</li>
            <li>要有dao接口, 及其实现类</li>
            <li>还有由一个工厂factory(静态获取dao实例的类)</li>
        </ul>
       </li>
    <li>service: 进行具体的业务处理, 使用得到的数据进行处理</li>
    <li>servlet: mvc模式的controller层, 一般实现HeepServlet中的ser</li>
</ul>

<h2>要实现的功能</h2>
<ul>
    <li>主页, 实现图书库存查询(跳转到具体的显示页面, 或使用ifram), 与导航(首页 用户 网盘 留言板功能 管理员页面)</li>
    <li>图书查询, 使用使用多种方式检索数据库</li>
    <li>用户管理, 可以依靠数据库(如果可以的化实现头像显示), 查看自己图书的借阅情况</li>
    <li>留言板: 实现显示多个用户的留言, 显示留言时间</li>
    <li>网盘: 进行个人文件管理, 上传于下载</li>
    <li>管理员页面: 两个功能1. 创建借阅信息, 查看借阅信息特定书籍的, 添加图书</li>
</ul>

<h2>网站页面布局</h2>
<ul>
    <li>统一最小尺寸965px, 进行导航栏复用, 每个页面都具有导航栏, 固定大小方便布局;</li>
</ul>

<h2>数据库设计</h2>
根据实现的功能, 主要有 用户表, 图书表, 借阅信息, 文件表, 留言表
<h3>用户表users</h3>
<ul>
    <li>字段: 1. userName 2. passWord 3.nikName 4.sex 5.address  6.phoneNumber</li>
    <li>主键: userName</li>
    <li>在注册时进行表单验证</li>
    <li>字段属性：</li>
</ul>

<h3>图书表books</h3>
<ul>
    <li>字段: 1.bookId 2.bookName 3.type 4.publusher 5.author  6.place 7.stock 8.time</li>
    <li>组件: bookId作为主键</li>
</ul>

<h3>借阅信息表borrow</h3>
<ul>
    <li>字段: 1.userName 2.bookId 3.start 4.flag(状态) 5.timeLeft(剩余时间) 6.isOverdue(是否逾期)</li>
    <li>主键: userName bookId 外键: userName bookId</li>
    <li>使用数据库自动调用存储过程, 如果状态为未还那么, 每天计算剩余时间, 于是否逾期</li>
</ul>

<h3>文件类file</h3>
<ul>
    <li>字段: 1.fileId(自动增加) 2.userName 3.fileName 4.fileType 5.size 6.directory</li>
    <li>主键:fileId 外键: userName</li>
    <li>使用userName建立的个用户文件夹放入上传文件, 并可以进行上传, 下载</li>
    <li>directory : varchar(60)</li>
</ul>

<h3>留言板messageBoard</h3>
<ul>
    <li>字段: 1.numAuto(自动增加字段) 2.userName 3.message 4.time</li>
    <li>主键: numAuto  外键userName</li>
 </ul>