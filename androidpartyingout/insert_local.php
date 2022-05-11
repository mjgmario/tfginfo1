<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $nombre = $_POST['nombre'];
        $descripcion =  $_POST['descripcion'];
        $loc1 = $_POST['loc1'];
        $loc2 = $_POST['loc2'];
        $loc3 = $_POST['loc3'];
        

        
        $query = "INSERT INTO local(nombre,descripcion, loc1, loc2, loc3) VALUES 
        ('$nombre' ,' $descripcion',  '$loc1', '$loc2', '$loc3')";
          
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
        
        }
        else{
            $response['error'] = true;
            $response['message'] = "No se ha podido ejecutar";
        
        }
        } catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        
        
        $mysql->close();
        echo json_encode($response); 
   
    }   

?>
