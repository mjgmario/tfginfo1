<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
         $id_user = $_POST['id_user'];
        $id_local = $_POST['id_local'];
        
     
        

        $query = "SELECT * FROM  r_ha_estado_local WHERE 
        id_user = '$id_user' AND id_local='$id_local' ";
        try {
            $result = $mysql->query($query);

        if($mysql->affected_rows >0){
            $response['error'] = false;
            $response['va'] = true;

           
        }
        else{
            $response['error'] = false;
            $response['va'] = false;

        }
        } catch (Exception $e) {
            $response['error'] = true;
            $response['message'] = $e->getMessage();
        }
        
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    