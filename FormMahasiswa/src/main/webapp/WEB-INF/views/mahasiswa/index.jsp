<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
request.setAttribute("contextName", request.getContextPath());
%>
<html>
<head>
<title>Form Mahasiswa</title>
<link rel="icon"
	href="https://fr.seaicons.com/wp-content/uploads/2022/04/Edit-validated-icon.png">

</head>
<body style="background-color: skyblue">
	<center>
		<h1>Validation Form</h1>
		<form method="post" action="${contextName}/mhs">
			<table>
				<tr>
					<td><input type="hidden" name="mode" value="tambah"></td>
				</tr>
				<tr>
					<td>NIM</td>
					<td><input type="text" name="nim" value=""
						oninvalid="alert('NIM wajib diisi')" required="required"></td>
				</tr>
				<tr>
					<td>Nama</td>
					<td><input type="text" name="nama" value=""
						oninvalid="alert('Nama wajib diisi')" required="required"></td>
				</tr>
				<tr>
					<td>Tanggal Lahir</td>
					<td><input type="date" name="tgl_lahir" value=""
						oninvalid="alert('Tanggal lahir wajib diisi')" required="required"></td>
				</tr>
				<tr>
					<td>Alamat</td>
					<td><input type="text" name="alamat" value=""
						oninvalid="alert('Alamat wajib diisi')" required="required"></td>
				</tr>
				<tr>
					<td>Jurusan</td>
					<td><input type="text" name="jurusan" value=""
						oninvalid="alert('Jurusan wajib diisi')" required="required"></td>
				</tr>

			</table>
			<button type="submit">Simpan</button>
		</form>

		<ol>
			<c:forEach var="mhs" items="${mhslist}">
				<li>${mhs.nama} - <a
					href="${contextName}/mhs/edit?nim=${mhs.nim}">Edit</a></li>
			</c:forEach>
		</ol>
	</center>
</body>
</html>