<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $nombre = $_POST['nombre'];
        $id_admin =  $_POST['id_admin'];
        $query = "INSERT INTO chats(fecha_creacion, nombre, id_admin) VALUES 
        (NOW() , '$nombre', '$id_admin')";
          
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){

            $query3 = "SELECT id_chat FROM chats        
                WHERE  id_admin = '$id_admin' AND 
                nombre = '$nombre'
                ORDER BY fecha_creacion DESC ";
            $result2 = $mysql->query($query3);                
            $lista = array();
            while($row =mysqli_fetch_assoc($result2)){
                $lista[] = $row;
            }
            $response['id_chat'] = $lista[0]['id_chat'];
            $id_chat =  $response['id_chat'];
            $response['error'] = false;

            $query4 = "INSERT INTO r_pertenece_user_chat
            (id_chat, id_user, fecha) VALUES 
        ( '$id_chat', '$id_admin', NOW())";
             $result4 = $mysql->query($query4);
    
        
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
