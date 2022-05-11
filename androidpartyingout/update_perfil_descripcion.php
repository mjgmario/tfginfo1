<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_user = $_POST['id_user'];
        $descripcion = $_POST['descripcion'];


        $query = "UPDATE descripcion SET Contenido =  '$descripcion'
        WHERE id_user='$id_user'";
          
         
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
