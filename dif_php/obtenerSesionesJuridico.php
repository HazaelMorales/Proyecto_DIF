<?php 
	require 'conexion.php';

	$sql = "SELECT * FROM juridico JOIN beneficiarios WHERE juridico.beneficiario = beneficiarios.id_beneficiario";
	$datos = Array();
	$resul = mysqli_query($conn,$sql);

	while ($row = mysqli_fetch_object($resul)) {
		$datos[] = $row;
	}
	echo json_encode($datos, JSON_UNESCAPED_UNICODE); //Acepta caracteres especiales
	mysqli_close($conn);
 ?>