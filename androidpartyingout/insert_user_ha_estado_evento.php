<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    
        $id_user = $_POST['id_user'];
        $id_evento = $_POST['id_evento'];
        
        
        $query = "INSERT INTO r_ha_estado_evento(id_user, id_evento) VALUES 
        ('$id_user', '$id_evento')";
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
           
        }
        else{
            $response['error'] = true;
            $response['message'] = "No se han encontrado filas";
        
        }
        } catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    