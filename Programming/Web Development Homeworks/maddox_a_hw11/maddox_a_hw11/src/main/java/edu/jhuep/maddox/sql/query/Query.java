package edu.jhuep.maddox.sql.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.jhuep.maddox.bean.Bean;
import edu.jhuep.maddox.constants.Constants.ColumnOrder;

//This class does the actual querying of the database.
public class Query {
	private static final String DATE = "$DATE";

	// query to be executed on the database. $DATE will be replaced by the inputDate value from the Bean when setQuery is called
	private String query = "SELECT r.StartDay, r.NumberOfDays, l.location, g.First, g.Last, r.First, r.Last "
			+ "FROM reservation as r, guides as g, locations as l "
			+ "WHERE (r.StartDay >= '" + DATE + "') AND r.guide = g.idguides AND r.location = l.idlocations "
			+ "ORDER BY r.StartDay ASC;";
	
	public Query(Bean bean) {
		setQuery(bean);
	}
	
	public Bean getResults(Bean bean, Connection connection) {
		List<Bean> beans = new ArrayList<Bean>();
		try (Statement statement = connection.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ResultSet rs = statement.executeQuery(query);){
			
			while(rs.next()) {
				Bean b = new Bean();
				b.setStartDay(rs.getString(ColumnOrder.START_DAY.toInt()));
				b.setDuration(rs.getString(ColumnOrder.DURATION.toInt()));
				b.setLocation(rs.getString(ColumnOrder.LOCATION.toInt()));
				b.setgFName(rs.getString(ColumnOrder.GUIDE_FIRST.toInt()));
				b.setgLName(rs.getString(ColumnOrder.GUIDE_LAST.toInt()));
				b.sethFName(rs.getString(ColumnOrder.HIKER_FIRST.toInt()));
				b.sethLName(rs.getString(ColumnOrder.HIKER_LAST.toInt()));
				beans.add(b);
			}
			
			bean.setBeans(beans);
			
		} catch (SQLException e) {
			e.printStackTrace();
			List<String> l = new ArrayList<String>();
			l.add("ERRROR: Unable to execute query");
			bean.setErrors(l);
		}

		return bean;
	}

	//used to set the $DATE value in the query field
	private void setQuery(Bean bean) {
		this.query = this.query.replace(DATE, bean.getsInputDate());
	}
}
