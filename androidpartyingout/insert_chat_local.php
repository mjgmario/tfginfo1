<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_local = $_POST['id_local'];
        $nombre = $_POST['nombre'];
        $descripcion = $_POST['descripcion'];

        $query = "INSERT INTO chat_local(nombre_chat, id_local, fecha_creacion, descripcion) VALUES 
        ('$nombre', '$id_local', NOW() , '$descripcion' )";
          
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
