package frontweb.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frontweb.Dept;
import frontweb.Emp;
import frontweb.vo.EmpDTO;
import frontweb.vo.Member;

public class PreparedStmtDao {
	public Object template(String dname) {
		Object ob = new Object();
		String sql = "select * from dept01 where dname like ? ";

		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			pstmt.setString(1, "%" + dname + "%");
			try (ResultSet rs = pstmt.executeQuery();) {
				// 처리코드 2
				rs.next();
				/*while(rs.next()) {
					ob.add(new Dept(
								rs.getInt("deptno"),
								rs.getString("dname"),
								rs.getString("loc")
							));
				}
				System.out.println("데이터 건수 : "+ dlist.size());*/
			}

		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		return ob;
	}

	public List<Dept> getDeptList(String dname) {
		List<Dept> dlist = new ArrayList<Dept>();
		String sql = "select deptno, dname, loc from dept01 where dname like ? ";

		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, "%" + dname + "%");
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					dlist.add(new Dept(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")));
				}
				System.out.println("데이터 건수 : " + dlist.size());
			}

		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		return dlist;
	}

	public List<Dept> getDeptList(String dname, String loc) {
		List<Dept> dlist = new ArrayList<Dept>();
		String sql = "select deptno, dname, loc " + "from dept " + "where dname like ? " + " and loc like ?";

		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, "%" + dname + "%");
			pstmt.setString(2, "%" + loc + "%");
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					dlist.add(new Dept(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")));
				}
				System.out.println("데이터 건수 : " + dlist.size());
			}

		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		return dlist;
	}

	/*
	SELECT *
	FROM emp
	WHERE ename LIKE ?
	AND job LIKE ?
	AND deptno = ?
	 * */
	public List<Emp> getEmpList(Emp sch) {
		List<Emp> empList = new ArrayList<Emp>();
		String sql = "SELECT *\r\n" 
				+ "FROM emp01\r\n" 
				+ "WHERE ename LIKE ?\r\n" 
				+ "AND job LIKE ?\r\n";
		
		if(sch.getDeptno() != 0) {
			sql += "AND deptno = ?";
		}
		sql += "order by empno";

		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			pstmt.setString(1, "%" + sch.getEname() + "%");
			pstmt.setString(2, "%" + sch.getJob() + "%");
			if (sch.getDeptno() != 0) { 
				pstmt.setInt(3, sch.getDeptno());
			}

			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					empList.add(new Emp(
							/*rs.getInt(0),
							rs.getString(1),
							rs.getString(2),
							rs.getInt(3),
							rs.getDate(4),
							rs.getDouble(5),
							rs.getDouble(6),
							rs.getInt(7)*/
							rs.getInt("empno"), 
							rs.getString("ename"), 
							rs.getString("job"), 
							rs.getInt("mgr"),
							rs.getDate("hiredate"), 
							rs.getDouble("sal"), 
							rs.getDouble("comm"), 
							rs.getInt("deptno")));
				}
				System.out.println("데이터 건수 : " + empList.size());
			}

		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}

		return empList;
	}

	public Member getMember(String id) {
		Member member = null;
		String sql = "SELECT *\r\n" + "FROM member01\r\n" + "WHERE id = ? ";

		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			pstmt.setString(1, id);

			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					member = new Member(rs.getInt("mno"), rs.getString("name"), rs.getString("id"), rs.getString("pwd"),
							rs.getString("auth"), rs.getInt("point"));
				}

			}
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}

		return member;
	}
	
	// INSERT INTO emp01 values(?, ?, ?, ?, to_date(?, 'YYYY-MM-DD'), ?, ?, ?)
	// new EmpDTO(1003, "하길동", "대리", 7799, "2023-11-01", 3500, 1000, 20);
	public int insertEmp01(EmpDTO ins) {
		int insCnt = 0;
		String sql = "INSERT INTO emp01 values(?, ?, ?, ?, "
				+ "to_date(?, 'YYYY-MM-DD'), ?, ?, ?)";
		
		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			con.setAutoCommit(false);
			pstmt.setInt(1, ins.getEmpno());
			pstmt.setString(2, ins.getEname());
			pstmt.setString(3, ins.getJob());
			pstmt.setInt(4, ins.getMgr());
			pstmt.setString(5, ins.getHiredateStr());
			pstmt.setDouble(6, ins.getSal());
			pstmt.setDouble(7, ins.getComm());
			pstmt.setInt(8, ins.getDeptno());
			
			insCnt = pstmt.executeUpdate();

			con.commit();
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		
		return insCnt;
	}
	
	public EmpDTO getEmp(int empno) {
		EmpDTO emp = null;
		String sql = "SELECT e.*, to_char(hiredate, 'YYYY-MM-DD') hiredatestr \r\n" 
				+ "FROM emp01 e\r\n" 
				+ "WHERE empno = ? ";

		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			pstmt.setInt(1, empno);

			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					emp = new EmpDTO(
							rs.getInt("empno"),
							rs.getString("ename"),
							rs.getString("job"),
							rs.getInt("mgr"),
							rs.getString("hiredatestr"),
							rs.getDouble("sal"),
							rs.getDouble("comm"),
							rs.getInt("deptno")
						);
				}

			}
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}

		return emp;
	}
	
	public int updateEmp01(EmpDTO upt) {
		int uptCnt = 0;
		String sql = "UPDATE emp01\r\n"
				+ "SET ename = ?,\r\n"
				+ "	job = ?,\r\n"
				+ "	mgr = ?,\r\n"
				+ "	hiredate = to_date(?, 'YYYY-MM-DD'),\r\n"
				+ "	sal = ?,\r\n"
				+ "	comm = ?,\r\n"
				+ "	deptno = ?\r\n"
				+ "WHERE empno = ?";
		
		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			pstmt.setString(1, upt.getEname());
			pstmt.setString(2, upt.getJob());
			pstmt.setInt(3, upt.getMgr());
			pstmt.setString(4, upt.getHiredateStr());
			pstmt.setDouble(5, upt.getSal());
			pstmt.setDouble(6, upt.getComm());
			pstmt.setInt(7, upt.getDeptno());
			pstmt.setInt(8, upt.getEmpno());
			uptCnt = pstmt.executeUpdate();
			
			if(uptCnt == 0) {
				System.out.println("수정실패");
				con.rollback();
			} else {
				con.commit();
				System.out.println("수정성공");
			}

		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		
		return uptCnt;
	}
	
	public int deleteEmp01(int empno) {
		int delCnt = 0;
		String sql = "delete from emp01 where empno = ?";
		
		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			pstmt.setInt(1, empno);
			delCnt = pstmt.executeUpdate();
			
			if(delCnt == 0) {
				System.out.println("삭제 실패");
				con.rollback();
			} else {
				con.commit();
				System.out.println("삭제 성공");
			}

		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		
		return delCnt;
	}
	
	public List<Dept> getDept01List(String dname, String loc) {
		List<Dept> dlist = new ArrayList<Dept>();
		String sql = "select deptno, dname, loc " + "from dept01 " + "where dname like ? " + " and loc like ?";

		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, "%" + dname + "%");
			pstmt.setString(2, "%" + loc + "%");
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					dlist.add(new Dept(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")));
				}
				System.out.println("데이터 건수 : " + dlist.size());
			}

		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		return dlist;
	}
	
	public int insertDept(Dept dept) {
		int insCnt = 0;
		String sql = "INSERT INTO dept01 values(?, ?, ?)";
		
		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDname());
			pstmt.setString(3, dept.getLoc());
			
			insCnt = pstmt.executeUpdate();

			con.commit();
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		
		return insCnt;
	}
	
	public Dept getDept01(int deptno) {
		Dept dept = null;
		String sql = "select * from dept01 where deptno = ?";
		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			pstmt.setInt(1, deptno);

			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					dept = new Dept(
								rs.getInt("deptno"),
								rs.getString("dname"),
								rs.getString("loc")
							);
				}

			}
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		
		return dept;
	}
	
	/*
	update dept01
	set dname = ?
	loc = ?
	where deptno = ?
	 * */
	public int updateDept01(Dept dept) {
		int uptCnt = 0;
		String sql = "update dept01\r\n"
				+ "	set dname = ?\r\n"
				+ "	loc = ?\r\n"
				+ "	where deptno = ?";
		
		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			pstmt.setString(1, dept.getDname());
			pstmt.setString(2, dept.getLoc());
			pstmt.setInt(3, dept.getDeptno());

			uptCnt = pstmt.executeUpdate();
			
			if(uptCnt == 0) {
				System.out.println("수정실패");
				con.rollback();
			} else {
				con.commit();
				System.out.println("수정성공");
			}
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		
		return uptCnt;
	}
	
	public int deleteDept01(int deptno) {
		int uptCnt = 0;
		String sql = "delete from dept01 where deptno = ?";
		
		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			pstmt.setInt(1, deptno);

			uptCnt = pstmt.executeUpdate();
			
			if(uptCnt == 0) {
				System.out.println("삭제 실패");
				con.rollback();
			} else {
				con.commit();
				System.out.println("삭제성공");
			}
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		
		return uptCnt;
	}

	public static void main(String[] args) {
		PreparedStmtDao dao = new PreparedStmtDao();
		/*for(Dept d : dao.getDeptList("O")) {
			System.out.println("==================");
			System.out.println(d.getDeptno());
			System.out.println(d.getDname());
			System.out.println(d.getLoc());
		}*/
		// dao.insertEmp01(new EmpDTO(1004, "구길동", "과장", 7799, "2023-12-01", 5500, 1000, 20));
		
		for (Emp e : dao.getEmpList(new Emp("", "", 20))) {
			System.out.println("=====================");
			System.out.println(e.getEmpno());
			System.out.println(e.getEname());
			System.out.println(e.getDeptno());
			
		}
	}

}
