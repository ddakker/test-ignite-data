package pe.kr.ddakker.test.ignite.data.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pe.kr.ddakker.test.ignite.data.app.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select(" SELECT * FROM user ")
    public List<User> getUsers();

    @Select(" SELECT * FROM user where username = #{username}")
    List<User> getUser(String username);
}
