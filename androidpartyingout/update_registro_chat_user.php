<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_user = $_POST['id_user'];
        $id_chat = $_POST['id_chat'];

      
        $query = "UPDATE tabla_registro_chat_user SET fecha_ultimo_acceso = NOW()
        WHERE id_user='$id_user' AND id_chat= '$id_chat'";
          
         
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
