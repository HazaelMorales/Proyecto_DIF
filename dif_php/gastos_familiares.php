<?php
require('conexion.php');
$estudios = $_POST['estudio_socioeconomico'];
$gasto = $_POST['tipo_gasto'];
$monto = $_POST['monto_total'];


$sql = "INSERT INTO gastos_familiares (estudio_socioeconomico, tipo_gasto, monto_total) 
VALUES ('$estudios','$gasto','$monto')";

if(!empty($estudios) && !empty($gasto) && !empty($monto)){
    $result = mysqli_query($conn, $sql);
    if($result){
        echo "Datos Insertados";
    }else{
        echo "Datos Insertados";
    }
    mysqli_close($conn);
}
?>