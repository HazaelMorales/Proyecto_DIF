<?php
	$servername = "localhost"; 
	$username = "id15894991_sergio";
	$password = "Kakaroto123!";
	$database = "id15894991_dif";

	$conn = mysqli_connect($servername, $username, $password, $database);

	if (!$conn) {
 	 	die("Database connection failed: " . mysqli_connect_error());
	}
?>	