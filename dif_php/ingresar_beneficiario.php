<?php
Require 'registrobene.php';
$nombre = $_POST['nombres'];
$ap = $_POST['AP'];
$am = $_POST['AM'];
$curp = $_POST['curp'];
$telefono = $_POST['telefono'];
$estado = $_POST['estado'];
$municipio = $_POST['municipio'];
$domicilio = $_POST['domicilio'];
$sexo = $_POST['sexo'];
$fecha_nacimiento = $_POST['fecha_nacimiento'];
$lugar_nacimiento = $_POST['lugar_nacimiento'];
$fecha_registro = $_POST['fecha_registro'];
$estado_civil = $_POST['estado_civil'];
$nombre_escuela = $_POST['nombre_escuela'];
$escolaridad = $_POST['escolaridad'];
$ocupacion = $_POST['ocupacion'];


$sql = "INSERT INTO beneficiarios (nombres, AP, AM, curp, telefono, estado, municipio, domicilio, sexo, fecha_nacimiento, lugar_nacimiento, fecha_registro, estado_civil, escolaridad, nombre_escuela, ocupacion) 
VALUES ('$nombre','$ap','$am','$curp','$telefono','$estado','$municipio','$domicilio','$sexo','$fecha_nacimiento','$lugar_nacimiento','$fecha_registro','$estado_civil','$escolaridad','$nombre_escuela','$ocupacion')";

if(!empty($nombre) && !empty($ap) && !empty($am) && !empty($curp) && !empty($telefono) && !empty($estado) && !empty($municipio) && !empty($domicilio) && !empty($sexo) && !empty($fecha_nacimiento) && !empty($lugar_nacimiento) && !empty($fecha_registro) && !empty($estado_civil) && !empty($escolaridad) && !empty($nombre_escuela) && !empty($ocupacion)){
    $result = mysqli_query($conexion, $sql);
    if($result){
        echo "Datos Insertados";
    }else{
        echo "No se pudo registrar el beneficiario";
    }
    mysqli_close($conexion);
}
?>