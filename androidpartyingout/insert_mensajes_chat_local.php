<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_autor = $_POST['id_autor'];
        $id_chat_local = $_POST['id_chat_local'];
        $contenido = $_POST['contenido'];
        

        
        $query = "INSERT INTO mensajes_chat_local(id_autor,id_chat_local, contenido, fecha) VALUES 
        ('$id_autor' , '$id_chat_local', '$contenido', NOW())";
          
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
