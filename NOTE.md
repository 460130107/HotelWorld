# 项目开展过程的坑和记录

- ajax提交
    - 注意传json要设置contentType，stringify;普通的文本传js对象就可以了
    - 第一种形式
    ```javascript
    $.ajax({
        type:"POST",
        contentType:"application/json",
        url:"json/update",
        data:JSON.stringify(data),
        success:function(msg){
            console.log(msg);
        },
        error:function(){
            console.log("error");
        },
        complete : function(XMLHttpRequest,status){
        }
    });
    ```
    - 第二种形式
    ```javascript
    $.ajax({
        type:"POST",
        url:"json/charge",
        data:data,
        success:function(msg){
            msg=JSON.parse(msg);
        },
        error:function(){
            console.log("error");
        },
        complete : function(XMLHttpRequest,status){
            cancleCharge();
        }
      });
    ```
    
- manager问题
    - 不知道tomcat是不是对于manager这个单词有什么特别的用途，反正在jsp里写manager就会有各种报错，改成managers就好了
- spring标签trick：可以在命名的时候，book.hotel.id然后传入对象就会自动注入
- spring mvc注解记录
    - @RequestParam
        - 对request中简单的类型的绑定，String，
        - Content-Type: 为 application/x-www-form-urlencoded编码的内容，提交方式GET、POST
    - @ModelAttribute
        - spring表单提交自动创建对象
        @PostMapping("/login")
        ```java
        public String login(@ModelAttribute LoginInfo loginInfo){
        }
        ```
    - @RequestBody
        - 往往用在传json数据的时候，能够绑定对象
    - @PathVariable绑定url里的参数
        - 还可以结合spring data jpa,直接查找对象
    ```java
    public String showUserForm(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        return "userForm";
      }
     ```
    - @sessionattributes
        - 应用到Controller上面，可以将Model中的属性同步到session当中
- el表达式
    - 要通过request，session一直找。之前写了个session和request的属性一样，然后只想找request里的，居然request里没有找到了session里的
    - 想要登陆信息里能够展示登陆error的问题，但是真tm的坑多
    - <c:if test="${} " ...... // error 
    注意 “” 与{} 之间不能有空格
    test="${}"  //success
    - javabean中的属性前三个字母，必须小写！
      ${paper.pNum}和${paper.pSize}不能找到
      而${paper.totalSize}和${paper.totalPaper}却找的到
- url不能用manager
-  sass --watch main-hotel.scss:style-hotel.css
- 获取自增的主键，加注解，数据库建表
- @GenerateValue
- p标签里面不能放ul，div，只能放段落内容Phrasing content
https://www.w3.org/TR/html5/grouping-content.html#the-p-element
- Sectioning content：article aside nav section
- flow content：用于文档的内容和应用内容，除了metadata几乎都是
- Phrasing content：a abbr area (if it is a descendant of a map element) audio b bdi bdo br button canvas cite code data datalist del dfn em embed i iframe img input ins kbd keygen label map mark math meter noscript object output progress q ruby s samp script select small span strong sub sup svg template textarea time u var video wbr text
- Heading content：h1 h2 h3 h4 h5 h6
- Embedded content：audio canvas embed iframe img math object svg video
- 关于java到底怎么得到是分秒为0的日期还是不知道，最后用js解决问题了。