<?php
require('conexion.php');
$beneficiario = $_POST['beneficiario'];
$beneficiario_dependiente = $_POST['beneficiario_dependiente'];
$parentesco = $_POST['parentesco'];


$sql = "INSERT INTO parentesco (beneficiario, beneficiario_dependiente, parentesco) 
VALUES ('$beneficiario','$beneficiario_dependiente','$parentesco')";

if(!empty($beneficiario) && !empty($beneficiario_dependiente) && !empty($parentesco)){
    $result = mysqli_query($conn, $sql);
    if($result){
        echo "Datos Insertados";
    }else{
        echo "No se pudo registrar los gastos";
    }
    mysqli_close($conn);
}
?>