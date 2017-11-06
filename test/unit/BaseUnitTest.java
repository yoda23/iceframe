package unit;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
@Transactional
@Rollback(false)
public class BaseUnitTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Override
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
}
