<?php 
	require('conexion.php');

	$id_caso = $_POST['id_caso'];
	$id_usuario = $_POST['id_usuario'];
	$id_beneficiario = $_POST['id_beneficiario'];

	$sql = $conn->prepare("SELECT psi.id_consulta, ben.nombres, ben.AP, ben.AM, psi.fecha, psi.hora,psi.motivo_atencion, psi.tipo_registro, psi.notas_sesion, psi.psicol_o_psiq, psi.consentimiento, psi.estado FROM psicologia as psi 
			INNER JOIN beneficiarios as ben
			ON psi.beneficiario = ben.id_beneficiario
			WHERE caso = ? AND usuario = ? AND beneficiario = ?");

	$sql ->bind_param('iii',$id_caso,$id_usuario,$id_beneficiario);
	$sql->execute();

	$datos_psicologicos = $sql->get_result();
	if($row =$datos_psicologicos->fetch_assoc()){
		echo json_encode($row,JSON_UNESCAPED_UNICODE);
	}
	$sql->close();
	$conn->close();
	
 ?>