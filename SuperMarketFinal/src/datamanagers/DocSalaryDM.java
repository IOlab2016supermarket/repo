package datamanagers;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.DocSalary;
import main.Common;

public class DocSalaryDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id");
		Date date = super.getDate("date");
		String number = rs.getString("number");
		String period = rs.getString("period");
		int daysAll = rs.getInt("days");
		int daysWork = rs.getInt("days_work");
		float summa = Common.Round(super.getFloat("summa"), 2);			// From INNER JOIN
		
		return new DocSalary(id, date, number, period, daysAll, daysWork, summa);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT salary.*, "
				+ "SUM(salary_table.summa) AS summa "
				+ "FROM salary "
				+ "LEFT JOIN salary_table ON salary.id = salary_table.id_salary "
				+ "GROUP BY salary.id";

		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		DocSalary salary = (DocSalary) ent;
		
		String sql = "INSERT INTO salary ("
				+ "date, "
				+ "number, "
				+ "period, "
				+ "days, "
				+ "days_work"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "%s, "	// without <'>
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s'"
				+ ")",
				
				super.sqlDateFormat(salary.getDate(), false),
				salary.getNumber(),
				salary.getPeriod(),
				salary.getDaysAll(),
				salary.getDaysWork()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		DocSalary salary = (DocSalary) ent;
		
		String sql = "UPDATE salary SET "
				+ "date=%s, "	// without <'>
				+ "number='%s', "
				+ "period='%s', "
				+ "days='%s', "
				+ "days_work='%s' ";
		
		sql = String.format(
				sql, 
				super.sqlDateFormat(salary.getDate(), false),
				salary.getNumber(),
				salary.getPeriod(),
				salary.getDaysAll(),
				salary.getDaysWork()
			);
		
		sql += "WHERE id=" + salary.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM salary WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
}