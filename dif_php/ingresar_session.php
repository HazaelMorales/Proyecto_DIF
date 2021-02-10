<?php
Require 'conexion.php';
$nombre = $_POST['usuario'];
$ap = $_POST['caso'];
$am = $_POST['beneficiario'];
$curp = $_POST['estado'];
$telefono = $_POST['fecha_registro'];
$estado = $_POST['procedencia'];
$municipio = $_POST['observaciones_relevantes'];
$domicilio = $_POST['telefono'];
$sexo = $_POST['contacto_alternativo'];
$fecha_nacimiento = $_POST['proceso_actual_iniciado'];
$lugar_nacimiento = $_POST['opciones_seguimiento'];
$fecha_registro = $_POST['descripcion_detallada'];
$estado_civil = $_POST['proceso_pendiente'];



$sql = "INSERT INTO juridico (usuario, caso, beneficiario, estate, fecha_registroj, procedencia, observaciones_relevantes, telefonoj, contacto_alternativo,
 proceso_actual_iniciado, opciones_seguimiento, descripcion_detallada, proceso_pendiente) 
VALUES ('$nombre','$ap','$am','$curp','$telefono','$estado','$municipio','$domicilio','$sexo','$fecha_nacimiento','$lugar_nacimiento','$fecha_registro','$estado_civil')";

if(!empty($nombre) && !empty($ap) && !empty($am) && !empty($curp) && !empty($telefono) && !empty($estado) && !empty($municipio) && !empty($domicilio) && !empty($sexo) && !empty($fecha_nacimiento) && !empty($lugar_nacimiento) && !empty($fecha_registro) && !empty($estado_civil)){
    $result = mysqli_query($conn, $sql);
    if($result){
        echo "Datos Insertados";
    }else{
        echo "No se pudo registrar el beneficiario";
    }
    mysqli_close($conn);
}
?>