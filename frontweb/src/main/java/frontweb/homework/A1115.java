package frontweb.homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frontweb.Emp;
import frontweb.database.DBCon;
import frontweb.vo.EmpHire;
import frontweb.vo.EmpHireTerm;

public class A1115 {

	/*
	1. 아래의 요청값을 처리하는 jsp화면을 만들어 보세요
	   1) **사용자 이름 출력하기**: 사용자로부터 이름을 입력받아, "안녕하세요, [이름]님!" 이라고 출력하는 페이지 만들기.
	   2)[생각보다 난이도 높음] **간단한 계산기**: 두 숫자와 연산자(+, -, *, /)를 입력받아 결과를 출력하는 JSP 페이지 만들기.
	   3) **로그인 시뮬레이션**: 사용자 아이디와 비밀번호를 입력받아, 특정 아이디/비밀번호와 일치할 때 "로그인 성공"을, 그렇지 않으면 "로그인 실패"를 출력하는 페이지 만들기.
	   4) **숫자 덧셈 vs 문자열 결합**: 두 입력값을 받아, 숫자일 경우 합을, 문자열일 경우 결합된 문자열을 출력하는 페이지 만들기.
	      hint) str.matches("-?\\d+(\\.\\d+)?") 활용(숫자형일 때,true)
	   5) **숫자 짝/홀 판별 **: 숫자면 짝수인지 홀수인지 판별하고 출력하는 JSP 페이지 만들기.
	2. 아래 내용을 DB로 처리하여 화면에 출력하세요.
	        # 처리순서, sql구문, 메서드선언, 화면호출, 요청값처리
	   1)[선택]  부서번호별 직원 조회: EMP와 각 부서 번호에 해당하는 직원들의 이름과 부서번호를 조회하는 쿼리 작성.
		부서번호 [10~40  ] [조회] 
		사원번호 사원명  급여  부서번호
	   2) 직책명 [     ]
	      입사분기 [1~4 ]  [조회]
	           사원번호 사원명 직책명 입사분기 입사일 
	    3) 특정 기간에 입사한 직원 조회: EMP 테이블을 사용하여 @@@@/@@/@@ 부터 @@@@/@@/@@ 사이에 입사한 직원들의 목록을 조회하는 쿼리 작성.
	      입사일:[@@@@/@@/@@] ~ [@@@@/@@/@@] [검색]
	      사원번호 사원명 직책 입사일(@@@/@@/@@표시) 부서번호
	 
	 * */
	/*1)[선택]  부서번호별 직원 조회: EMP와 각 부서 번호에 해당하는 직원들의 이름과 부서번호를 조회하는 쿼리 작성.
	부서번호 [10~40  ] [조회] 
	사원번호 사원명  급여  부서번호*/
	public List<Emp> getEmpListByDeptno(int deptno) {
		List<Emp> elist = new ArrayList<Emp>();
		String sql = "SELECT empno, ename, sal, deptno\r\n" + "FROM emp\r\n" + "WHERE deptno = ? ";

		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			pstmt.setInt(1, deptno);
			try (ResultSet rs = pstmt.executeQuery();) {
				// 처리코드 2
				while (rs.next()) {
					elist.add(new Emp(rs.getInt("empno"), rs.getString("ename"), rs.getDouble("sal"),
							rs.getInt("deptno")));
				}
				System.out.println("데이터 건수 : " + elist.size());
			}
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}

		return elist;
	}

	// 2-2번문제
	public List<EmpHire> getEmpHireInfo(String job, String hire_qua) {
		List<EmpHire> hiList = new ArrayList<EmpHire>();
		String sql = "SELECT empno, ename, job, to_char(hiredate, 'Q') hire_qua,\r\n"
				+ "	to_char(hiredate, 'YYYY/MM/DD') hire_str\r\n" 
				+ "FROM emp\r\n" 
				+ "WHERE job LIKE ?\r\n"
				+ "AND to_char(hiredate, 'Q') = ?";

		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			pstmt.setString(1, "%" + job + "%");
			pstmt.setString(2, hire_qua);
			try (ResultSet rs = pstmt.executeQuery();) {
				// 처리코드 2
				while (rs.next()) {
					hiList.add(new EmpHire(
							rs.getInt("empno"), rs.getString("empno"), 
							rs.getString("job"), rs.getString("hire_qua"), 
							rs.getString("hire_str")
					));

				}
				System.out.println("데이터 건수 : " + hiList.size());
			}
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}

		return hiList;

	}
	
	// 2-3문제
	public List<EmpHireTerm> getEmphireList(String start, String end) {
		List<EmpHireTerm> list = new ArrayList<EmpHireTerm>();
		String sql = "SELECT empno, ename, job,\r\n"
				+ "	to_char(hiredate, 'YYYY/MM/DD') hire_str, deptno\r\n"
				+ "FROM emp\r\n"
				+ "WHERE hiredate BETWEEN to_date(?, 'YYYY/MM/DD')\r\n"
				+ "	AND to_date(?, 'YYYY/MM/DD')";
		
		try (Connection con = DBCon.con(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 처리코드1
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			try (ResultSet rs = pstmt.executeQuery();) {
				// 처리코드 2
				while (rs.next()) {
					list.add(new EmpHireTerm(
							rs.getInt("empno"),
							rs.getString("ename"),
							rs.getString("job"),
							rs.getString("hirestr"),
							rs.getInt("deptno")
					));

				}
				System.out.println("데이터 건수 : " + list.size());
			}
		} catch (SQLException e) {
			System.out.println("DB에러 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 에러 : " + e.getMessage());
		}
		
		return list;
	}
	
}
