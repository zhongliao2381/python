package recommend;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import com.yc.bean.LableBean;
import com.yc.dao.LableDao;

public class LableDaoTest {
	LableDao lDao=new LableDao();
	@Test
	public void test() throws Exception {
		String [] a={"1","2"};
		
		for(Entry<String, List<LableBean>> map:lDao.findDishes().entrySet() ){
			System.out.println(map.getKey()+map.getValue().size());
		}
		
	}

}
