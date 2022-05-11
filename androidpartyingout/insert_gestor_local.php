<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id_local = $_POST['id_local'];
        $id_user = $_POST['id_user'];

        
        $query = "INSERT INTO r_gestor_local(id_local,id_user) VALUES 
        ('$id_local' , '$id_user')";
          
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
