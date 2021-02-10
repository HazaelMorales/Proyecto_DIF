<?php
Require 'conexion.php';
$servicio = $_POST['servicio'];
$beneficiario = $_POST['beneficiario'];
$canalizacion_intrainstitucional = $_POST['canalizacion_intrainstitucional'];
$canalizacion_instituciones = $_POST['canalizacion_instituciones'];
$referido = $_POST['referido'];
$autoridad_contacto = $_POST['autoridad_contacto'];
$acciones_emprendidas = $_POST['acciones_emprendidas'];
$areas_seguimiento = $_POST['areas_seguimiento'];
$estatus = $_POST['estatus'];

$sql = "INSERT INTO servicios_sociales (servicio, beneficiario, canalizacion_intrainstitucional, canalizacion_instituciones, referido, autoridad_contacto, acciones_emprendidas, areas_seguimiento,estatus) 
VALUES ('$servicio','$beneficiario','$canalizacion_intrainstitucional','$canalizacion_instituciones','$referido','$autoridad_contacto','$acciones_emprendidas','$areas_seguimiento','$estatus')";

if(!empty($servicio) && !empty($beneficiario) && !empty($canalizacion_intrainstitucional) && !empty($canalizacion_instituciones) && !empty($referido) && !empty($autoridad_contacto) && !empty($acciones_emprendidas) && !empty($areas_seguimiento) && !empty($estatus)){
    $result = mysqli_query($conn, $sql);
    if($result){
        echo "Datos Insertados";
    }else{
        echo "Datos insertados.";
    }
    mysqli_close($conn);
}
?>