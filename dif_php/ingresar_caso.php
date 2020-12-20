<?php
    Require 'conexion.php';

    $fecha_apertura = $_POST['fecha_apertura'];
    $descripcion_general = $_POST['descripcion_general'];
    $estado = $_POST['estado'];

    $sql = "INSERT INTO casos (fecha_apertura, descripcion_general, estado) 
    VALUES ('$fecha_apertura','$descripcion_general','$estado')";

    if(!empty($fecha_apertura) && !empty($descripcion_general) && !empty($estado)){
        $result = mysqli_query($conn, $sql);
        if($result){
            echo "Datos Insertados";
        }else{
            echo "No se pudo registrar el beneficiario";
        }
        mysqli_close($conn);
    }
?>