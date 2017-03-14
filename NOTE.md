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