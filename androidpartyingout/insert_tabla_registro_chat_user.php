<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    
        $id_user = $_POST['id_user'];
        $id_chat = $_POST['id_chat'];
        
        
        $query = "INSERT INTO  tabla_registro_chat_user(id_user, id_chat, fecha_ultimo_acceso) VALUES 
        ('$id_user', '$id_chat', NOW())";
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
    