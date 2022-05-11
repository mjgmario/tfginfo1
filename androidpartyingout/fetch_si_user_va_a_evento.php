<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    
        $id_user = $_POST['id_user'];
        $id_evento = $_POST['id_evento'];
        

        $query = "SELECT * FROM  r_va_a_evento WHERE 
        id_user = '$id_user' AND id_evento='$id_evento' ";
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
            $response['va'] = true;

           
        }
        else{
            $response['error'] = false;
            $response['va'] = false;

        }
        } catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    