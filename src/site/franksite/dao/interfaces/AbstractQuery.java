/**
 * 
 */
package site.franksite.dao.interfaces;

import com.dbquery.dal.DBConnection;
import com.dbquery.dal.MySQLDBInfo;
import com.dbquery.interfaces.AbstractQueryObject;
import com.dbquery.interfaces.DBBasicInfo;

/**
 * @author Frank
 *
 */
public class AbstractQuery extends AbstractQueryObject {

	/* (non-Javadoc)
	 * @see com.dbquery.interfaces.AbstractQueryObject#setupDatabase()
	 */
	@Override
	protected void setupDatabase() {
		DBBasicInfo info = new MySQLDBInfo("frank", "data.jxufehelper.com");
		info.setPort(3306);
		info.setPassword("zouzhipeng123A");
		info.setUsername("frank");
		DBConnection connection = new DBConnection();
		connection.setDbinfo(info);
		this.dbManager = connection;
	}

}
