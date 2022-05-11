<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
        $id_user = $_POST['id_user'];
        $nombre_chat = $_POST['nombre_chat'];
        $nombre_local = $_POST['nombre_local'];
        $descripcion = $_POST['descripcion'];


        
        //obtener id del local
        $query2 = "SELECT * FROM local WHERE nombre='$nombre_local' ";
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
        $id_local = $lista1[0]['id_local'];

        // ver si el nombre ya esta en los chats de locales

        $query6 = "SELECT * FROM chat_local WHERE nombre_chat='$nombre_chat' AND id_local = ' $id_local' ";
        $result6 = $mysql->query($query6);
        if($mysql->affected_rows > 0){
            $response['ExisteNombre'] = true;
        }
        else{
        
            $response['ExisteNombre'] = false;
        $query = "INSERT INTO chat_local(nombre_chat, id_local, fecha_creacion, descripcion) VALUES 
        ('$nombre_chat', '$id_local', NOW(), '$descripcion' )";
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;

            
            $query4 = "SELECT * FROM chat_local WHERE nombre_chat='$nombre_chat' 
            AND '$id_local'= id_local AND descripcion = '$descripcion' ";
            $result4 = $mysql->query($query4);
            
            $lista5 = array();
            while($row =mysqli_fetch_assoc($result4)){
                $lista5[] = $row;
            }
            $id_chat_local = $lista5[0]['id_chat_local'];



            $query3 = "INSERT INTO  r_pertenece_user_chat_local(id_chat_local, id_user, fecha) VALUES 
            ('$id_chat_local', '$id_user',  NOW())";
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
    