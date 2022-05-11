<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
        $id_chat_local = $_POST['id_chat_local'];
        $id_user = $_POST['id_user'];
        
       
        // ver si el usuario ya esta en el chat
        
        $query1 = "SELECT * FROM r_pertenece_user_chat_local WHERE id_chat_local='$id_chat_local' AND 
        id_user = '$id_user'";
        $result1 = $mysql->query($query1);
        if($mysql->affected_rows >0){
            $response['error'] = true;
            $response['message'] = "Ya esta el user en el chat";
        }
        else{
        $query = "INSERT INTO r_pertenece_user_chat_local(id_chat_local , id_user, fecha) VALUES 
        ('$id_chat_local', '$id_user', NOW())";
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
    }
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    