<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
        $id_chat = $_POST['id_chat'];
        $nombre = $_POST['nombre'];
        
        //obtener id del nombre
        $query2 = "SELECT * FROM usuarios WHERE nombre='$nombre' ";
        $result2 = $mysql->query($query2);
        if($mysql->affected_rows == 0){
            $response['error'] = false;
            $response['noExiste'] = true;
        }
        else{
            $response['noExiste'] = false;

        $lista1 = array();
            while($row =mysqli_fetch_assoc($result2)){
                $lista1[] = $row;
        }
        $id_user = $lista1[0]['id'];
        // ver si el usuario ya esta en el chat
        
        $query1 = "SELECT * FROM r_pertenece_user_chat WHERE id_chat='$id_chat' AND 
        id_user = '$id_user'";
        $result1 = $mysql->query($query1);
        if($mysql->affected_rows >0){
            $response['error'] = true;
            $response['message'] = "Ya esta el user en el chat";
        }
        else{
        $query = "INSERT INTO r_pertenece_user_chat(id_chat, id_user, fecha) VALUES 
        ('$id_chat', '$id_user', NOW())";
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
            $query3 = "INSERT INTO  tabla_registro_chat_user(id_user, id_chat, fecha_ultimo_acceso) VALUES 
            ('$id_user', '$id_chat', NOW())";
            $result = $mysql->query($query3);
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
}
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    