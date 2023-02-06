/**
 * 
 */
package com.prodemy.springmvc.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.prodemy.springmvc.dao.MahasiswaDao;
import com.prodemy.springmvc.model.Mahasiswa;


@Repository
public class MahasiswaDaoImpl implements MahasiswaDao {
	private Connection con = null;
	private String url = "jdbc:postgresql://localhost:5433/mahasiswa";
	private String username = "postgres";
	private String password = "postgres";

	public MahasiswaDaoImpl() {
		try {
			Class.forName("org.postgresql.Driver");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws Exception {
		if (con!=null) {
			if (con.isClosed()) {
				con = DriverManager.getConnection(url, username, password);
			}
		} else {
			con = DriverManager.getConnection(url, username, password);
		}
		return con;
	}
	
	@Override
	public Mahasiswa findById(String nim) throws Exception {
		Mahasiswa result = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder("SELECT * FROM data_mahasiswa WHERE nim=?");
			ps = getConnection().prepareStatement(query.toString());
			ps.setString(1, nim);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = new Mahasiswa();
				result.setNama(rs.getString("nama"));
				result.setTgl_lahir(rs.getString("tgl_lahir"));
				result.setAlamat(rs.getString("alamat"));
				result.setJurusan(rs.getString("jurusan"));
			}
		} finally {
			try {
				rs.close();
			} catch (Exception ignored) {}
			try {
				ps.close();
			} catch (Exception ignored) {}
		}
		
		return result;
	}

	@Override
	public void deleteById(String nim) throws Exception {
		PreparedStatement ps = null;
		
		try {
			StringBuilder query = new StringBuilder("DELETE FROM data_mahasiswa WHERE nim=?");
			ps = getConnection().prepareStatement(query.toString());
			ps.setString(1, nim);
			ps.executeUpdate();
		} finally {
			try {
				ps.close();
			} catch (Exception ignored) {}			
		}
	}

	@Override
	public void insert(Mahasiswa mhs) throws Exception {
		PreparedStatement ps = null;
		
		try {
			StringBuilder query = new StringBuilder("INSERT INTO data_mahasiswa (nim,nama,tgl_lahir,alamat,jurusan) VALUES (?,?,?,?,?)");
			ps = getConnection().prepareStatement(query.toString());
			ps.setString(1, mhs.getNim());
			ps.setString(2, mhs.getNama());
			ps.setString(3, mhs.getTgl_lahir());
			ps.setString(4, mhs.getAlamat());
			ps.setString(5, mhs.getJurusan());
			ps.executeUpdate();
		} finally {
			try {
				ps.close();
			} catch (Exception ignored) {}			
		}
	}

	@Override
	public void update(Mahasiswa mhs) throws Exception {
		PreparedStatement ps = null;
		
		try {
			StringBuilder query = new StringBuilder("UPDATE data_mahasiswa SET nim=?, nama=?, tgl_lahir=?, alamat=?, jurusan=? WHERE nim=?");
			ps = getConnection().prepareStatement(query.toString());
			ps.setString(1, mhs.getNama());
			ps.setString(2, mhs.getTgl_lahir());
			ps.setString(3, mhs.getAlamat());
			ps.setString(4, mhs.getJurusan());
			ps.setString(5, mhs.getNim());
			ps.executeUpdate();
		} finally {
			try {
				ps.close();
			} catch (Exception ignored) {}			
		}
	}

	@Override
	public List<Mahasiswa> findAll() throws Exception {
		List<Mahasiswa> result = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder("SELECT * FROM data_mahasiswa");
			ps = getConnection().prepareStatement(query.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				result = new ArrayList<>();
				do {
					Mahasiswa mhs = new Mahasiswa();
					mhs.setNim(rs.getString("nim"));
					mhs.setNama(rs.getString("nama"));
					mhs.setTgl_lahir(rs.getString("tgl_lahir"));
					mhs.setAlamat(rs.getString("alamat"));
					mhs.setJurusan(rs.getString("jurusan"));
					
					result.add(mhs);
				} while (rs.next());
			}
		} finally {
			try {
				rs.close();
			} catch (Exception ignored) {}
			try {
				ps.close();
			} catch (Exception ignored) {}
		}
		
		return result;
	}

}
