<?php 
	require("conexion.php");


	$id_caso=$_POST['id_caso'];
	$id_trabajador=$_POST['id_usuario'];
	$id_beneficiario=$_POST['id_beneficiario'];
	$motivo = $_POST['motivo_envio'];	
	$interrogatorio = $_POST['interrogatorio'];
	$tension_arterial = $_POST['tension_arterial'];
	$frec_cardiaca = $_POST['frec_cardiaca'];
	$frec_respiratoria = $_POST['frec_respiratoria'];
	$temperatura = $_POST['temperatura'];
	$sat_O2 = $_POST['sat_O2'];
	$talla = $_POST['talla'];
	$peso = $_POST['peso'];
	$imc = $_POST['imc'];
	$obs_fisicas = $_POST['obs_fisicas'];
	$diagnostico = $_POST['diagnostico'];
	$analisis = $_POST['analisis'];
	$plan_seguimiento = $_POST['plan_seguimiento'];
	$fecha_registro = $_POST['fecha_registro'];
	$estado = $_POST['estado'];

	$sql = "INSERT INTO medica (caso, usuario, beneficiario, motivo_envio, interrogatorio, tension_arterial, frec_cardiaca, frec_respiratoria, temperatura, sat_02, talla, peso, imc, obs_fisicas, diagnostico, analisis, plan_seguimiento, fecha_registro, estado) 
	VALUES ('$id_caso','$id_trabajador','$id_beneficiario','$motivo','$interrogatorio','$tension_arterial','$frec_cardiaca','$frec_respiratoria','$temperatura','$sat_O2','$talla','$peso','$imc','$obs_fisicas','$diagnostico','$analisis','$plan_seguimiento','$fecha_registro','$estado')";

	if(!empty($id_caso) && !empty($id_trabajador) && !empty($id_beneficiario) && !empty($motivo) && !empty($interrogatorio) && !empty($tension_arterial) && !empty($frec_cardiaca) && !empty($frec_respiratoria) && !empty($temperatura) && !empty($sat_O2) && !empty($talla) && !empty($peso) && !empty($imc) && !empty($obs_fisicas) && !empty($diagnostico) && !empty($analisis)&& !empty($plan_seguimiento)&& !empty($fecha_registro)&& !empty($estado)){
    	$result = mysqli_query($conn, $sql);
   		if($result){
        	echo "Datos Correctamente";
    	}else{
        	echo "Datos Incorrectamente";
    	}
    	mysqli_close($conn);
	}


 ?>