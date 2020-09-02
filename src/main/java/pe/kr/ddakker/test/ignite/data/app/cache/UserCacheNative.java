package pe.kr.ddakker.test.ignite.data.app.cache;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.kr.ddakker.test.ignite.data.app.domain.User;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class UserCacheNative {
    private static Logger logger = LoggerFactory.getLogger(UserCacheNative.class);

    public static final String USER_CACHE = "UserCache";

    @Autowired
    Ignite igniteInstance;

    IgniteCache igniteCache;

    @PostConstruct
    public void init() {
        igniteCache = igniteInstance.getOrCreateCache(USER_CACHE);
    }

    public List<User> getUserQuery(String username) {
        QueryCursor<User> cursor = igniteCache.query(new SqlFieldsQuery("SELECT username, password FROM user where username = ?").setArgs(username));
        return cursor.getAll();
    }
}