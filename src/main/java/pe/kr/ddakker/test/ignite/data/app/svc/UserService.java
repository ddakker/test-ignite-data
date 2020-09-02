package pe.kr.ddakker.test.ignite.data.app.svc;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.kr.ddakker.test.ignite.data.app.cache.UserCacheNative;
import pe.kr.ddakker.test.ignite.data.app.cache.UserCacheRepository;
import pe.kr.ddakker.test.ignite.data.app.domain.User;
import pe.kr.ddakker.test.ignite.data.app.mapper.UserMapper;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserCacheRepository userCacheRepository;

    @Autowired
    UserCacheNative userCacheNative;

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userCacheRepository.findAll().forEach(users::add);
        logger.info("===== CACHE users: {}", users);

        if (users == null || users.size() == 0) {
            users = userMapper.getUsers();
            logger.info("===== DB users: {}", users);
            for (User user : users) {
                userCacheRepository.save(user.getUsername(), user);
            }
        }


        return users;
    }


    public List<User> getUser(String username) {
        List<User> users = userCacheRepository.getByUsername(username);
        logger.info("===== CACHE users: {}", users);

        if (users == null || users.size() == 0) {
            users = userMapper.getUser(username);
            logger.info("===== DB users: {}", users);
            for (User user : users) {
                userCacheRepository.save(user.getUsername(), user);
            }
        }
        return users;
    }

    public List<User> getUserQuery(String username) {
        List<User> users = userCacheNative.getUserQuery(username);

        logger.info("===== CACHE users: {}", users);

        if (users == null || users.size() == 0) {
            users = userMapper.getUser(username);
            logger.info("===== DB users: {}", users);
            for (User user : users) {
                userCacheRepository.save(user.getUsername(), user);
            }
        }
        return users;
    }
}
