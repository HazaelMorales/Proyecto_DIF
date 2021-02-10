<?php 
	
	require('conexion.php');

	$item = $_POST['id_session'];
	$dato1 = explode(" ", $item);
	$id_bene = $dato1[0];
	$id_nombre = $dato1[1];

	$sql=$conn->prepare("SELECT * FROM juridico JOIN beneficiarios WHERE juridico.beneficiario = beneficiarios.id_beneficiario and id_atencion=? AND nombres=?");
	$sql ->bind_param('ss',$id_bene,$id_nombre);
	$sql->execute();

	$beneficiario_seleccionado = $sql->get_result();
    
	if($row =$beneficiario_seleccionado->fetch_assoc()){
		echo json_encode($row,JSON_UNESCAPED_UNICODE);
        
	}
	$sql->close();
	$conn->close();
 ?>