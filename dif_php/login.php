<?php 
	require('conexion.php');

	$usu_correo = $_POST['correo'];
	$usu_password = $_POST['password'];

	$usu_password_encrytd = sha1($usu_password);

	$sql=$conn->prepare("SELECT * FROM usuarios WHERE correo=? AND password=?");
	$sql ->bind_param('ss',$usu_correo,$usu_password_encrytd);
	$sql->execute();

	$login_user = $sql->get_result();
	if($row = $login_user->fetch_assoc()){
		echo json_encode($row,JSON_UNESCAPED_UNICODE);
	}
	$sql->close();
	$conn->close();
 ?>	