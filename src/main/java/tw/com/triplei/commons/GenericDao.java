package tw.com.triplei.commons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GenericDao<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
