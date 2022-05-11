<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_user = $_POST['id_user'];
        $descripcion = $_POST['descripcion'];

        
        $query = "INSERT INTO publicacion(id_user,fecha, descripcion) VALUES 
        ('$id_user' ,  NOW(), '$descripcion')";
          
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
