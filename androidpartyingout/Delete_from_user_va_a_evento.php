<?php

require_once("db.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
        $id_evento = $_POST['id_evento'];
        $id_user = $_POST['id_user'];
        
        
        $query1 = "DELETE FROM r_va_a_evento WHERE id_user = '$id_user' AND id_evento = '$id_evento'";
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
    