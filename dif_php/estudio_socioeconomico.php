<?php
Require 'conexion.php';
$usuario = $_POST['usuario'];
$caso = $_POST['caso'];
$beneficiario = $_POST['beneficiario'];
$ingreso_mensual = $_POST['ingreso_mensual'];
$incapacidad = $_POST['incapacidad'];
$enfermedad = $_POST['enfermedad'];
$total_gastos = $_POST['total_gastos'];
$servicio_mactual = $_POST['servicio_mactual'];
$tipo_vivienda = $_POST['tipo_vivienda'];
$servicio_actual = $_POST['servicio_actual'];
$apoyo_solicitado = $_POST['apoyo_solicitado'];
$diagnostico = $_POST['diagnostico'];
$observaciones = $_POST['observaciones'];
$estado = $_POST['estado'];


$sql = "INSERT INTO estudio_socioeconomico (usuario, caso, beneficiario, ingreso_mensual, incapacidad, enfermedad, total_gastos, servicio_mactual, tipo_vivienda, servicio_actual, apoyo_solicitado, diagnostico, observaciones, estado) 
VALUES ('$usuario','$caso','$beneficiario','$ingreso_mensual','$incapacidad','$enfermedad','$total_gastos','$servicio_mactual','$tipo_vivienda','$servicio_actual','$apoyo_solicitado','$diagnostico','$observaciones','$estado')";

if(!empty($usuario) && !empty($caso) && !empty($beneficiario) && !empty($ingreso_mensual) && !empty($incapacidad) && !empty($enfermedad) && !empty($total_gastos) && !empty($servicio_mactual) && !empty($tipo_vivienda) && !empty($servicio_actual) && !empty($apoyo_solicitado) && !empty($diagnostico) && !empty($observaciones) && !empty($estado)){
    $result = mysqli_query($conn, $sql);
    if($result){
        echo "Datos Insertados";
    }else{
        echo "Datos Insertados";
    }
    mysqli_close($conn);
}
?>