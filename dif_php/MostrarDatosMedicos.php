<?php 
	require('conexion.php');

	$id_caso = $_POST['id_caso'];
	$id_usuario = $_POST['id_usuario'];
	$id_beneficiario = $_POST['id_beneficiario'];

	$sql = $conn->prepare("SELECT med.id_consulta,ben.nombres, ben.AP, ben.AM, med.motivo_envio, med.interrogatorio, med.tension_arterial, med.frec_cardiaca, med.frec_respiratoria, med.temperatura, med.sat_02, med.talla, med.peso, med.imc, med.obs_fisicas, med.diagnostico, med.analisis, med.plan_seguimiento, med.fecha_registro, med.estado FROM medica as med 
			INNER JOIN beneficiarios as ben
			ON med.beneficiario = ben.id_beneficiario	
			WHERE caso = ? AND usuario = ? AND beneficiario = ?");

	$sql ->bind_param('iii',$id_caso,$id_usuario,$id_beneficiario);
	$sql->execute();

	$datos_medicos = $sql->get_result();
	if($row =$datos_medicos->fetch_assoc()){
		echo json_encode($row,JSON_UNESCAPED_UNICODE);
	}
	$sql->close();
	$conn->close();

 ?>