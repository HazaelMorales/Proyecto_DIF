<?php 
	require 'conexion.php';

	$sql = "SELECT * FROM usuarios";
	$datos = Array();
	$resul = mysqli_query($conn,$sql);

	while ($row = mysqli_fetch_object($resul)) {
		$datos[] = $row;
	}
	echo json_encode($datos, JSON_UNESCAPED_UNICODE); //Acepta caracteres especiales
	mysqli_close($conn);
 ?>