<?php
	Require 'conexion.php';

	$nombre = $_POST['nombres'];
	$apellidoPaterno = $_POST['AP'];
	$apellidoMaterno = $_POST['AM'];
	$email = $_POST['correo'];
	$pwd = $_POST['password'];
	$rol = $_POST['rol'];

	$sql = "INSERT INTO usuarios (nombres, AP , AM, correo, password, rol)
	VALUES ('$nombre','$apellidoPaterno','$apellidoMaterno','$email', '$pwd', '$rol')";

	if (!empty($nombre) && !empty($apellidoPaterno) && !empty($apellidoMaterno) && !empty($email) && !empty($pwd) && !empty($rol)){
    	$result = mysqli_query($conn, $sql);
    	if ($result) {
    		echo "Datos Ingresados Correctamente";
    	}else{
    		echo "No se pudo insertar";
    	}
    	mysqli_close($conn);
	}
?>