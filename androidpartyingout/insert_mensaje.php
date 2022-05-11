<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_autor = $_POST['id_autor'];
        $id_chat = $_POST['id_chat'];
        $contenido = $_POST['contenido'];
        

        
        $query = "INSERT INTO mensajes(id_autor,id_chat, contenido, fecha) VALUES 
        ('$id_autor' , '$id_chat', '$contenido', NOW())";
          
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
            $query2 = "UPDATE chats SET fecha_ultimo_mensaje = NOW()
            WHERE id_chat='$id_chat'";
            $result2 = $mysql->query($query2);
            
        
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
