<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    
        $id_seguidor = $_POST['id_seguidor'];
        $id_seguido = $_POST['id_seguido'];
        
        $query1 = "DELETE FROM r_seguidor_seguido WHERE id_seguidor = '$id_seguidor' AND id_seguido = '$id_seguido'";

        try{
            $result1 = $mysql->query($query1);
            if($mysql->affected_rows >0){
                $response['error'] = false;
                $response['message'] = "Ok";
            }
            else{
                $response['error'] = true;
                $response['message'] = "No se han encontrado filas";
                }
            } catch (Exception $e) {
                $response['error'] = true;
                $response['message'] = $e->getMessage();
            }
        
        
        $mysql->close();
        echo json_encode($response);
    }   

   
        
   ?>
    