# secure
- 通过WebMvcConfig配置拦截器访问路径

- 接口调用结果可见枚举类EnumType,调用接口`ResultUtil.Result(EnumType enumType)`返回结果
    ```java
    return ResultUtil.Result(EnumType.SUCCESS);
    ```

- 引入tk.mybatis依赖，可通过mapper接口继承Mapper<T>方法实现Mapeer接口
    ```java
    @org.apache.ibatis.annotations.Mapper
    public interface UserInfoMapper extends Mapper<UserInfo> {
    }
    
    @Table(name = "userinfo")
    public class UserInfo {
        @Id
        private int id;
        //......
    }
    ```
- 引入lombok依赖，可通过@Getter、@Setter、@ToString等注释避免冗余代码
    ```java
    @Table(name = "userinfo")
    @Getter
    @Setter
    @ToString
    public class UserInfo {
        //......
    }
    ```