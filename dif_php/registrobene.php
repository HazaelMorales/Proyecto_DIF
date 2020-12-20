<?php
$servername = "localhost";
$username = "root";
$password = "";
$database = "dif";

$conexion = mysqli_connect($servername, $username, $password, $database);

if(!$conexion){
    die("Database connection failed".mysqli_connect_error());
}
?>