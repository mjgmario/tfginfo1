<?php
header('Content-Type: application/json');
require_once("db.php");
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $desc = $_POST['descripcion'];
        // ver si el usuario ya esta en el chat
    
    $query1 = "SELECT * FROM publicacion WHERE descripcion='$desc' ";
    $result1 = $mysql->query($query1);

    

    $lista1 = array();
    while($row =mysqli_fetch_assoc($result1)){
        $lista1[] = $row;
    }
    $response['lista'] = $lista1;

       
        
        $mysql->close();
        echo json_encode($response);
    }   

   ?> 