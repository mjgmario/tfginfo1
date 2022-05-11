<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    
        $id_seguidor = $_POST['id_seguidor'];
        $id_seguido = $_POST['id_seguido'];
        
        
        $query = "SELECT * FROM  r_seguidor_seguido WHERE 
        id_seguidor = '$id_seguidor' AND id_seguido='$id_seguido'";
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
            $response['sigue'] = true;

           
        }
        else{
            $response['error'] = false;
            $response['sigue'] = false;

        }
        } catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    