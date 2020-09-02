package pe.kr.ddakker.test.ignite.data.app.cache;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import pe.kr.ddakker.test.ignite.data.app.domain.User;

import java.util.List;

@RepositoryConfig(cacheName = "UserCache")
public interface UserCacheRepository extends IgniteRepository<User, String> {
    public List<User> getByUsername(String username);
}