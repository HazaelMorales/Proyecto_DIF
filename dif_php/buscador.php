<?php
    require 'conexion.php';

    $curp=$_GET['curp'];

    $consulta = "SELECT * FROM beneficiarios where curp = '$curp'";
    $resultado = $conn  -> query($consulta);

    while($fila=$resultado->fetch_array()){
        $beneficiarioss[] =array_map('utf8_encode',$fila);
    }
    echo json_encode($beneficiarioss);
    $resultado -> close();
?>