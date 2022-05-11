<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    
        $username = $_POST['nombre'];
        $password = $_POST['password'];

        $query = "SELECT * FROM usuarios WHERE (nombre='$username' AND password= '$password') OR
        (correo='$username' AND password= '$password') ";
        $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
            while($row =mysqli_fetch_assoc($result)){
            $response['id']  = $row['id'];

        }
           
        }else{
            $response['error'] = true;
            $response['message'] = "No se han encontrado filas";
        
        }
        $result->close();
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    