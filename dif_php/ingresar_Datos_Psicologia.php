<?php 
	require("conexion.php");

	$id_caso=$_POST['id_caso'];
	$id_trabajador=$_POST['id_usuario'];
	$id_beneficiario=$_POST['id_beneficiario'];
	$fecha = $_POST['fecha'];	
	$hora = $_POST['hora'];
	$motivo = $_POST['motivo_atencion'];
	$tipo = $_POST['tipo_registro'];
	$notas = $_POST['notas_sesion'];
	$psicologia = $_POST['psicologia_psiquiatra'];
	$concentimiento = $_POST['consentimiento'];
	$estado = $_POST['estado'];
	

	$sql = "INSERT INTO psicologia (usuario, caso, beneficiario, fecha, hora, motivo_atencion, tipo_registro, notas_sesion, psicol_o_psiq, consentimiento, estado) 
	VALUES ('$id_trabajador','$id_caso','$id_beneficiario','$fecha','$hora','$motivo','$tipo','$notas','$psicologia','$concentimiento','$estado')";

	if(!empty($id_trabajador) && !empty($id_caso) && !empty($id_beneficiario) && !empty($fecha) && !empty($hora) && !empty($motivo) && !empty($tipo) && !empty($notas) && !empty($psicologia) && !empty($concentimiento) && !empty($estado)){
    	$result = mysqli_query($conn, $sql);
   		if($result){
        	echo "Datos Correctamente";
    	}else{
        	echo "Datos Incorrectamente";
    	}
    	mysqli_close($conn);
	}


 ?>