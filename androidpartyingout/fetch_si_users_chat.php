<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    
        $id_chat = $_POST['id_chat'];

        $id_user = $_POST['id_user'];
                
        
        $query1 = "SELECT * FROM r_pertenece_user_chat
        WHERE id_chat = ' $id_chat' AND id_user='$id_user'";
        $result1 = $mysql->query($query1);
        if($mysql->affected_rows >0){
            $response['esta'] = true;

            $response['error'] = false;
            $response['message'] = "El usuario ya esta";
        }
        else{   
            $response['esta'] = false;
            $response['error'] = false;
       
        }
        
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    

