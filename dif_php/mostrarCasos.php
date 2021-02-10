<?php 
	require('conexion.php');

	$item = $_POST['id_case'];
	$dato1 = explode(" ", $item);
	$id_caso = $dato1[0];

	$sql=$conn->prepare("SELECT * FROM casos WHERE id_caso=?");
	$sql ->bind_param('s',$id_caso);
	$sql->execute();

	$caso_seleccionado = $sql->get_result();
	if($row =$caso_seleccionado->fetch_assoc()){
		echo json_encode($row,JSON_UNESCAPED_UNICODE);
	}
	$sql->close();
	$conn->close();
 ?>