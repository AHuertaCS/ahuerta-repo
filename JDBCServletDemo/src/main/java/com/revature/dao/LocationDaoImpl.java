package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Location;
import com.revature.util.ConnectionUtil;

public class LocationDaoImpl implements LocationDao {

	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<Location> getLocations() {
		List<Location> locations = new ArrayList<>();
		
		String sql = "SELECT * FROM LOCATIONS";
		
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				int id = rs.getInt("LOCATION_ID");
				String city = rs.getString("CITY");
				String street = rs.getString("STREET");
				String state = rs.getString("STATE");
				int zipcode = rs.getInt("ZIPCODE");
				locations.add(new Location(id, street, city, state, zipcode));
			}
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
		return locations;
	}

	@Override
	public Location getLocationById(int id) {
		Location l = null;
		
		String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";
		ResultSet rs = null;
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String street = rs.getString("STREET");
				String city = rs.getString("CITY");
				String state = rs.getString("STATE");
				int zipcode = rs.getInt("ZIPCODE");
				l = new Location(id, street, city, state, zipcode);
			}
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e.getMessage());
				} 
			}
		}
		
		return l;
	}
	
	@Override
	public Location getLocationById(int id, Connection con) {
		Location l = null;
		
		String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";
		ResultSet rs = null;
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String street = rs.getString("STREET");
				String city = rs.getString("CITY");
				String state = rs.getString("STATE");
				int zipcode = rs.getInt("ZIPCODE");
				l = new Location(id, street, city, state, zipcode);
			}
			
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return l;
	}

	
	@Override
	public int createLocation(Location location) {
		return 0;
	}

	@Override
	public int updateLocation(Location location) {
		return 0;
	}

	@Override
	public int deleteLocationById(int id) {
		return 0;
	}

}