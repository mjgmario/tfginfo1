<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
        $id_chat = $_POST['id_chat'];
        
        
        $query1 = "DELETE FROM chats WHERE id_chat = '$id_chat'";
        try{
            $result1 = $mysql->query($query1);
            if($mysql->affected_rows >0){
                $response['error'] = false;
                $response['message'] = "Ok";
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
    